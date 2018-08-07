/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsk.cath.com.dbQueries;

import java.util.List;
import nsk.cath.com.model.Articles;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
public class MovieQueries {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    public void save(Articles movie) {
        session = sessionFactory.getCurrentSession();
        session.save(movie);
    }

    public List<Articles> retriveAll() {
        List<Articles> movie = null;

        try {

            session = sessionFactory.getCurrentSession();
            String sql = "SELECT * FROM MOVIE";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Articles.class);
            movie = query.list();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return movie;
    }

    public void update(Articles movie) {
        // String sql = "UPDATE MOVIE SET movieTitle =:movieTile, movieDes =:movieDes,lastModified =:lastModified WHERE movieId =:movieId";

        session = sessionFactory.getCurrentSession();
        Articles movieUpdate = (Articles) session.get(Articles.class, movie.getArticleId());
        movieUpdate.setTitle(movie.getTitle());
        movieUpdate.setContent(movie.getContent());
        movieUpdate.setUpdated(movie.getCreated());
        session.update(movieUpdate);
    }

    public void delete(Articles movie) {
         session = sessionFactory.getCurrentSession();
        Articles movieDelete = (Articles) session.get(Articles.class, movie.getArticleId());
        session.delete(movieDelete);

    }

    public void delete(Integer movieId) {
        session = sessionFactory.getCurrentSession();
    }

    public List<Articles> findById(String movieId) {
        List<Articles> movie = null;
        try {

            session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("SELECT * FROM Movie mov WHERE mov.movieId =:movieId");
            query.setString("movieId", movieId);
            movie = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return movie;
    }

    public void findByProperty(String prop, Object obj) {
         session = sessionFactory.getCurrentSession();
    }

}
