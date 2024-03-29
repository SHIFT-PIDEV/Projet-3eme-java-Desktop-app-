    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import entities.Reclamation;
import dao.ReclamationDao;
import javafx.event.ActionEvent;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author medam
 */
public class ReclamationController implements Initializable {
    @FXML
Text reclamation;
private ListData2 listdata = new ListData2();
            private ObservableList<Reclamation> recla=FXCollections.observableArrayList();
    @FXML
    private TextField id;
    @FXML
    private TextField objet;
    @FXML
    private TextArea description;
    @FXML
    private Button ajouter3;
    @FXML
    private Button modifier;
   @FXML
    private TableView<Reclamation> reclaTable;
    @FXML
    private TableColumn<Reclamation, Integer> idColonne;
    @FXML
    private TableColumn<Reclamation, String> objetColonne;
    @FXML
    private TableColumn<Reclamation, String> descriptionColonne;
    @FXML
    private Button supprimer;
    @FXML
    private TextField idlab;
    @FXML
    private TextField objetlab;
    @FXML
    private TextArea objetdes;
    @FXML
    private AnchorPane tab;
    @FXML
    private TextField filterField;
    @FXML
    private Text iconUserDef;
    @FXML
    private HBox dashbord;
    @FXML
    private HBox eventsInEventView;
    @FXML
    private Text demande;
    @FXML
    private TextField searchBar;
    @FXML
    private Button devFormateur;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODOreclaTable.setItems(listdata.getrecla());
                
        idColonne.setCellValueFactory(cell -> cell.
                getValue().getIdProperty().asObject());
        
        objetColonne.setCellValueFactory(cell -> cell.
                getValue().getobjetProperty());
        descriptionColonne.setCellValueFactory(cell -> cell.
                getValue().getDescriptionProperty());
        reclaTable.setOnMouseClicked(event->{
        idlab.setText(String.valueOf(listdata.getrecla()
                .get(reclaTable.getSelectionModel().getSelectedIndex())
                .getId()));
        objetlab.setText(listdata.getrecla()
                .get(reclaTable.getSelectionModel().getSelectedIndex())
                .getObjet());
        objetdes.setText(listdata.getrecla()
                .get(reclaTable.getSelectionModel().getSelectedIndex())
                .getDescription());
        });
         reclaTable.setItems(listdata.getrecla());
                
        idColonne.setCellValueFactory(cell -> cell.
                getValue().getIdProperty().asObject());
        
        objetColonne.setCellValueFactory(cell -> cell.
                getValue().getobjetProperty());
        descriptionColonne.setCellValueFactory(cell -> cell.
                getValue().getDescriptionProperty());
        reclaTable.setOnMouseClicked(event->{
        idlab.setText(String.valueOf(listdata.getrecla()
                .get(reclaTable.getSelectionModel().getSelectedIndex())
                .getId()));
        objetlab.setText(listdata.getrecla()
                .get(reclaTable.getSelectionModel().getSelectedIndex())
                .getObjet());
        objetdes.setText(listdata.getrecla()
                .get(reclaTable.getSelectionModel().getSelectedIndex())
                .getDescription());
        });
        ajouter3.setOnAction(event -> {
            if ((!(id.getText() == null || id.getText().trim().isEmpty()))&&(!(objet.getText() == null || objet.getText().trim().isEmpty()))&&(!(description.getText() == null || description.getText().trim().isEmpty())))
                    {
            Reclamation p = new Reclamation (Integer.parseInt(id.getText()), objet.getText(), description.getText());
        
            ReclamationDao pdao = ReclamationDao.getInstance();
            pdao.insert(p);
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Personne insérée avec succés!");
        alert.show();


                    }
             Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation non valide!");
        alert.show();
        });
          supprimer.setOnAction(event -> {
                    
                 int x=  Integer.parseInt(idlab.getText());
                 ReclamationDao pdao = ReclamationDao.getInstance();
                  Reclamation p =          pdao.displayById(x);
                  pdao.delete(p);
          
                });
                 modifier.setOnAction(event->{
                        int x=  Integer.parseInt(idlab.getText());
            Reclamation p1 = new Reclamation (Integer.parseInt(idlab.getText()), objetlab.getText(), objetdes.getText());
                     ReclamationDao pdao = ReclamationDao.getInstance();
                     pdao.update(p1);
        });
           ReclamationDao pdao=ReclamationDao.getInstance();
        recla= pdao.displayAll();

         FilteredList<Reclamation> filteredData = new FilteredList<>(recla, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(reclamation -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
                                      System.out.println(newValue);

					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (reclamation.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (reclamation.getObjet().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                    
					return true; // Filter matches last name.
				}
				else if (String.valueOf(reclamation.getId()).indexOf(lowerCaseFilter)!=-1)
                                    
                                   
				     return true;
				     else  
				    	 return false; // Does not match.
			});
                });
                
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(reclaTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		reclaTable.setItems(sortedData);     
    }    

   
    @FXML
    private void showDashbord(MouseEvent event) {
    }

    @FXML
    private void eventsView(MouseEvent event) {
    }

    @FXML
    private void reclamation(MouseEvent event) {
    }

    @FXML
    private void demande(MouseEvent event) {
    }

    @FXML
    private void deconnecter(MouseEvent event) {
    }

    @FXML
    private void trierLesEvents(MouseEvent event) {
    }

    @FXML
    private void refreshPage(MouseEvent event) {
    }

    @FXML
    private void recherche(KeyEvent event) {
    }

    @FXML
    private void searchByNameAndDesc(MouseEvent event) {
    }

    @FXML
    private void devenirFormateur(ActionEvent event) {
    }
    
}
