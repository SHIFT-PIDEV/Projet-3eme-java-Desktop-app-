/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import entities.inscriView;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class InscriListController implements Initializable {

    @FXML
    private Text nbrInscri;
    @FXML
    private TextField searchInscri;
    @FXML
    private TableView<inscriView> tableauInscri;
    @FXML
    private TableColumn<inscriView, Integer> idInscri;
    @FXML
    private TableColumn<inscriView, Integer> idClient;
    @FXML
    private TableColumn<inscriView, Integer> idEvent;
    @FXML
    private TableColumn<inscriView, Timestamp> dateInscri;
    @FXML
    private TableColumn<inscriView, String> mailClient;
    @FXML
    private TableColumn<inscriView, String> nomClient;
    
    private List<inscriView> listInscris=new ArrayList<>();
    @FXML
    private Text lesInscris;
    
    public void setText(String nbInscrit,List<inscriView> l){
        this.nbrInscri.setText(nbInscrit);
        this.listInscris=l;
    }
    public void afficher(){
        ObservableList<inscriView> list=FXCollections.observableArrayList(this.listInscris); 
        
        System.out.println("Sizeee: "+this.listInscris.size());
         idInscri.setCellValueFactory(new PropertyValueFactory<>("idinscri"));
        idClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        idEvent.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
        dateInscri.setCellValueFactory(new PropertyValueFactory<>("dateInscri")); 
        nomClient.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        mailClient.setCellValueFactory(new PropertyValueFactory<>("emailClient"));
        
        tableauInscri.setItems(list);
        
        FilteredList<inscriView> filteredData = new FilteredList<>(list, b -> true);  
 searchInscri.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(e -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (e.getEmailClient().toLowerCase().contains(lowerCaseFilter) ) {
     return true; // Filter matches nomEvent
    } else if (e.getNomClient().toLowerCase().contains(lowerCaseFilter)) {
     return true; // Filter matches DescriptioEvent
    }
         else  
          return false; 
   });
  });  
  SortedList<inscriView> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tableauInscri.comparatorProperty());  
  tableauInscri.setItems(sortedData);
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
    private void chercherInscription(ActionEvent event) {
    }

}
