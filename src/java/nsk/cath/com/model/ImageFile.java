package nsk.cath.com.model;

import java.sql.Blob;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
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
@Table(name = "fileUpload")
public class ImageFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_Id")
    private int fileId;

    private String username;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file")
    private Blob file;

    @CreationTimestamp 
    @Column(name = "date_created")
    private Date created;

    @UpdateTimestamp 
    @Column(name = "last_modified")
    private Date updated;

    public ImageFile() {
    }

    public ImageFile(String ownerId, String fileName, Blob file) {
        this.username = ownerId;
        this.fileName = fileName;
        this.file = file;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getOwnerId() {
        return username;
    }

    public void setOwnerId(String ownerId) {
        this.username = ownerId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Blob getFile() {
        return file;
    }

    public void setFile(Blob file) {
        this.file = file;
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

    @Override
    public String toString() {
        return "FileUpload{" + "fileId=" + fileId + ", ownerId=" + username + ", fileName=" + fileName + ", created=" + created + ", updated=" + updated + '}';
    }

}
