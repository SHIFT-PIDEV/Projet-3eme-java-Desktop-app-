/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Client;
import entities.Event;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ClientS;
import services.EventS;
import upgradi.Upgradi;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FrontEventViewController implements Initializable {

    @FXML
    private HBox dashbord;
    @FXML
    private HBox eventsInEventView;
    private Button refresh;
    @FXML
    private TextField searchBar;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Button devFormateur;
    @FXML
    private Text iconUserDef;
    @FXML
    private Text notifNumbre;
    @FXML
    private FontAwesomeIconView notif;
    private final ListData listdata = new ListData();
    private final List<Event> events = new ArrayList<>();
    private final List<Event> eventsTrier = new ArrayList<>();
    
    Client c=new Client();
    
          public void setNameUserandNotif(){
              this.iconUserDef.setText(this.c.getNom()+" "+this.c.getPrenom());
              this.notifNumbre.setText(String.valueOf(this.c.getNbrnotif()));
              
              if(this.c.getNbrnotif()!=0){
                  this.notif.setStyle("-fx-fill:red;");
              }
              else{
                  this.notif.setStyle("-fx-fill:#0fbcf9;");
              }
          }
          private void memePage(Object event){
              FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/frontEventView.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               FrontEventViewController fev=Loader.getController();
               fev.c=this.c;
               fev.setNameUserandNotif();
               fev.afficherAll();
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
       public void afficherAll(){    
        events.addAll(listdata.getEvents());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <events.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/eventv2.fxml"));
                HBox hbox = fxmlLoader.load();

                Eventv2Controller eventController = fxmlLoader.getController();
                eventController.setData(events.get(i),this.c);
                eventController.setLikeIconColor();
                
                if (column == 1) {
                    column = 0;
                    row++;
                }
                
                 grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                grid.add(hbox, column++, row); 
                GridPane.setMargin(hbox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void afficherAfterSearch(){
         List<Event> eventsSearch=new ArrayList();
          EventS evs=EventS.getEventS();
          if(!" ".equals(searchBar.getText())){
              grid.getChildren().clear();
        eventsSearch= evs.displayAllAfterSearch(searchBar.getText()); 
         int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <eventsSearch.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/eventv2.fxml"));
                HBox hbox = fxmlLoader.load();
                
                Eventv2Controller eventController = fxmlLoader.getController();
                eventController.setData(eventsSearch.get(i),this.c);
                eventController.setLikeIconColor();
                if (column == 1) {
                    column = 0;
                    row++;
                }
                
                 grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                grid.add(hbox, column++, row); 
                GridPane.setMargin(hbox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
          }           
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
    private void showDashbord(MouseEvent event) {
    }

    @FXML
    private void eventsView(MouseEvent event) {
        this.memePage(event);
    }

    @FXML
    private void trierLesEvents(MouseEvent event) {
        eventsTrier.clear();
        eventsTrier.addAll(listdata.getEvents());
        eventsTrier.sort((o1, o2) -> -(o1.getIdE()-o2.getIdE()));
       
        grid.getChildren().clear();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <eventsTrier.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/eventv2.fxml"));
                HBox hbox = fxmlLoader.load();

                Eventv2Controller eventController = fxmlLoader.getController();
                eventController.setData(eventsTrier.get(i),this.c);
                eventController.setLikeIconColor();

                if (column == 1) {
                    column = 0;
                    row++;
                }
                
                 grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                grid.add(hbox, column++, row); 
                GridPane.setMargin(hbox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }

    @FXML
    private void recherche(KeyEvent event) {
        this.afficherAfterSearch();
    }

    @FXML
    private void searchByNameAndDesc(MouseEvent event) {
        this.afficherAfterSearch();
    }

    @FXML
    private void devenirFormateur(ActionEvent event) {
        FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/myEvents.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               MyEventsController mec=Loader.getController();
               mec.c=this.c;
               mec.setNameUserandNotif();
               mec.afficherAll();
                Parent p=Loader.getRoot();
                Stage myEvents;
               myEvents= (Stage) ((Node) event.getSource()).getScene().getWindow();
               Scene scene = new Scene(p);
                myEvents.setScene(scene);
                myEvents.show();
    }


    @FXML
    private void deconnecter(MouseEvent event) {
        
        Upgradi u=new Upgradi();
        Stage s=(Stage)this.devFormateur.getScene().getWindow();
        s.close();
        u.callStart();
        
    }

    @FXML
    private void refreshPage(MouseEvent event) {
        this.memePage(event);
    }

    @FXML
    private void toEventShare(MouseEvent event) {
        //code affichage des eventsShare lenaaaaaaaaaaaaa
        ClientS cs=ClientS.getClientS();
        cs.toZeroNotif(this.c.getId());
        this.c=cs.displayClientById(this.c.getId());
        
        FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/myNotifications.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(FrontEventViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               MyNotificationsController mnc=Loader.getController();
               mnc.c=this.c;
               mnc.setNameUserandNotif();
               mnc.afficherAll();
                Parent p=Loader.getRoot();
                Stage s;
               s= (Stage) ((Node) event.getSource()).getScene().getWindow();
               Scene scene = new Scene(p);
                s.setScene(scene);
                s.show();
    }

    @FXML
    private void toPanier(MouseEvent event) {
        FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/monPanier.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               MonPanierController mpc=Loader.getController();
               mpc.c=this.c;
               mpc.setNameUser();
               mpc.afficherAll();
                Parent p=Loader.getRoot();
                Stage myPanier;
               myPanier= (Stage) ((Node) event.getSource()).getScene().getWindow();
               Scene scene = new Scene(p);
                myPanier.setScene(scene);
                myPanier.show();
    }
    
}
