package nsk.cath.com.controller;

import java.sql.SQLException;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import nsk.cath.com.services.FileUploadService;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author LEOGOLD
 */
@Named(value = "fileBean")
@ApplicationScoped
public class FileUploadBean {
    private String fileName;
    private String fileSize;
    private UploadedFile file ;
    
    @Autowired
    FileUploadService fileUploadService;
    
    private StreamedContent dbImage;

    public FileUploadBean() {
    }

    public String uploadFile() throws SQLException{
        fileUploadService.imageView();
        dbImage = fileUploadService.getDbImage();
//        if(file != null){
//            System.out.println("From bean class before execution");
//        fileUploadService.uploadFile();
//            System.out.println("From bean class after execution");
//        
//        }
        return "upload_file";
    }
    public void viewImage(){
        fileUploadService.image();
    }
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public FileUploadService getFileUploadService() {
        return fileUploadService;
    }

    public void setFileUploadService(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    public StreamedContent getDbImage() {
        return dbImage;
    }

    public void setDbImage(StreamedContent dbImage) {
        this.dbImage = dbImage;
    }
    
    
    
}
