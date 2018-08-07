package nsk.cath.com.services;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import nsk.cath.com.controller.FileUploadBean;
import nsk.cath.com.model.ImageFile;
import nsk.cath.com.model.Users;
import nsk.cath.com.utility.SessionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LEOGOLD
 */
@Service
@Transactional
public class FileUploadService {

    @Autowired
    private   SessionFactory sessionFactory;
    
    @Autowired
      FileUploadBean fileBean;
    
      ImageFile image;
    private   Session session;
      HttpSession httpsession;
      Users user;
      Query query;

    private   byte[] imageByte;
    private   Blob imageBlob;
private StreamedContent dbImage;
    public void uploadFile() throws SQLException {

        httpsession = SessionUtils.getSession();
        user = (Users)httpsession.getAttribute("loggedUser");

        session = sessionFactory.getCurrentSession();
        try{
            
            System.out.println("From service class before execute");
        imageByte = fileBean.getFile().getContents();
        imageBlob = new SerialBlob(imageByte);
        image = new ImageFile(user.getUsername(), fileBean.getFile().getFileName(), imageBlob);
        session.save(image);
            
            System.out.println("From Service class after execute");
        FacesMessage message = new FacesMessage("Succesful", fileBean.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        //imageView();
        }catch(SQLException s){
           
        FacesMessage message = new FacesMessage("Failed", fileBean.getFile().getFileName() + " Failed to upload");
        FacesContext.getCurrentInstance().addMessage(null, message); 
        }
    }
    public   ImageFile image(){
        httpsession = SessionUtils.getSession();
        user = (Users)httpsession.getAttribute("loggedUser");
        session = sessionFactory.getCurrentSession();
        query = session.createQuery("FROM ImageFile img WHERE img.username=:username");
        query.setParameter("username",user.getUsername() );
        image  = (ImageFile)query.uniqueResult();
        return image;
    }
    public   void imageView() throws SQLException{ 
        image = image();
        System.out.println("the image name is "+ image.getFileName());
        imageBlob = image.getFile();
        InputStream imageStream = imageBlob.getBinaryStream();
        dbImage= new DefaultStreamedContent(imageStream,"image/jpg");
    }

    public StreamedContent getDbImage() {
        return dbImage;
    }
    
}
