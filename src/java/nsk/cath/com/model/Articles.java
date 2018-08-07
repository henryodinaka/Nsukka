package nsk.cath.com.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany; 
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author LEOGOLD
 */
@Entity
@Table (name = "Articles")
public class Articles implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "articleId", nullable = false,unique = true)
    private int articleId ;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "content",nullable = false)
    private String content;
    
    @OneToMany(mappedBy ="articleId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Rating.class)
    private List<Rating> rateId ;
    
    @OneToMany(targetEntity=Comment.class,
            mappedBy="articleId", cascade=CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Comment> commentId;
    
    @CreationTimestamp 
    @Column (name = "date_created")
    private Date created;
     
    @UpdateTimestamp 
    @Column (name = "last_modified")
    private Date updated;
    
    @Column (name = "movie_logo")
    private String logo;
    
    public Articles() {
    }

    public Articles(int articleId, String title, String content, String logo) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;  
        this.logo = logo;
    }

    public Articles(String movieTitle, String movieDes) {
        this.title = movieTitle;
        this.content = movieDes; 
    }


    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Rating> getRateId() {
        return rateId;
    }

    public void setRateId(List<Rating> rateId) {
        this.rateId = rateId;
    }

    public List<Comment> getCommentId() {
        return commentId;
    }

    public void setCommentId(List<Comment> commentId) {
        this.commentId = commentId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Articles{" + "articleId=" + articleId + ", title=" + title + ", content=" + content + ", rateId=" + rateId + ", commentId=" + commentId + ", created=" + created + ", updated=" + updated + ", logo=" + logo + '}';
    }
    
}
