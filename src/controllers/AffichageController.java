/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Event;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AffichageController implements Initializable {

    @FXML
    private TableView<Event> tableEvent;
    @FXML
    private TableColumn<Event,Integer> ide;
    @FXML
    private TableColumn<Event, Integer> idf;
    @FXML
    private TableColumn<Event, String> ne;
    @FXML
    private TableColumn<Event, Date> dd;
    @FXML
    private TableColumn<Event, Integer> du;
    @FXML
    private TableColumn<Event, String> desc;
    
    private ListData listdata = new ListData();
    @FXML
    private TextField searchBar;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ide.setCellValueFactory(new PropertyValueFactory<>("idE"));
        idf.setCellValueFactory(new PropertyValueFactory<>("idF"));
        ne.setCellValueFactory(new PropertyValueFactory<>("nomE"));
        dd.setCellValueFactory(new PropertyValueFactory<>("dateD"));
        du.setCellValueFactory(new PropertyValueFactory<>("duree"));
        desc.setCellValueFactory(new PropertyValueFactory<>("descE"));
        //dataList=mysqlconnect.getDatausers();
   tableEvent.setItems( listdata.getEvents());
  FilteredList<Event> filteredData = new FilteredList<>(listdata.getEvents(), b -> true);  
 searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(e -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (e.getNomE().toLowerCase().contains(lowerCaseFilter) ) {
     return true; // Filter matches nomEvent
    } else if (e.getDescE().toLowerCase().contains(lowerCaseFilter)) {
     return true; // Filter matches DescriptioEvent
    }
         else  
          return false; 
   });
  });  
  SortedList<Event> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tableEvent.comparatorProperty());  
  tableEvent.setItems(sortedData);    
    }    



    
}
