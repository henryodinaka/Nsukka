/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsk.cath.com.controller;

import java.util.Date;
import javax.inject.Named;
import javax.faces.bean.RequestScoped;
import nsk.cath.com.dbQueries.CommentQueries;
import nsk.cath.com.model.Comment;
import nsk.cath.com.model.Articles;
import nsk.cath.com.model.Users;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author LEOGOLD
 */
@Named(value = "commentBean")
@RequestScoped

public class CommentBean {

    private String userId;
    private int articleId;
    private String comment;
    private Date commentDate;

    @Autowired
    private CommentQueries commentService;
    
    public CommentBean() {
    }

    public CommentBean(String userId, int articleId, String comment, CommentQueries commentDto) {
        this.userId = userId;
        this.articleId = articleId;
        this.comment = comment;
        this.commentService = commentDto;
    }

    public String save(){
        Users user = new Users();
        Articles article = new Articles();
        user.setUsername(userId);
        article.setArticleId(articleId);
        Comment com = new Comment(article, user, comment, commentDate);
        
        commentService.save(com);
        return "Success";
    }
    
    public String articleComment(){
        commentService.findById(articleId);
        return "adminPage";
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
}
