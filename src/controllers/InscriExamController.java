/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Client;
import entities.Examen;
import entities.InscriEvent;
import entities.InscripExam;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
import services.InscriEventS;
import services.InscriptionExService;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class InscriExamController implements Initializable {

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
    private Text titre_exam;
    @FXML
    private TextField date_passage_ex;

     public Client c=new Client();
     public Examen ex= new Examen();
    public void setInfoExam(Examen exam){
        this.ex=exam;
        date_passage_ex.setText(toString().valueOf(this.ex.getDate()));
        titre_exam.setText(this.ex.getTitreE());
       
    }
    public void setInfoUser(){
        this.idClient.setText(String.valueOf(this.c.getId()));
        this.nomClient.setText(this.c.getNom());
        this.prenomClient.setText(this.c.getPrenom());
        this.adresseM.setText(this.c.getEmail());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sinscrire(ActionEvent event) throws MessagingException {
        // envoyer mail et ajouter dans la table bd
          Properties prop = System.getProperties();
        prop.put("mail.smtp.port", "587");
         prop.put("mail.smtp.auth", true);
         prop.put("mail.smtp.starttls.enable", "true");
        Session newSession = Session.getDefaultInstance(prop, null);

        String emailsubject="UPGRADI inscription valideé";
//        String emailbody="Bienvenue Mr/Mme "+nomClient.getText()+" "+prenomClient.getText()+  
//                ", votre demande d'inscription a été acceptée pour l'event "+nomEvent.getText()+"  "+
//               "l'event sera le "+this.date+ " à "+this.heure+" H";
        String emailbody="Bienvenue Mr/Mme "+this.c.getNom()+" "+this.c.getPrenom()+" votre inscription est validé pour le "
                + " examen intitulé  "+this.ex.getTitreE()+".\n"+
                " votre examen sera le "+toString().valueOf(this.ex.getDate())
                         +" vous trouverez votre lien d'examen ci-dessus "+this.ex.getSupport()+
                " bonne chance \n";
        Message message = new MimeMessage(newSession);
        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(adresseM.getText()));
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
           
           /// bloc ajout dans db
               //ajout d'une inscri
        InscripExam ie=new InscripExam();
        ie.setIdClient(Integer.parseInt(idClient.getText()));
        ie.setIdExam(this.ex.getIdE());
        ie.setNom(this.c.getNom());
        ie.setPrenom(this.c.getPrenom());
        ie.setEmail(this.c.getEmail());
        InscriptionExService ies= InscriptionExService.getInstance();
        ies.insert(ie);
           
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("mail envoyée à "+adresseM.getText()+" avec succés! Bravo");
            alert.show();
            /////////////////////////
    }

    @FXML
    private void annuller(ActionEvent event) {
        Stage s=(Stage)btn_annuler.getScene().getWindow();
        s.close();
    }
    
}
