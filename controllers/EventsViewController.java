/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import entities.Event;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.EventS;
import upgradi.Upgradi;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class EventsViewController implements Initializable {

    @FXML
    private HBox dashbord;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Button createEventBt;
    private Image image;
    @FXML
    private TextField searchBar;
    @FXML
    private HBox eventsInEventView;
    
    private final ListData listdata = new ListData();
    private final List<Event> events = new ArrayList<>();
    private final List<Event> eventsTrier = new ArrayList<>();
    @FXML
    private Text iconUserDef;
    public void afficherAll(){
        events.addAll(listdata.getEvents());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <events.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/event.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                EventController eventController = fxmlLoader.getController();
                eventController.setData(events.get(i));

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
                grid.add(anchorPane, column++, row); 
                GridPane.setMargin(anchorPane, new Insets(10));
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
                fxmlLoader.setLocation(getClass().getResource("/views/event.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                EventController eventController = fxmlLoader.getController();
                eventController.setData(eventsSearch.get(i));

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
                grid.add(anchorPane, column++, row); 
                GridPane.setMargin(anchorPane, new Insets(10));
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
        this.afficherAll();
    }    

    @FXML
    private void showDashbord(MouseEvent event) {
        Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/dashbord.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(EventsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void createEvent(ActionEvent event) {
        Parent page1=null;
        try {
            page1 = FXMLLoader.load(getClass().getResource("/views/createEvent.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(EventsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                Stage ajoutStage=new Stage();
                ajoutStage.setScene(scene);
                ajoutStage.show();
    }
       
    @FXML
    private void refreshPage(MouseEvent event) {
        Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/eventsView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }
    @FXML
    private void eventsView(MouseEvent event) {
        Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/eventsView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    
    }

    @FXML
    private void searchByNameAndDesc(MouseEvent event) {
        this.afficherAfterSearch();
    }

    @FXML
    private void recherche(KeyEvent event) {
      this.afficherAfterSearch();
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
                fxmlLoader.setLocation(getClass().getResource("/views/event.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                EventController eventController = fxmlLoader.getController();
                eventController.setData(eventsTrier.get(i));

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
                grid.add(anchorPane, column++, row); 
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }

    @FXML
    private void deconnexion(MouseEvent event) {
        Upgradi u=new Upgradi();
        Stage s=(Stage)this.createEventBt.getScene().getWindow();
        s.close();
        u.callStart();
    }

       
    
}
