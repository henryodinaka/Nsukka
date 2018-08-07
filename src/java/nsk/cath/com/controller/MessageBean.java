/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsk.cath.com.controller;


import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import nsk.cath.com.dbQueries.MessageQueries;
import nsk.cath.com.model.Message;
import nsk.cath.com.model.Users;
import nsk.cath.com.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author LEOGOLD
 */
@Named(value = "messageBean")
@RequestScoped
public class MessageBean {
   
    private String fullName;
    private String email;
    private String subject;
    private String body;

    @Autowired
    private MessageService messageService;
    
    public MessageBean() {
    }

    public MessageBean(String email, String subject, String message) {
        this.email = email;
        this.subject = subject;
        this.body = message;
    }

    public String save(){
        messageService.save();
        
        return "error_page?faces-redirect=true";
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    
}
