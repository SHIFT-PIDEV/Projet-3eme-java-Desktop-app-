/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import upgradi.Entities.categorie;
import upgradi.Services.categorieservice;

/**
 * FXML Controller class
 *
 * @author Fedy
 */
public class categorieController implements Initializable {

    @FXML
    private ImageView userimg;
    @FXML
    private Label username;
    @FXML
    private HBox acceuil;
    @FXML
    private ImageView icon_home;
    @FXML
    private Label label_acceuil;
    @FXML
    private HBox cours;
    @FXML
    private ImageView icon_cour;
    @FXML
    private Label label_cour;
    @FXML
    private ImageView icon_panier;
   /* @FXML
    private HBox pack;*/
    @FXML
    private ImageView icon_pack;
  /*  @FXML
    private Label label_pack;*/
    @FXML
    private HBox demande;
    @FXML
    private ImageView icon_demande;
    @FXML
    private Label label_demande;
    @FXML
    private HBox event;
    @FXML
    private ImageView icon_event;
    @FXML
    private Label label_event;
    @FXML
    private HBox news;
    @FXML
    private ImageView icon_news;
    @FXML
    private Label label_news;
    @FXML
    private HBox reclamation;
    @FXML
    private ImageView icon_reclamation;
    @FXML
    private Label label_reclamation;
    @FXML
    private HBox wishlist;
    @FXML
    private ImageView icon_wishlist;
    @FXML
    private Label label_wishlist;
    @FXML
    private ImageView icon_signout;
    @FXML
    private Label label_signout;
    @FXML
    private HBox exam;
    @FXML
    private ImageView icon_exam;
    @FXML
    private Label label_exam;
    @FXML
    private HBox categorie;
    @FXML
    private Label label_categorie;
    @FXML
    private HBox pack;
    @FXML
    private Label label_pack;
    @FXML
    private TextField nomcat;
    @FXML
    private Button btn_valider;
    @FXML
    private TableView<categorie> categorietable;
    @FXML
    private TableColumn<categorie, Integer> id;
    @FXML
    private TableColumn<categorie, String> nom_categorie;
      private listaData listdata = new listaData();
    @FXML
    private Button btn_supp;
    @FXML
    private TextField t_id;
    @FXML
    private TextField id1;
    @FXML
    private Label col_id;
    @FXML
    private Button btn_find;
    @FXML
    private Button btn_mod;
    @FXML
    private Button trie;
    @FXML
    private TextField searchBar;

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
    private void acceuilClick(MouseEvent event) {
    

     try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/upgradi/Views/acceuil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void ajouter_categorie(ActionEvent event) {
             categorie c = new categorie(nomcat.getText());
            categorieservice cserv= categorieservice.getInstance();
            cserv.insert(c);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("categorie insérée avec succés!");
        alert.show();
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
        
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/upgradi/Views/categorie.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(categorieController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void find_categorie(ActionEvent event) {
            int idl=Integer.valueOf(id1.getText());
        categorieservice es;
        es=categorieservice.getInstance();
        categorie e=es.displayById(idl);
        
        id.setText(String.valueOf(e.getId()));
        nomcat.setText(e.getNomcategorie());
    }

    @FXML
    private void modifier_categorie(ActionEvent event) {
           categorie e=new categorie();
        
        e.setId(Integer.parseInt(id.getText()));
        e .setNomcategorie(nomcat.getText());
      
        
      
        categorieservice es= categorieservice.getInstance();
        es.update(e);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("categorie Modifier avec succés!");
        alert.show();
        id.setText("");
        nomcat.setText("");
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/upgradi/Views/categorie.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(categorieController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void Tri(ActionEvent event) {
                     nom_categorie.setSortType(TableColumn.SortType.ASCENDING);
        categorietable.getSortOrder().add(nom_categorie);
        categorietable.sort();
    }
}
