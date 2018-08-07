package nsk.cath.com.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp; 

/**
 *
 * @author LEOGOLD
 */
@Entity
@Table (name = "Comment")
public class Comment implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_Id")
    private int Id;
    
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Articles.class)
    @JoinColumn(name ="articleId",nullable=false,foreignKey = @ForeignKey(name="FK_Comment_Movie"))  
    private Articles articleId;
    
    @ManyToOne (fetch = FetchType.LAZY,targetEntity = Users.class)
    @JoinColumn(name = "user_Id", nullable=false,foreignKey = @ForeignKey(name="FK_Comment_Users"))
    private Users userId;
    
    @Column (name = "comment")
    private String comment ;

    @CreationTimestamp 
    @Column (name = "date_created")
    private Date created;

    public Comment() {
    }

    public Comment(Articles movieId, Users userId, String comment, Date commentDate) {
        this.articleId = movieId;
        this.userId = userId;
        this.comment = comment;
        this.created = commentDate;
    }

    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Articles getArticleId() {
        return articleId;
    }

    public void setArticleId(Articles articleId) {
        this.articleId = articleId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Comment{" + "Id=" + Id + ", movieId=" + articleId + ", userId=" + userId + ", comment=" + comment + ", commentDate=" + created + '}';
    }
     
   
}
