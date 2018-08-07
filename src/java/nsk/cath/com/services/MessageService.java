/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsk.cath.com.services;

import nsk.cath.com.controller.MessageBean;
import nsk.cath.com.controller.UsersBean;
import nsk.cath.com.model.Message;
import nsk.cath.com.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LEOGOLD
 */
@Service
@Transactional
public class MessageService {

    @Autowired
    private SessionFactory sessionFactory;
    Session session;
    
    @Autowired
    private MessageBean messageBean;
    Message message;
    @Autowired
    UsersBean userBean;

    public void save() {
        session = sessionFactory.getCurrentSession();
        
        message = new Message(messageBean.getFullName(),messageBean.getEmail(),messageBean.getSubject(), messageBean.getBody());
        session.save(message);
        userBean.setMessage("Your Message has been sent, the issue will be resolved in not more than 12hrs");
    }

    public void update(Message massage) {
        session = sessionFactory.getCurrentSession();
    }

    public void delete(Message massage) {
        session = sessionFactory.getCurrentSession();
    }

    public void delete(Integer massageId) {
        session = sessionFactory.getCurrentSession();
    }

    public void findById(Integer massageId) {
        session = sessionFactory.getCurrentSession();
    }

    public void findByProperty(String prop, Object obj) {
        session = sessionFactory.getCurrentSession();
    }
}
