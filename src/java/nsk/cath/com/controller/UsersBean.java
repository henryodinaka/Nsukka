package nsk.cath.com.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import nsk.cath.com.dbQueries.UserQueries;
import nsk.cath.com.exception.HandlingExeption;
import nsk.cath.com.model.Users;
import nsk.cath.com.services.UserService;
import nsk.cath.com.utility.SessionUtils;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author LEOGOLD
 */
@Named("userBean")
@RequestScoped
public class UsersBean {

    private String signUpId;
    private String title;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String homeParish;
    private String homeAddress;
    private String homeTown;
    private String residenceParish;
    private String residenceAddress;
    private String occupation;
    private String qualification;
    private Date dateOfBirth;
    private String status; 
    private String email;
    private String username;
    private String password;
    private String password2;
    private int role;

    private Date created;
    private Date updated;
    
    private UploadedFile file;
    private String message;

    private String loginBtn = "Login";
    private String signBtn = "Sign Up";
    private String logName;
    private String search;
    private String serviceQueryReport;
    private String accountInfoReport; 
    private Users user;
    private List<Users> allUser;

    @Autowired
    private UserService userService;

    public UsersBean() {
    }

    public UsersBean(String adminId, String password) {
        this.username = adminId;
        this.password = password;
    }

    

    public String save() {
        serviceQueryReport = userService.save();
        if (serviceQueryReport.equals("login")) {
            return "login?faces-redirect=true";
        } else {
            setMessage("All fields must be filled");
            return "user_form";
        }
    }

    public String userList() {
        allUser = userService.viewUser();
        System.out.println(allUser);
        if (serviceQueryReport.equals("success")) {
            return "user_list?faces-redirect=true";
        } else {
            return null;
        }
    }

    public String loginControlBtn() {
        serviceQueryReport = userService.loginControlBtn();
        if (serviceQueryReport.equals("index")) {
            return "index?faces-redirect=true";
        } else {
            return "login?faces-redirect=true";
        }
    }

    public String regBtn() {
        setMessage(null);
        return "user_form?faces-redirect=true";
    }

    public String login() throws SQLException {
        serviceQueryReport = userService.login();

        switch (serviceQueryReport) {
            case "admin":
                return "admin_dashboard?faces-redirect=true";

            case "user":
                return "user_dashboard?faces-redirectq";

            case "login":
                return "login?faces-redirect=true";
//                
//            case "blocked":
//                return "error_page?faces-redirect=true";
//
//            case "inactive":
//                return "error_page?faces-redirectq";
//
//            case "issue":
//                return "error_page?faces-redirect=true";

            default:
                return "error_page?faces-redirect=true";
        }
    }

    public String loggedUser() {
        serviceQueryReport = userService.loggedUser();

        switch (serviceQueryReport) {
            case "admin":
                return "admin_dashboard?faces-redirect=true";

            case "user":
                return "user_dashboard?faces-redirectq";

            default:
                return "user_dashboard?faces-redirect=true";
        }
    }

    public String logout() {
        serviceQueryReport = userService.loginControlBtn();
        if (serviceQueryReport.equals("login")) {
            return "login";
        } else {
            return "login";
        }
    }

    public String updateLoggedUser() {
        serviceQueryReport = userService.updateLoggedUser();

        if (serviceQueryReport.equals("success")) {
            return "user_dashboard?faces-redirect=true";
        } else {
            return "update_page?faces-redirect=true";
        }
    }

    public String delete() {
        serviceQueryReport = userService.delete();

        if (serviceQueryReport.equals("success")) {
            userList();
            return "user_list?faces-redirect=true";
        } else {
            return "user_detail?faces-redirect=true";
        }
    }

    public String blockUser() {
        serviceQueryReport = userService.blockUser();
        if (serviceQueryReport.equals("success")) {
            return "user_detail?faces-redirect=true";
        } else {
            return null;
        }
    }

    public String moreDetails(String username) {
        serviceQueryReport = userService.moreDetails(username);
        return "user_detail?faces-redirect=true";

    }

    public String setAdminRole() {
        serviceQueryReport = userService.setAdminRole();
        if (serviceQueryReport.equals("success")) {
            return "user_detail?faces-redirect=true";
        } else {
            return null;
        }
    }

    public String resetPassword() {
        serviceQueryReport = userService.resetPassword();
        if (serviceQueryReport.equals("success") && message.equals("userLogged")) {
            return "user_dashboard?faces-redirect=true";
        } else if (serviceQueryReport.equals("success") && message.equals("notLogged")) {
            return "login?faces-redirect=true";
        } else {
            return "reset_password?faces-redirect=true";
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getLoginBtn() {
        return loginBtn;
    }

    public void setLoginBtn(String loginBtn) {
        this.loginBtn = loginBtn;
    }

    public String getSignBtn() {
        return signBtn;
    }

    public void setSignBtn(String signBtn) {
        this.signBtn = signBtn;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getServiceQueryReport() {
        return serviceQueryReport;
    }

    public void setServiceQueryReport(String serviceQueryReport) {
        this.serviceQueryReport = serviceQueryReport;
    }

    public List<Users> getAllUser() {
        return allUser;
    }

    public void setAllUser(List<Users> allUser) {
        this.allUser = allUser;
    }

    public String getAccountInfoReport() {
        return accountInfoReport;
    }

    public void setAccountInfoReport(String accountInfoReport) {
        this.accountInfoReport = accountInfoReport;
    }

    public String getSignUpId() {
        return signUpId;
    }

    public void setSignUpId(String signUpId) {
        this.signUpId = signUpId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHomeParish() {
        return homeParish;
    }

    public void setHomeParish(String homeParish) {
        this.homeParish = homeParish;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getResidenceParish() {
        return residenceParish;
    }

    public void setResidenceParish(String residenceParish) {
        this.residenceParish = residenceParish;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

}
