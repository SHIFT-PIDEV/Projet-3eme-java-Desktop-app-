/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.ListData2;
import dao.ReclamationDao;
import entities.Client;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.controlsfx.control.Notifications;
import upgradi.Upgradi;


/**
 * FXML Controller class
 *
 * @author medam
 */
public class Reclamation1Controller implements Initializable {
    @FXML
    Text reclamation;
private ListData2 listdata = new ListData2();
            private ObservableList<Reclamation> recla=FXCollections.observableArrayList();

    @FXML
    private TableView<Reclamation> reclaTable;
    @FXML
    private TableColumn<Reclamation, Integer> idColonne;
    @FXML
    private TableColumn<Reclamation, String> objetColonne;
    @FXML
    private TableColumn<Reclamation, String> descriptionColonne;
    @FXML
    private TextField idlab;
    @FXML
    private TextField objetlab;
    @FXML
    private TextArea objetdes;
    @FXML
    private Button supprimer;
    @FXML
    private TextField filterField;
    @FXML
    private Text iconUserDef;
    @FXML
    private HBox dashbord;
    @FXML
    private HBox eventsInEventView;
    @FXML
    private Text demande;
    @FXML
    private TextField searchBar;
    @FXML
    private Button devFormateur;
    @FXML
    private Button sms;
    @FXML
    private Button mail;
    private Client c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         idColonne.setCellValueFactory(cell -> cell.
                getValue().getIdProperty().asObject());
        
        objetColonne.setCellValueFactory(cell -> cell.
                getValue().getobjetProperty());
        descriptionColonne.setCellValueFactory(cell -> cell.
                getValue().getDescriptionProperty());
        reclaTable.setOnMouseClicked(event->{
            idlab.setText(String.valueOf(listdata.getrecla()
                .get(reclaTable.getSelectionModel().getSelectedIndex())
                .getId()));
        objetlab.setText(listdata.getrecla()
                .get(reclaTable.getSelectionModel().getSelectedIndex())
                .getObjet());
        objetdes.setText(listdata.getrecla()
                .get(reclaTable.getSelectionModel().getSelectedIndex())
                .getDescription());
        
        });
        reclaTable.setItems(listdata.getrecla());
                
        idColonne.setCellValueFactory(cell -> cell.
                getValue().getIdProperty().asObject());
        
        objetColonne.setCellValueFactory(cell -> cell.
                getValue().getobjetProperty());
        descriptionColonne.setCellValueFactory(cell -> cell.
                getValue().getDescriptionProperty());
       reclaTable.setOnMouseClicked(event->{
        idlab.setText(String.valueOf(listdata.getrecla()
                .get(reclaTable.getSelectionModel().getSelectedIndex())
                .getId()));
        objetlab.setText(listdata.getrecla()
                .get(reclaTable.getSelectionModel().getSelectedIndex())
                .getObjet());
        objetdes.setText(listdata.getrecla()
                .get(reclaTable.getSelectionModel().getSelectedIndex())
                .getDescription());
       });
        supprimer.setOnAction(event -> {
                                                                 Image img = new Image ("/controllers/check.png");

                 int x=  Integer.parseInt(idlab.getText());
                 ReclamationDao pdao = ReclamationDao.getInstance();
                  Reclamation p =          pdao.displayById(x);
                  pdao.delete(p);
                  Notifications notificationBuilder = null;
                    notificationBuilder = Notifications.create()                
                            .title("supprimer demande")
                            .text("demande supprimer avec succés!").darkStyle()
                            .graphic(new ImageView (img))
                            .position(Pos.TOP_RIGHT)
                            .onAction((ActionEvent event1) -> {
                                System.out.println("Clicked on notification");
                          });
                       notificationBuilder.show();

                });
                    ReclamationDao pdao=ReclamationDao.getInstance();
        recla= pdao.displayAll();

         FilteredList<Reclamation> filteredData = new FilteredList<>(recla, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(reclamation -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
                                      System.out.println(newValue);

					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (reclamation.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (reclamation.getObjet().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                    
					return true; // Filter matches last name.
				}
				else if (String.valueOf(reclamation.getId()).indexOf(lowerCaseFilter)!=-1)
                                    
                                   
				     return true;
				     else  
				    	 return false; // Does not match.
			});
                });
                
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(reclaTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		reclaTable.setItems(sortedData);
    }    

    @FXML
    private void mail(ActionEvent event) throws MessagingException {
           // mailing 
        Properties prop = System.getProperties();
        prop.put("mail.smtp.port", "587");
         prop.put("mail.smtp.auth", true);
         prop.put("mail.smtp.starttls.enable", "true");
        Session newSession = Session.getDefaultInstance(prop, null);

        String emailsubject="UPGRADI reclmation valideé";
        String emailbody="reclamtion traitee";
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

           String fromuser ="hajouwa1998@gmail.com";
           String pass ="jiji1998";
           String emailhost="smtp.gmail.com";
           Transport transport =newSession.getTransport("smtp");
           transport.connect(emailhost,fromuser,pass);
           transport.sendMessage( message, message.getAllRecipients());
           transport.close();
           
           //ajout d'une inscri
      
     
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("reclamtion supprimer check your Mail");
        alert.show();
    }
 private void memePage(Object event){
              FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/Reclamation1.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               Reclamation1Controller fev=Loader.getController();
               fev.c=this.c;
               
                Parent p=Loader.getRoot();
                Stage frontView ;
                if(event instanceof MouseEvent){
                    frontView= (Stage) ((Node) ((MouseEvent)event).getSource()).getScene().getWindow();
                }
                else{
                    frontView = (Stage) ((Node) ((ActionEvent)event).getSource()).getScene().getWindow();
                }
                Scene scene = new Scene(p);
                frontView.setScene(scene);
                frontView.show(); 
          }
    private void sms(ActionEvent event) {
        	
   
	}




    @FXML
    private void eventsView(MouseEvent event) {
    }

    @FXML
    private void reclamation(MouseEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/Reclamation1.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void demande(MouseEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/Demande1.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(DemandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void deconnecter(MouseEvent event) {
         Upgradi u=new Upgradi();
        Stage s=(Stage)this.devFormateur.getScene().getWindow();
        s.close();
        u.callStart();
    }

    @FXML
    private void trierLesEvents(MouseEvent event) {
    }

    @FXML
    private void refreshPage(MouseEvent event) {
        this.memePage(event);
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
    private void showDashbord(MouseEvent event) {
    }
    }

   
    

