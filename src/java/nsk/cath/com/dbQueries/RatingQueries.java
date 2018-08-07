/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsk.cath.com.dbQueries;

import nsk.cath.com.model.Rating;
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
public class RatingQueries {
             
  @Autowired
  private SessionFactory sessionFactory;
  Session session;
    public void save(Rating rating){
        session = sessionFactory.getCurrentSession();
        session.save(rating);
    }
    
    public void delete(Rating ratin){
        session = sessionFactory.getCurrentSession();
    }
    
}
