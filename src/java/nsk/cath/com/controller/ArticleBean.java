package nsk.cath.com.controller;

import java.util.Date;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import nsk.cath.com.model.Comment;
import nsk.cath.com.model.ImageFile;
import nsk.cath.com.model.Articles;
import nsk.cath.com.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author LEOGOLD
 */
@Named("articleBean")
@RequestScoped
public class ArticleBean {

    private int articleId;
    private String title;
    private String content;
    private String report;
    private String logo;
    private double rate;
    private List<Comment> commentId;
    private List<ImageFile> fileId;
    private Date created;
    private Date updated;

    @Autowired
    private ArticleService articleService;
    private List<Articles> articleList;

    public ArticleBean() {
    }

    public ArticleBean(int articleId, String title, String content) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
    }

//This method calls the service class that actully save the data to the DB
    public void save() {
        Articles article = new Articles(articleId, title, content, logo);
        articleService.save(article);
//        return "upload_page";
    }

    public String articleList() {
        articleList = articleService.viewMovie();
        if (report.equals("success")) {
            return "article_list?faces-redirect=true";
        } else {
            return null;
        }
    }

    public String update() {
        Articles article = new Articles(title, content);
        article.setArticleId(articleId);
        articleService.update(article);
        return "adminPage";
    }
//    public List<Movie> searchMovieById(ValueChangeEvent vce){
//        String id = vce.getNewValue().toString();
//        return articleService.findById(id);
//         
//    }

    public String delete() {
        report = articleService.delete();
        if (report.equals("success")) {
            articleList();
            return "article_list?faces-redirect=true";
        } else {
            return "article_detail?faces-redirect=true";
        }
    }

    public String moreDetails(String articleId) {
        report = articleService.moreDetails(articleId);
        return "article_detail?faces-redirect=true";

    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public List<Articles> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Articles> articleList) {
        this.articleList = articleList;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public List<Comment> getCommentId() {
        return commentId;
    }

    public void setCommentId(List<Comment> commentId) {
        this.commentId = commentId;
    }

    public List<ImageFile> getFileId() {
        return fileId;
    }

    public void setFileId(List<ImageFile> fileId) {
        this.fileId = fileId;
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

}
