/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsk.cath.com.services;

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
public class AdminService {
    
  @Autowired
  private SessionFactory sessionFactory;
  
  public void saveAdmin(Users admin){
      final Session session = sessionFactory.getCurrentSession();
      session.save(admin);
  }
}
