/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Client;
import entities.Commentaire;
import entities.InscriEvent;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import services.CommentaireS;
import services.InscriEventS;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class InscriptionEController implements Initializable {

    @FXML
    private TextField nomClient;
    @FXML
    private TextField prenomClient;
    @FXML
    private TextField adresseM;
    @FXML
    private TextField idClient;
    @FXML
    private Button btn_inscription;
    @FXML
    private Button btn_annuler;
    @FXML
    private ImageView imageEvent;
    @FXML
    private Text nomEvent;
    private int idEvent;
    private int heure;
    private String date;
   
     
    public Client c=new Client();
    public void setNameImageEvent(String name,Image image,int idE,String date,int heure){
        this.imageEvent.setImage(image);
        this.nomEvent.setText(name);
        this.idEvent=idE;
        this.date=date;
        this.heure=heure;
       
    }
    public void setInfoUser(){
        this.idClient.setText(String.valueOf(this.c.getId()));
        this.nomClient.setText(this.c.getNom());
        this.prenomClient.setText(this.c.getPrenom());
        this.adresseM.setText(this.c.getEmail());
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void sinscrire(ActionEvent event) throws MessagingException {
        // mailing 
        Properties prop = System.getProperties();
        prop.put("mail.smtp.port", "587");
         prop.put("mail.smtp.auth", true);
         prop.put("mail.smtp.starttls.enable", "true");
        Session newSession = Session.getDefaultInstance(prop, null);

        String emailsubject="UPGRADI inscription valideé";
        String emailbody="Bienvenue Mr/Mme "+nomClient.getText()+" "+prenomClient.getText()+  
                ", votre demande d'inscription a été acceptée pour l'event "+nomEvent.getText()+"  "+
               "l'event sera le "+this.date+ " à "+this.heure+" H";
        Message message = new MimeMessage(newSession);
        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(adresseM.getText()));
        } catch (AddressException ex) {
           //Logger.getLogger(InscriptionEController.class.getName()).log(Level.SEVERE, null, ex);
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("adresse mail non valide");
        alert.show();
        }

         message.setSubject(emailsubject);

         MimeBodyPart mimeBodyPart = new MimeBodyPart();
         mimeBodyPart.setContent(emailbody, "text/html");

          Multipart multipart = new MimeMultipart();
          multipart.addBodyPart(mimeBodyPart);


          message.setContent(multipart);

           String fromuser ="hamdiskander5@gmail.com";
           String pass ="skanderhamdi200998*";
           String emailhost="smtp.gmail.com";
           Transport transport =newSession.getTransport("smtp");
           transport.connect(emailhost,fromuser,pass);
           transport.sendMessage( message, message.getAllRecipients());
           transport.close();
           
           //ajout d'une inscri
        InscriEvent ie=new InscriEvent();
        ie.setIdClient(Integer.parseInt(idClient.getText()));
        ie.setIdEvent(this.idEvent);
        InscriEventS ies= InscriEventS.getInscriEvent();
        ies.insert(ie);
       
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Inscription valide check your Mail");
        alert.show();
    }

    @FXML
    private void annuller(ActionEvent event) {
        Stage s=(Stage)btn_annuler.getScene().getWindow();
        s.close();
    }
    
}
