package nsk.cath.com.dbQueries;

import java.util.Iterator;
import java.util.List;
import nsk.cath.com.exception.HandlingExeption;
import nsk.cath.com.controller.UsersBean;
import nsk.cath.com.model.Users;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LEOGOLD
 */
@Service
@Transactional
public class UserQueries {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;
    private String hql;
    private Query query;

}
