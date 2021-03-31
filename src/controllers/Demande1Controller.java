/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Demande;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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
import javax.swing.JFileChooser;
import services.DemandeDao;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class Demande1Controller implements Initializable {

    @FXML
    private Text iconUserDef;
    @FXML
    private HBox dashbord;
    @FXML
    private HBox eventsInEventView;
    @FXML
    private Text reclamation;
    @FXML
    private TextField searchBar;
    @FXML
    private Button devFormateur;
     private ListData2 listdata = new ListData2();
    private ObservableList<Demande> recla2=FXCollections.observableArrayList();
    @FXML
    private TableView<Demande> reclaTable;
    @FXML
    private TableColumn<Demande, Integer> idColonne;
    @FXML
    private TableColumn<Demande, String> objetColonne;
    @FXML
    private TableColumn<Demande, String> descriptionColonne;
    @FXML
     private TableColumn<Demande, String> cvColonne;
    @FXML
    private TextField filterField;
    @FXML
    private TextField idlab;
    @FXML
    private TextField objetlab;
    @FXML
    private TextArea objetdes;
    @FXML
    private TextField cvlab;
    @FXML
    private Button supprimer;
    @FXML
    private Button sms;
    @FXML
    private Button mail;
    @FXML
    private HBox HB_examen;
    @FXML
    private HBox HB_coupon;
    @FXML
    private HBox HB_deconnexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       reclaTable.setItems(listdata.getrecla2());
                
        idColonne.setCellValueFactory(cell -> cell.
                getValue().getIdProperty().asObject());
        
        objetColonne.setCellValueFactory(cell -> cell.
                getValue().getobjetProperty());
        descriptionColonne.setCellValueFactory(cell -> cell.
                getValue().getDescriptionProperty());
                 cvColonne.setCellValueFactory(cell -> cell.
                getValue().getCvProperty());
        reclaTable.setOnMouseClicked((MouseEvent event) -> {
            idlab.setText(String.valueOf(listdata.getrecla2()
                    .get(reclaTable.getSelectionModel().getSelectedIndex())
                    .getId()));
            objetlab.setText(listdata.getrecla2()
                    .get(reclaTable.getSelectionModel().getSelectedIndex())
                    .getObjet());
            objetdes.setText(listdata.getrecla2()
                    .get(reclaTable.getSelectionModel().getSelectedIndex())
                    .getDescription());
            cvlab.setText(listdata.getrecla2()
                    .get(reclaTable.getSelectionModel().getSelectedIndex())
                    .getCv());
         });
          supprimer.setOnAction((ActionEvent event) -> {
              int x=  Integer.parseInt(idlab.getText());
              DemandeDao pdao = DemandeDao.getInstance();
              Demande p;
              p = pdao.displayById(x);
              pdao.delete(p);
         });
          
           DemandeDao pdao2=DemandeDao.getInstance();
        recla2= pdao2.displayAll();
        
        FilteredList<Demande> filteredData = new FilteredList<>(recla2, b -> true);
         filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(demande -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
                                      System.out.println(newValue);

					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (demande.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (demande.getObjet().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                    
					return true; // Filter matches last name.
				}
                                
				else if (String.valueOf(demande.getId()).indexOf(lowerCaseFilter)!=-1)
                                    
                                   
				     return true;
                                else if (String.valueOf(demande.getCv()).indexOf(lowerCaseFilter)!=-1)
                                    
                                   
				     return true;
                               
				     else  
				    	 return false; // Does not match.
			});
                });
         // 3. Wrap the FilteredList in a SortedList. 
		SortedList<Demande> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(reclaTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		reclaTable.setItems(sortedData);
         
    }         

   

    @FXML
    private void mail(ActionEvent event) throws MessagingException {
         Properties prop = System.getProperties();
        prop.put("mail.smtp.port", "587");
         prop.put("mail.smtp.auth", true);
         prop.put("mail.smtp.starttls.enable", "true");
        Session newSession = Session.getDefaultInstance(prop, null);

        String emailsubject="UPGRADI demande valideé";
        String emailbody="Demande traitee et supprimer avec succes";
        Message message = new MimeMessage(newSession);
        try {
        String    email="hajouwa1998@gmail.com";
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
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

           String fromuser ="space.upgradi@gmail.com";
           String pass ="upgradi123456789";
           String emailhost="smtp.gmail.com";
           Transport transport =newSession.getTransport("smtp");
           transport.connect(emailhost,fromuser,pass);
           transport.sendMessage( message, message.getAllRecipients());
           transport.close();
           
           //ajout d'une inscri
      
     
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("demande supprimer check your Mail");
        alert.show();
    }

    private void back(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/eventsView.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  


     
            } catch (IOException ex) {
                Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void showDashbord(MouseEvent event) {
    }

    @FXML
    private void eventsView(MouseEvent event) {
    }

    @FXML
    private void reclamation(MouseEvent event) {
    }


    @FXML
    private void deconnecter(MouseEvent event) {
    }

    @FXML
    private void trierLesEvents(MouseEvent event) {
    }

    @FXML
    private void refreshPage(MouseEvent event) {
         Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/Demande1.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void recherche(KeyEvent event) {
    }

    @FXML
    private void searchByNameAndDesc(MouseEvent event) {
    }

    @FXML
    private void devenirFormateur(ActionEvent event) {
    }

    @FXML
    private void coursview(MouseEvent event) {
          Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/couradmin.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void reclamationview(MouseEvent event) {
         Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/Reclamation1.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void demandeview(MouseEvent event) {
         Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/Demande1.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void packetview(MouseEvent event) {
        Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/Pack.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void examenview(MouseEvent event) {
          Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/examenview.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void couponBview(MouseEvent event) {
                 Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/couponB.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }
    

}
