package nsk.cath.com.services;

import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;
import nsk.cath.com.controller.MessageBean;
import nsk.cath.com.controller.UsersBean;
import nsk.cath.com.model.Users;
import nsk.cath.com.utility.SessionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author LEOGOLD
 */
@Service
@Transactional

public class UserService {

    public static final String LOGIN_ACTIVE = "act";
    public static final String LOGIN_BLOCKED = "blk";
    public static final String LOGIN_INACTIVE = "ina";

    public static final int ROLE_ADMIN = 1;
    public static final int ROLE_USER = 2;

    private boolean flag = false;

    @Autowired
    private SessionFactory sessionFactory;

    HttpSession httpSession;
    private Session session;
    private String hql;
    private Query query;
    int quryResult = 0;
    int role = 0;

    private Users user;
    private List<Users> userList;

    @Autowired
    private UsersBean userBean;
    @Autowired
    private MessageBean messageBean;

    public String save() {
        if (!userBean.getUsername().isEmpty() && !userBean.getFirstName().isEmpty() && !userBean.getLastName().isEmpty() && !userBean.getPhoneNumber().isEmpty() && !userBean.getPassword().isEmpty()) {

            user = new Users(
                    userBean.getUsername(),
                    userBean.getFirstName(),
                    userBean.getLastName(),
                    userBean.getEmail(),
                    userBean.getPhoneNumber(),
                    userBean.getHomeParish(),
                    userBean.getHomeAddress(),
                    userBean.getHomeTown(),
                    userBean.getResidenceParish(),
                    userBean.getResidenceAddress(),
                    userBean.getOccupation(),
                    userBean.getDateOfBirth(),
                    userBean.getGender(),
                    userBean.getQualification(),
                    userBean.getPassword(),
                    ROLE_USER,
                    LOGIN_ACTIVE
            );

            session = sessionFactory.getCurrentSession();
            session.save(user);
            return "login";
        } else {
            userBean.setMessage("All fields must be filled");
            return "user_form";
        }
    }

    public List<Users> viewUser() {

        try {

            session = sessionFactory.getCurrentSession();
            query = session.createQuery("FROM Users");
            userList = query.list();

        } catch (NullPointerException npex) {
            System.err.println("Can not perform " + npex.getMessage());
        }

        if (userList != null) {
            httpSession = SessionUtils.getSession();
            httpSession.setAttribute("allUser", userList);
            userBean.setServiceQueryReport("success");

            return userList;
        } else {
            userBean.setServiceQueryReport("failed");
            return null;
        }
    }

    public String loginControlBtn() {
        HttpSession session = SessionUtils.getSession();
        if (session.getAttribute("username") != null) {
            logout();
            userBean.setSignBtn("Sign Up");
            return "index";
        } else {
            userBean.setMessage(null);
            return "login";
        }
    }

    public String login() throws SQLException {
        String username = userBean.getUsername();
        String password = userBean.getPassword();
        String status;
        if (!username.isEmpty() || !password.isEmpty()) {

            try {
                session = sessionFactory.getCurrentSession();

                user = (Users) session.createQuery("FROM Users u "
                        + "WHERE u.username = :username "
                        + "AND u.password = :password")
                        .setParameter("username", username)
                        .setParameter("password", password)
                        .uniqueResult();

            } catch (NullPointerException ex) {
                System.err.println(ex);
                System.out.println("Nullpointer exception caught " + ex.getMessage());
            }

            if (user != null) {
                status = user.getStatus();
                role = user.getRole();
                int sn = user.getSn();

                httpSession = SessionUtils.getSession();
                if (sn == 1) {
                    if (role != 1) {
                        httpSession.setAttribute("userView", user);
                        assignRole(ROLE_ADMIN);
                        role = ROLE_ADMIN;
                        httpSession.removeAttribute("userView");
                        System.out.println("The new is "+role);
                    }

                    httpSession.setAttribute("username", user.getUsername());
                    httpSession.setAttribute("loggedUser", user);

                    userBean.setLogName(user.getUsername());
                    setUserBean(user);
                    userBean.setLoginBtn("Log out");
                    userBean.setSignBtn(null);
                    httpSession.setAttribute("adminRole", role);
                    return "admin";

                } else {

                    switch (status) {
                        case LOGIN_ACTIVE:
                            httpSession.setAttribute("username", user.getUsername());
                            httpSession.setAttribute("loggedUser", user);

                            userBean.setLogName(user.getUsername());
                            setUserBean(user);
                            userBean.setLoginBtn("Log out");
                            userBean.setSignBtn(null);

                            switch (role) {

                                case ROLE_ADMIN:
                                    httpSession.setAttribute("adminRole", role);
                                    return "admin";

                                case ROLE_USER:
                                    return "user";

                                default:
                                    return "user";
                            }
                        case LOGIN_BLOCKED:
                            userBean.setAccountInfoReport("Sorry  " + user.getFirstName() + "! your Account is Currently blocked, please contact admin for further clearifications");
                            messageBean.setEmail(user.getUsername());
                            return "blocked";

                        case LOGIN_INACTIVE:
                            userBean.setAccountInfoReport("Sorry  " + user.getFirstName() + "! We noticed that your Account is Currently inactive, this may be due to an unresovled issues,  please contact admin for further clearifications");
                            messageBean.setEmail(user.getUsername());
                            return "inactive";

                        default:
                            userBean.setAccountInfoReport("Sorry  " + user.getFirstName() + "! you can't currently access your account now , please try again later or contact admin for further clearifications");
                            messageBean.setEmail(user.getUsername());
                            return "issue";
                    }
                }

            } else {
//                FacesContext.getCurrentInstance().addMessage(
//                        null,
//                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid login", "Please enter correct Username and Password"));
                userBean.setMessage("wrong username or password");
                userBean.setPassword("");
                return "login";
            }
        } else {
            userBean.setMessage("Please enter valid username and password");
            return "login";
        }
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        userBean.setMessage("Log out successful");
        userBean.setUsername("");
        userBean.setLogName(null);
        userBean.setLoginBtn("Login");
        return "login";
    }

    public int assignRole(int roleToAssign) {
//        if(roleToAssign == ROLE_ADMIN){
//            roleToAssign = ROLE_ADMIN;
//        }else{
//            roleToAssign =ROLE_TEACHER;
//        }
        Users userView = (Users) httpSession.getAttribute("userView");

        hql = "UPDATE Users p SET p.role=:role WHERE p.username =:id";
        session = sessionFactory.getCurrentSession();
        query = session.createQuery(this.hql);
        query.setParameter("id", userView.getUsername());
        query.setParameter("role", roleToAssign);

        return query.executeUpdate();

    }

    private void setUserBean(Users user) {
        userBean.setFirstName(user.getFirstName());
        userBean.setLastName(user.getLastName());
        userBean.setUsername(user.getUsername());
        userBean.setEmail(user.getEmail());
        userBean.setHomeAddress(user.getHomeAddress());
        userBean.setHomeParish(user.getHomeParish());
        userBean.setHomeTown(user.getHomeTown());
        userBean.setResidenceAddress(user.getResidentialAddress());
        userBean.setResidenceParish(user.getResidentialParish());
        userBean.setOccupation(user.getOccupation());
        userBean.setQualification(user.getQualification());
        userBean.setDateOfBirth(user.getDateOfBirth());
        userBean.setPhoneNumber(user.getPhone());
        userBean.setGender(user.getGender());
        userBean.setRole(user.getRole());
        userBean.setStatus(user.getStatus());
        userBean.setCreated(user.getCreated());
        userBean.setUpdated(user.getUpdated());
    }

    public String loggedUser() {
        httpSession = SessionUtils.getSession();
        user = (Users) httpSession.getAttribute("loggedUser");
        role = user.getRole();
        setUserBean(user);
        switch (role) {
            case ROLE_ADMIN:
                return "admin";
            case ROLE_USER:
                return "user";
            default:
                return "user";
        }
    }

    public String moreDetails(String username) {
        try {
            session = sessionFactory.getCurrentSession();

            user = (Users) session.createQuery("FROM Users u "
                    + "WHERE u.username = :username")
                    .setParameter("username", username)
                    .uniqueResult();
        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
        }
        if (user != null) {
            httpSession = SessionUtils.getSession();
            httpSession.setAttribute("userView", user);
            setUserBean(user);
            return "success";
        } else {
            return "failed";
        }
    }

    public String updateLoggedUser() {
        String username = SessionUtils.getUserName();
        user = new Users(userBean.getFirstName(), userBean.getLastName(), userBean.getEmail(), userBean.getPhoneNumber());
        hql = "UPDATE Users u SET u.firstName=:fName, u.lastName=:lName, u.phone=:phone,u.emailId=:email WHERE u.username =:uName";
        session = sessionFactory.getCurrentSession();
        query = session.createQuery(this.hql);
        query.setParameter("uName", username);
        query.setParameter("fName", user.getFirstName());
        query.setParameter("lName", user.getLastName());
        query.setParameter("phone", user.getPhone());
        query.setParameter("email", user.getEmail());

        quryResult = query.executeUpdate();

        if (quryResult != 0) {
            userBean.setMessage("Your details have been updated successfully");
            return "success";
        } else {
            userBean.setMessage("Update was not succesful");
            return "failed";
        }
    }

    /*session = sessionFactory.getCurrentSession();
        Movie movieDelete = (Movie) httpSession.getAttribute("movieViewed");
        movie = (Movie) session.get(Movie.class, movieDelete.getMovieId());
        session.delete(movie);
        httpSession.removeAttribute("movieList");
        return "success";*/
    public String delete() {
        try {

            session = sessionFactory.getCurrentSession();
            Users userDelete = (Users) httpSession.getAttribute("userView");
            user = (Users) session.get(Users.class, userDelete.getUsername());
            session.delete(user);
            httpSession.removeAttribute("userView");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        hql = "DELETE FROM Users u WHERE u.username=:username";
//        query = session.createQuery(hql);
//        query.setParameter("username", username);
//        query.executeUpdate();
        return "success";
    }

    public List<Users> findByProperty(String prop, Object obj) {
        try {
            session = sessionFactory.getCurrentSession();

            query = session.createQuery("FROM Users u "
                    + "WHERE u." + prop + " = :obj")
                    .setParameter("obj", obj);
            userList = query.list();

        } catch (EmptyResultDataAccessException ex) {
            ex.printStackTrace();
        }
        if (userList != null) {

            return userList;
        } else {
//            FacesContext.getCurrentInstance().addMessage(
//                    null,
//                    new FacesMessage(FacesMessage.SEVERITY_WARN, "User not exist", "User with specified attribute does not exist"));
            userBean.setMessage("User with specified attribute does not exist");
            return null;
        }
    }

    public String setAdminRole() {
        Users userRole = null;
        try {

            session = sessionFactory.getCurrentSession();
            httpSession = SessionUtils.getSession();
            userRole = (Users) httpSession.getAttribute("userView");
            String username = userRole.getUsername();

            hql = "UPDATE Users u SET u.role=:role WHERE u.username =:uName";
            query = session.createQuery(hql);
            query.setParameter("uName", username);
            query.setParameter("role", ROLE_ADMIN);
            quryResult = query.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (quryResult != 0) {
            userBean.setRole(ROLE_ADMIN);
            userBean.setMessage(userRole.getFirstName() + " has been successfully granted administrator right");
            return "success";
        } else {
            userBean.setMessage("Update was not succesful");
            return "failed";
        }

    }

    public String blockUser() {
        Users userBlock = null;
        try {
            session = sessionFactory.getCurrentSession();
            httpSession = SessionUtils.getSession();
            userBlock = (Users) httpSession.getAttribute("userView");
            String username = userBlock.getUsername();

            hql = "UPDATE Users u SET u.status=:status WHERE u.username =:uName";
            query = session.createQuery(hql);
            query.setParameter("status", LOGIN_BLOCKED);
            query.setParameter("uName", username);
            quryResult = query.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (quryResult != 0) {
            userBean.setMessage(userBlock.getFirstName() + " has been successfully Blocked");
            return "success";
        } else {
            userBean.setMessage("Update was not succesful");
            return "failed";
        }

    }

    public String resetPassword() {
        try {

            session = sessionFactory.getCurrentSession();
            String email;
            String password;
            httpSession = SessionUtils.getSession();
            Users loggedUser = (Users) httpSession.getAttribute("username");
            if (httpSession != null) {
                email = loggedUser.getEmail();
                userBean.setMessage("userLogged");
            } else {
                email = userBean.getEmail();
                userBean.setMessage("notLogged");
            }
            password = userBean.getPassword();

            hql = "UPDATE Users u SET u.password=:pass WHERE u.email =:email";
            query = session.createQuery(hql);
            query.setParameter("email", email);
            query.setParameter("pass", password);
            quryResult = query.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (quryResult != 0) {
            return "success";
        } else {
            return "failed";
        }

    }

    //this method retrieves all the messages ,comments,
    @RequestMapping(value = "/user/dashboard", method = RequestMethod.GET)
    public String userDashboard() {
        return "user_dashboard";
    }

    @RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
    public String adminDashBoard() {
        return "admin_dashbord";
    }

    @RequestMapping(value = "/admin/adminPage", method = RequestMethod.GET)
    public String adminPage() {
        return "adminPage";
    }

    @RequestMapping(value = "/admin/addmovie", method = RequestMethod.GET)
    public String addMovie() {
        return "addMovie";
    }

    @RequestMapping(value = "/success")
    public String success() {
        return "Success";
    }

    @RequestMapping(value = "/rating")
    public String ratingPage() {
        return "Rating";
    }

}
