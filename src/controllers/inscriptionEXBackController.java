/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.InscripExam;
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
 * @author mahdi
 */
public class inscriptionEXBackController implements Initializable {

    @FXML
    private Text nbrInscri;
    @FXML
    private Text lesInscris;
    @FXML
    private TextField searchInscri;
    @FXML
    private TableView<InscripExam> tableauInscri;
    @FXML
    private TableColumn<InscripExam, Integer> idInscri;
    @FXML
    private TableColumn<InscripExam, Integer> idClient;
    @FXML
    private TableColumn<InscripExam, Integer> idexam;
    @FXML
    private TableColumn<InscripExam, Timestamp> dateInscri;
    @FXML
    private TableColumn<InscripExam, String> nomClient;
    @FXML
    private TableColumn<InscripExam, String> prenomclient;
    @FXML
    private TableColumn<InscripExam, String> mailClient;

    private List<InscripExam> listInscris=new ArrayList<>();
    
     public void setText(int nbreinscrits,List<InscripExam> l ){
        this.nbrInscri.setText(toString().valueOf(nbreinscrits));
        this.listInscris=l;
    }
      public void afficher(){
        ObservableList<InscripExam> list=FXCollections.observableArrayList(this.listInscris); 
        
        System.out.println("Sizeee: "+this.listInscris.size());
         idInscri.setCellValueFactory(new PropertyValueFactory<>("idinscri"));
        idClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        idexam.setCellValueFactory(new PropertyValueFactory<>("idExam"));
        dateInscri.setCellValueFactory(new PropertyValueFactory<>("dateInscri")); 
        nomClient.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomclient.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        mailClient.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableauInscri.setItems(list);
        
        
        
       
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void chercherInscription(ActionEvent event) {
    }
    
}
