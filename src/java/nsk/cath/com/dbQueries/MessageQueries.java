/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsk.cath.com.dbQueries;

import nsk.cath.com.model.Message;
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
public class MessageQueries {
          
  @Autowired
  private SessionFactory sessionFactory;
  Session session;
  
    public void save(Message fb){
        session = sessionFactory.getCurrentSession();
        session.save(fb);
    }
    
    public void  update(Message fb){
       session = sessionFactory.getCurrentSession(); 
    }
    
    public void delete(Message fb){
        session = sessionFactory.getCurrentSession();
    }
    
    public void delete(Integer fbId){
        session = sessionFactory.getCurrentSession();
    }
    
    public void findById(Integer fbId){
       session = sessionFactory.getCurrentSession(); 
    }
    public void findByProperty(String prop, Object obj){
       session = sessionFactory.getCurrentSession(); 
    }
}
