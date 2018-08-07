/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsk.cath.com.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author LEOGOLD
 */
@Entity
@Table (name = "Users",uniqueConstraints = { 
        @UniqueConstraint(columnNames = "email"), 
        @UniqueConstraint(columnNames = "username")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sn")
    private int sn;
    
    @Column(name = "username", nullable = false, unique = true)
    private String username ;
    
    @Column(name = "first_name",nullable = false)
    private String firstName;
    
    @Column (name = "last_name",nullable = false)
    private String lastName;
     
    @Column (name = "email",nullable = false,unique = true)
    private String email;
    
    @Column (name = "phone_number",nullable = false, unique = true)
    private String phone;
    
    @Column(name = "homeParish",nullable = false, length = 200)
    private String homeParish;

    @Column(name = "homeAddress",nullable = false, length = 100)
    private String homeAddress;

    @Column(name = "homeTown",nullable = false, length = 30)
    private String homeTown;

    @Column(name = "residentialParish",nullable = false, length = 100)
    private String residentialParish;

    @Column(name = "residentialAddress",nullable = false, length = 100)
    private String residentialAddress;

    @Column(name="occupation",nullable = false, length = 30)
    private String occupation;

    @Temporal(TemporalType.DATE)
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @Column(name = "gender", nullable = false)
    private String gender;
    
    @Column(name ="qualification",nullable = false)
    private String qualification;

    @Column (name = "password",nullable = false)
    private String password;
    
    @Column (name = "role",nullable = false) // Admin role is 1, user's role is 2
    private int role;
        
    @Column (name = "status",nullable = false, length = 3) //this defines user's status: blk = blocked, act = active , ina = inactive
    private String status;
        
    @OneToMany(targetEntity=Comment.class,
            mappedBy="userId", cascade=CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Comment> commentId;
 
    @CreationTimestamp 
    @Column (name = "date_created")
    private Date created;
     
    @UpdateTimestamp 
    @Column (name = "last_modified")
    private Date updated;
        
    public Users() {
    }

    public Users(String firstName, String lastName, String emailId, String phone, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = emailId;
        this.phone = phone;
        this.password = password;
    }

    public Users(String firstName, String lastName, String emailId, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = emailId;
        this.phone = phone;
    }

    public Users(String username, String firstName, String lastName, String emailId, String phone, String password, int role, String status) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = emailId;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public Users(String username, String firstName, String lastName, String email, String phone, String homeParish, String homeAddress, String homeTown, String residentialParish, String residentialAddress, String occupation, Date dateOfBirth, String gender, String qualification, String password, int role, String status) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.homeParish = homeParish;
        this.homeAddress = homeAddress;
        this.homeTown = homeTown;
        this.residentialParish = residentialParish;
        this.residentialAddress = residentialAddress;
        this.occupation = occupation;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.qualification = qualification;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public Users(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    } 
 
    public List<Comment> getCommentId() {
        return commentId;
    }

    public void setCommentId(List<Comment> commentId) {
        this.commentId = commentId;
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
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

    public String getResidentialParish() {
        return residentialParish;
    }

    public void setResidentialParish(String residentialParish) {
        this.residentialParish = residentialParish;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    

    @Override
    public String toString() {
        return "Users{" + "sn=" + sn + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + ", homeParish=" + homeParish + ", homeAddress=" + homeAddress + ", homeTown=" + homeTown + ", residentialParish=" + residentialParish + ", residentialAddress=" + residentialAddress + ", occupation=" + occupation + ", dateOfBirth=" + dateOfBirth + ", qualification=" + qualification + ", password=" + password + ", role=" + role + ", status=" + status + ", commentId=" + commentId + ",created=" + created + ", updated=" + updated + '}';
    }

    
    
    
}
