/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsk.cath.com.services;

import java.util.List; 
import javax.faces.event.ValueChangeEvent; 
import javax.servlet.http.HttpSession;
import nsk.cath.com.controller.ArticleBean;
import nsk.cath.com.model.Articles;
import nsk.cath.com.utility.SessionUtils;
import nsk.cath.com.utility.StringManipulation;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author LEOGOLD
 */
@Service
@Transactional 
public class ArticleService {

    @Autowired
    private ArticleBean articleBean;
    private List<Articles> articleList;
    private Articles article;

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    HttpSession httpSession;

    public List<Articles> viewMovie() {

        try {

            session = sessionFactory.getCurrentSession();
            String sql = "SELECT * FROM Articles";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Articles.class);
            articleList = query.list();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (articleList != null) {
            httpSession = SessionUtils.getSession();
            httpSession.setAttribute("articleList", articleList);
            articleBean.setReport("success");

            return articleList;
        } else {
            articleBean.setReport("failed");
            return null;
        }
    }

    @RequestMapping(value = "/admin/addmovie")
    public String save(Articles article) {
        session = sessionFactory.getCurrentSession();
        session.save(article);
        return "upload_page";
    }

    public String update(Articles editArticle) {
        editArticle.setArticleId(articleBean.getArticleId());
        // String sql = "UPDATE Article SET title =:title, content =:content WHERE articleId =:articleId";

        session = sessionFactory.getCurrentSession();
        article = (Articles) session.get(Articles.class, editArticle.getArticleId());
        article.setTitle(editArticle.getTitle());
        article.setContent(editArticle.getContent());
        article.setUpdated(editArticle.getCreated());
        session.update(article);
        return "adminPage";
    }

    public List<Articles> searchMovieById(ValueChangeEvent vce) {
        String articleId = vce.getNewValue().toString();
        
        try {

            session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("SELECT * FROM Article mov WHERE mov.articleId =:articleId");
            query.setString("articleId", articleId);
            articleList = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articleList;

    }

    public Articles findByMovieId(String articleId) {
        session = sessionFactory.getCurrentSession();
        try {
            session = sessionFactory.getCurrentSession();

            article = (Articles) session.createQuery("FROM Articles a "
                    + "WHERE a.articleId = :articleId")
                    .setParameter("articleId", articleId)
                    .uniqueResult();
            return article;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public String delete() {
        session = sessionFactory.getCurrentSession();
        Articles articleDelete = (Articles) httpSession.getAttribute("articleRead");
        article = (Articles) session.get(Articles.class, articleDelete.getArticleId());
        session.delete(article);
        httpSession.removeAttribute("articleList");
        return "success";
    }

    //this method will delete multiple movies at a go
    public void delete(String[] articleIds) {
        String ids = StringManipulation.commaSeprated(articleIds);
        String sql = "DELETE FROM Articles WHERE  articleId IN {" + ids + "}";

    }

    public String moreDetails(String articleId) {
        article = findByMovieId(articleId);
        if (article != null) {
            httpSession = SessionUtils.getSession();
            httpSession.setAttribute("articleRead", article);
            setArticleBean(article);
            return "user_detail?faces-redirect=true";
        } else {
            return "article_list";
        }
    }

//This  part queries the database
    public void findByProperty(String prop, Object obj) {
        session = sessionFactory.getCurrentSession();
    }

    private void setArticleBean(Articles movie) {
        articleBean.setArticleId(movie.getArticleId());
        articleBean.setTitle(movie.getTitle());
        articleBean.setContent(movie.getContent());
        articleBean.setCreated(movie.getCreated());
        articleBean.setUpdated(movie.getUpdated());
    }
}
