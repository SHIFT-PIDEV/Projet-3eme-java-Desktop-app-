/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.packge;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import org.apache.commons.lang3.RandomStringUtils;
import org.controlsfx.control.Notifications;
import services.packageservice;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class CreatpackageController implements Initializable {

    @FXML
    private TextField nompackage;
    @FXML
    private TextField nbcours;
    @FXML
    private TextField prix;
    @FXML
    private TextArea description;
    @FXML
    private Button btn_createpackage;
    @FXML
    private ComboBox<String> categorie;
    @FXML
    private TextField duration;
    @FXML
    private ImageView imgview;
    @FXML
    private Button btn_add_img;
    @FXML
    private Label babel_catsaisie;
    @FXML
    private Label label_nivsaisie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             String dbUsername = "root";
        String dbPassword = "";
        String dbURL = "jdbc:mysql://127.0.0.1/upgradi";
        try {
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
          
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT  nom_cat FROM categorie");
            while (rs.next()) {
                //get string from db,whichever way 
                  categorie.getItems().addAll(rs.getString("nom_cat"));
            }

        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
    }    

    @FXML
    private void createpackage(ActionEvent event) throws SQLException, IOException, MessagingException {
       
         String a="";
         

        if  ( nompackage.getText().isEmpty()||categorie.getValue()==null || description.getText().isEmpty()||prix.getText().isEmpty()||duration.getText().isEmpty() || nbcours.getText().isEmpty() ){
            if (nompackage.getText().isEmpty()) 
               nompackage.setStyle("-fx-border-color:red;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
            
            else 
         nompackage.setStyle("-fx-border-color: #0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
            if (categorie.getValue()==null)
                a+="  NB! veuillez saisir votre categorie\n";
         /*   else 
              s+= "categorie saisie";*/
            if (nbcours.getText().isEmpty())
          nbcours.setStyle("-fx-border-color:red;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
            else 
               nbcours.setStyle("-fx-border-color: #0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
            if (description.getText().isEmpty())
                description.setStyle("-fx-border-color:red;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
            else 
                 description.setStyle("-fx-border-color: #0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
            if (prix.getText().isEmpty())
                     prix.setStyle("-fx-border-color:red;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
           else 
                      prix.setStyle("-fx-border-color: #0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
           
            if (duration.getText().isEmpty())
                        duration.setStyle("-fx-border-color:red;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
          else 
              duration.setStyle("-fx-border-color: #0598ff;-fx-border-width:0px 0px 2px 0px;-fx-background-color:transparent;");
            if (!a.isEmpty())
            {
                
               babel_catsaisie.setText(a);
            }
                
                        
        }
        else
        {
         Image image1=null;
             image1= imgview.getImage();
             String photo = null;
             
             photo = saveToFileImageNormal(image1);
             System.out.println(photo);
        packge c;
       c = new packge ( nompackage.getText(), Float.parseFloat(prix.getText()),duration.getText(),description.getText(),(String) categorie.getSelectionModel().getSelectedItem(),photo,Integer.parseInt(nbcours.getText()));
            packageservice cserv= packageservice.getInstance();
            cserv.insert(c);
           // this.sendMail();
             Notifications.create()
                 .title("AJOUT Package")
                 .text("package  est ajouté avec success")
                 .darkStyle().position(Pos.BOTTOM_RIGHT).showConfirm();
     nompackage.setText("");
        prix.setText("");
        duration.setText("");
        description.setText("");
        nbcours.setText("");
        imgview.setImage(image1);
        
        }
    }
    

    @FXML
    private void add_image(ActionEvent event) {
           FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
              WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
             imgview.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
       public static String saveToFileImageNormal(Image image)throws SQLException, IOException  {

        String ext = "jpg";
        File dir = new File("D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/image");
        String  name;
        name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
     BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;  
    }
     /*    public void sendMail() throws MessagingException {
         Properties prop = System.getProperties();
        prop.put("mail.smtp.port", "587");
         prop.put("mail.smtp.auth", true);
         prop.put("mail.smtp.starttls.enable", "true");
         prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session newSession = Session.getDefaultInstance(prop, null);

        String emailsubject="UPGRADI inscription valideé";
        String emailbody="Bienvenue Mr/Mme ";
        Message message = new MimeMessage(newSession);
        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("yasmine.chelbi@esprit.tn"));
        } catch (AddressException ex) {
           // Logger.getLogger(InscriptionEController.class.getName()).log(Level.SEVERE, null, ex);
        }

         message.setSubject(emailsubject);

         MimeBodyPart mimeBodyPart = new MimeBodyPart();
         mimeBodyPart.setContent(emailbody, "text/html");

          Multipart multipart = new MimeMultipart();
          multipart.addBodyPart(mimeBodyPart);


          message.setContent(multipart);

           String fromuser ="space.upgradi@gmail.com";
           String pass ="upgradi123456789";
           String emailhost="smtp.gmail.com";
           Transport transport =newSession.getTransport("smtp");
           transport.connect(emailhost,fromuser,pass);
           transport.sendMessage( message, message.getAllRecipients());
           transport.close();
       
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                   alert.setHeaderText(null);
                   alert.setContentText("MAIL modifié avec succés!");
                   alert.show();
    
    
}*/
}
