package nsk.cath.com.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Blob;
import java.sql.SQLException;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import nsk.cath.com.services.FileUploadService;
import nsk.cath.com.model.ImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author LEOGOLD
 */
@Named(value = "file")
@ViewScoped
public class FileBean {

    private static final String UPLOAD_DIRECTORY = "/fileUploaded";
    private String fileName;
    private String movieId;

    @Autowired
    private FileUploadService fileDto;
    private ImageFile fileUpload;
    private Part file;
    private Path path;
    private byte[] fileData;
    Blob fileBlob;

    public FileBean() {
    }

    public void upload() throws IOException, SQLException {
        System.out.println(file.getInputStream());
//        ImageFile convFile = new ImageFile(file.getOriginalFilename());
//        convFile.createNewFile();
//        FileOutputStream fos = new FileOutputStream(convFile);
//        fos.write(file.getBytes());
//        fos.close();
//        fileData = file.getBytes();
//        fileName = file.getName();
//        fileBlob = new SerialBlob(fileData);
//        Movie movie = new Movie();
//        movie.setMovieId(movieId);
//        fileUpload = new ImageFile(movie, fileName, fileBlob);
//        fileDto.uploadFile(fileUpload);
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public ImageFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(ImageFile fileUpload) {
        this.fileUpload = fileUpload;
    }
}
