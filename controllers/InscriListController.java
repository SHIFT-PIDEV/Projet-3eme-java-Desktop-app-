/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Event;
import entities.InscriEvent;
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
import javafx.scene.input.MouseEvent;
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
    private TableView<InscriEvent> tableauInscri;
    @FXML
    private TableColumn<InscriEvent, Integer> idInscri;
    @FXML
    private TableColumn<InscriEvent, Integer> idClient;
    @FXML
    private TableColumn<InscriEvent, Integer> idEvent;
    @FXML
    private TableColumn<InscriEvent, Timestamp> dateInscri;
    @FXML
    private TableColumn<?, ?> mailClient;
    @FXML
    private TableColumn<?, ?> nomClient;
    
    private List<InscriEvent> listInscris=new ArrayList<>();
    @FXML
    private Text lesInscris;
    
    public void setText(String nbInscrit,List<InscriEvent> l){
        this.nbrInscri.setText(nbInscrit);
        this.listInscris=l;
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

    @FXML
    private void afficher(MouseEvent event) {
        ObservableList<InscriEvent> list=FXCollections.observableArrayList(this.listInscris); 
        
        System.out.println("Sizeee: "+this.listInscris.size());
         idInscri.setCellValueFactory(new PropertyValueFactory<>("idinscri"));
        idClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        idEvent.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
        dateInscri.setCellValueFactory(new PropertyValueFactory<>("dateInscri")); 
        tableauInscri.setItems(list);
        
    }
    
}
