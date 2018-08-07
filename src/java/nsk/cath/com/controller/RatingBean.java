/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsk.cath.com.controller;

import javax.inject.Named;
import javax.faces.bean.RequestScoped;
import nsk.cath.com.dbQueries.RatingQueries;
import nsk.cath.com.model.Articles;
import nsk.cath.com.model.Rating;
import nsk.cath.com.model.Users;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author LEOGOLD
 */
@Named(value = "ratingBean")
@RequestScoped
public class RatingBean {

    private String userId;
    private int articleId;
    private int star;
    
    @Autowired
    private RatingQueries ratingService;
    Articles article;
    Rating rating;
    
    public RatingBean() {
    }

    public RatingBean(String userId, int articleId, int star) {
        this.userId = userId;
        this.articleId = articleId;
        this.star = star;
        this.ratingService = ratingService;
    }

    public String save(){
        Users user = new Users();
        article = new Articles();
        user.setUsername(userId);
        article.setArticleId(articleId);
        rating = new  Rating(article, star);
        ratingService.save(rating);
        return "success";
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
    
}
