/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.EventS;

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
    @FXML
    private Button tri;
    @FXML
    private Button refresh;
    @FXML
    private TextField searchBar;
    @FXML
    private ImageView searchIcon;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

     private final ListData listdata = new ListData();
    private final List<Event> events = new ArrayList<>();
    private final List<Event> eventsTrier = new ArrayList<>();
    @FXML
    private Button devFormateur;
    @FXML
    private Text iconUserDef;
    
       public void afficherAll(){
        events.addAll(listdata.getEvents());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <events.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/eventForClient.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                EventForClientController eventController = fxmlLoader.getController();
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
                fxmlLoader.setLocation(getClass().getResource("/views/eventForClient.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                EventForClientController eventController = fxmlLoader.getController();
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.afficherAll();
    }    

    @FXML
    private void showDashbord(MouseEvent event) {
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
    private void trierLesEvents(ActionEvent event) {
        eventsTrier.clear();
        eventsTrier.addAll(listdata.getEvents());
        eventsTrier.sort((o1, o2) -> -(o1.getIdE()-o2.getIdE()));
       
        grid.getChildren().clear();
        System.out.println("grid size"+grid.getChildren().size());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <eventsTrier.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/eventForClient.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                EventForClientController eventController = fxmlLoader.getController();
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
    private void refreshPage(ActionEvent event) {
         Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/frontEventView.fxml"));
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
        this.afficherAfterSearch();
    }

    @FXML
    private void searchByNameAndDesc(MouseEvent event) {
        this.afficherAfterSearch();
    }

    @FXML
    private void devenirFormateur(ActionEvent event) {
    }

    @FXML
    private void moveToAdmin(MouseEvent event) {
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
    
}
