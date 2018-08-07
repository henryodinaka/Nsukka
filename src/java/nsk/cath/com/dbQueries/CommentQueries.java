package nsk.cath.com.dbQueries;

import static javassist.CtMethod.ConstParameter.string;
import nsk.cath.com.model.Comment;
import org.hibernate.Query;
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
public class CommentQueries {
        
  @Autowired
  private SessionFactory sessionFactory;
  Session session;    
    public void save(Comment comment){
        session = sessionFactory.getCurrentSession();
        session.save(comment);
    }
    
    public void  retrive(Comment comment){
        session = sessionFactory.getCurrentSession();
    }
    
    public void delete(Comment comment){
        session = sessionFactory.getCurrentSession();
    }
    
    public void delete(Integer commentId){
        session = sessionFactory.getCurrentSession();
    }
    
    public Long findById(int articleId){
        session = sessionFactory.getCurrentSession();
        
        Query query = session.createQuery("SELECT COUNT(*) FROM Comment com WHERE com.articleId =:articleId");
        query.setParameter("articleId", articleId);
        
        Long count = (Long)query.uniqueResult();
        return count;
    }
    public void findByProperty(String prop, Object obj){
        session = sessionFactory.getCurrentSession();
    }
 
}
