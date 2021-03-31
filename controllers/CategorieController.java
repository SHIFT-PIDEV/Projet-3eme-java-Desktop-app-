/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.categorie;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.categorieservice;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class CategorieController implements Initializable {

    @FXML
    private Text iconUserDef;
    @FXML
    private HBox dashbord;
    @FXML
    private HBox eventsInEventView;
    @FXML
    private TextField searchBar;
    @FXML
    private Button createEventBt;
    @FXML
    private TableView<categorie> categorietable;
    @FXML
    private TableColumn<categorie, Integer> id;
    @FXML
    private TableColumn<categorie, String> nom_categorie;
    @FXML
    private TextField t_id;
    @FXML
    private Button btn_supp;

    private listda listdata = new listda();
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         categorietable.setItems(listdata.getcategorie());
        id.setCellValueFactory( new PropertyValueFactory<>("id"));

 nom_categorie.setCellValueFactory( new PropertyValueFactory<>("nomcategorie"));
  
FilteredList<categorie> filteredData = new FilteredList<>(listdata.getcategorie(), b -> true);  
  searchBar.textProperty().addListener(((observable,oldValue,newValue) -> {

       filteredData.setPredicate(e -> {
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }    
       String lowerCaseFilter = newValue.toLowerCase();
       if (e.getNomcategorie().toLowerCase().contains(lowerCaseFilter) ) {
            return true; 
        }
         else  
          return false; 
        });
       }));
    SortedList<categorie> sortedData = new SortedList<>(filteredData);  
    sortedData.comparatorProperty().bind(categorietable.comparatorProperty());  
    categorietable.setItems(sortedData);

    }   

    @FXML
    private void showDashbord(MouseEvent event) {
    }

    @FXML
    private void eventsView(MouseEvent event) {
    }

    @FXML
    private void cour(MouseEvent event) {
    }

    @FXML
    private void deconnexion(MouseEvent event) {
    }

    @FXML
    private void trierLesEvents(MouseEvent event) {
    }

    @FXML
    private void refreshPage(MouseEvent event) {
         Parent page1 = null;
        try {
            page1= FXMLLoader.load(getClass().getResource("/views/categorie.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }


    @FXML
    private void createCategorie(ActionEvent event) {
              Parent page1=null;
        try {
            page1 = FXMLLoader.load(getClass().getResource("/views/createcategorie.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AjoutercoursformateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Scene scene = new Scene(page1);
                Stage ajoutStage=new Stage();
                ajoutStage.setScene(scene);
                ajoutStage.show();
    }

    @FXML
    private void supprimer_categorie(ActionEvent event) {
         int s=Integer.valueOf(t_id.getText());
            categorieservice cserv= categorieservice.getInstance();
            cserv.delete(s);
                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("categorie supprimer avec succés!");
        alert.show();
        t_id.setText("");
    }

    private void rechercher(ActionEvent event) {
           List<categorie> eventsSearch=new ArrayList();
          categorieservice evs=categorieservice.getInstance();
                if(!"".equals(searchBar.getText())){
                       categorietable.setItems(listdata.getcategorie());
                      eventsSearch= evs.displayAllAfterSearch(searchBar.getText()); 
                              id.setCellValueFactory( new PropertyValueFactory<>("id"));

 nom_categorie.setCellValueFactory( new PropertyValueFactory<>("nomcategorie"));
                }
    }

    
}
