/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Client;
import entities.cour;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.courservice;
import upgradi.Upgradi;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class CouradminController implements Initializable {

    @FXML
    private Text iconUserDef;
    @FXML
    private HBox dashbord;
    @FXML
    private HBox eventsInEventView;
    @FXML
    private TextField searchBar;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    private final listdat listdata = new listdat();
    private final List<cour> cours = new ArrayList<>();
Client c=new Client();
    @FXML
    private HBox HB_examen;
    @FXML
    private HBox HB_coupon;
    @FXML
    private HBox HB_deconnexion;
    /**
     * Initializes the controller class.
     */
  private void memePage(Object cour){
              FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/couradmin.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(CouradminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       //   CouradminController fev=Loader.getController();
             //  fev.c=this.c;
             //  fev.setNameUser();
               //fev.afficherAll();
                Parent p=Loader.getRoot();
                Stage frontView ;
                if(cour instanceof MouseEvent){
                    frontView= (Stage) ((Node) ((MouseEvent)cour).getSource()).getScene().getWindow();
                }
                else{
                    frontView = (Stage) ((Node) ((ActionEvent)cour).getSource()).getScene().getWindow();
                }
                Scene scene = new Scene(p);
                frontView.setScene(scene);
                frontView.show(); 
          }
     public void afficherAll(){
         
        cours.addAll(listdata.getcour());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <cours.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/cour.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CourController CourController = fxmlLoader.getController();
                CourController.setData(cours.get(i));

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
       List<cour> eventsSearch=new ArrayList();
          courservice evs=courservice.getInstance();
          if(!"".equals(searchBar.getText())){
              grid.getChildren().clear();
        eventsSearch= evs.displayAllAfterSearch(searchBar.getText()); 
         int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <eventsSearch.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/cour.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

              CourController eventController = fxmlLoader.getController();
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.afficherAll();
    }    

    @FXML
    private void showDashbord(MouseEvent event) {
    }

    @FXML
    private void eventsView(MouseEvent event) {
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
    private void deconnexion(MouseEvent event) {
          Upgradi u=new Upgradi();
        Stage s=(Stage)this.HB_deconnexion.getScene().getWindow();
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
        this.afficherAfterSearch();
    }

    @FXML
    private void searchByNameAndDesc(MouseEvent event) {
        this.afficherAfterSearch();
    }

    @FXML
    private void all_cours(MouseEvent event) {
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
    private void all_packs(MouseEvent event) {
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

    @FXML
    private void all_categorie(MouseEvent event) {
           Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/categorie.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }
}
