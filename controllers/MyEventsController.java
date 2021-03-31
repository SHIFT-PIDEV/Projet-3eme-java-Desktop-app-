/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Client;
import entities.Event;
import entities.InscriEvent;
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
import services.EventS;
import services.InscriEventS;
import upgradi.Upgradi;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class MyEventsController implements Initializable {

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
    private Text iconUserDef;
    
    private final List<Event> events = new ArrayList<>();
    private final List<Event> eventsTrier = new ArrayList<>();
    
    Client c=new Client();
    @FXML
    private FontAwesomeIconView tri;
          public void setNameUser(){
              this.iconUserDef.setText(this.c.getNom()+" "+this.c.getPrenom());
          }
          
          
    private void memePage(Object event){
              FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/myEvents.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(MyEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               MyEventsController mec=Loader.getController();
               mec.c=this.c;
               mec.setNameUser();
               mec.afficherAll();
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
           InscriEventS ies= InscriEventS.getInscriEvent();
           List<Event> list=new ArrayList<>(); 
           list=ies.displayByIdClient(this.c.getId());
        events.addAll(list);
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
          if(!"".equals(searchBar.getText())){
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
         FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/frontEventView.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               FrontEventViewController fev=Loader.getController();
               fev.c=this.c;
               fev.setNameUser();
               fev.afficherAll();
                Parent p=Loader.getRoot();
                Stage frontView ;
                    frontView= (Stage) ((Node) ((MouseEvent)event).getSource()).getScene().getWindow();
                Scene scene = new Scene(p);
                frontView.setScene(scene);
                frontView.show(); 
    }

    @FXML
    private void trierLesEvents(MouseEvent event) {
        eventsTrier.clear();
        InscriEventS ies= InscriEventS.getInscriEvent();
           List<Event> list=new ArrayList<>(); 
           list=ies.displayByIdClient(this.c.getId());
        eventsTrier.addAll(list);
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
    private void deconnecter(MouseEvent event) {
        
        Upgradi u=new Upgradi();
        Stage s=(Stage)tri.getScene().getWindow();
        s.close();
        u.callStart();
        
    }

    @FXML
    private void refreshPage(MouseEvent event) {
        this.memePage(event);
    }
    
}
