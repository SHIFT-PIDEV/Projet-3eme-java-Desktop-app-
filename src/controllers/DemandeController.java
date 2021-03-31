/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Client;
import entities.Demande;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import services.DemandeDao;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class DemandeController implements Initializable {

    @FXML
    private Text iconUserDef;
    @FXML
    private HBox dashbord;
    @FXML
    private HBox eventsInEventView;
    @FXML
    private Text reclamation;
    @FXML
    private TextField searchBar;
    @FXML
    private TextField id;
    @FXML
    private TextField objet;
    @FXML
    private TextArea description;
    @FXML
    private Button attach;
    @FXML
    private Button ajouter3;
    @FXML
    private TextField cv;
    @FXML
    private Button modifier;
   @FXML
    private TableView<Demande> reclaTable;
    @FXML
    private TableColumn<Demande, Integer> idColonne;
    @FXML
    private TableColumn<Demande, String> objetColonne;
    @FXML
    private TableColumn<Demande, String> descriptionColonne;
    @FXML
    private TableColumn<Demande, String> cvColonne;
    @FXML
    private TextField filterField;
    @FXML
    private Button supprimer;
    @FXML
    private TextField idlab;
    @FXML
    private TextField objetlab;
    @FXML
    private TextArea objetdes;
    @FXML
    private TextField cvlab;
    @FXML
    private Button attach2;

    
     private ListData2 listdata = new ListData2();
    private ObservableList<Demande> recla2=FXCollections.observableArrayList();
                Client c=new Client();
    @FXML
    private HBox HB_examen;
    @FXML
    private HBox HB_deconnexion;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
             
               public void setNameUser(){
              this.iconUserDef.setText(this.c.getNom()+" "+this.c.getPrenom());
          }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ajouter3.setOnAction((ActionEvent event) -> {
             if ((!(id.getText() == null || id.getText().trim().isEmpty()))&&(!(objet.getText() == null || objet.getText().trim().isEmpty()))&&(!(description.getText() == null || description.getText().trim().isEmpty()))&&(!(cv.getText() == null || cv.getText().trim().isEmpty())))
             {
                 Demande p = new Demande (Integer.parseInt(id.getText()), objet.getText(), description.getText(), cv.getText());
                 
                 DemandeDao pdao = DemandeDao.getInstance();
                 pdao.insert(p);
                 
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Information Dialog");
                 alert.setHeaderText(null);
                 alert.setContentText("Demande insérée avec succés!");
                 alert.show();
                 
             }
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Information Dialog");
             alert.setHeaderText(null);
             alert.setContentText("Demande non valide !");
             alert.show();
         });
          
                reclaTable.setItems(listdata.getrecla2());
                
        idColonne.setCellValueFactory(cell -> cell.
                getValue().getIdProperty().asObject());
        
        objetColonne.setCellValueFactory(cell -> cell.
                getValue().getobjetProperty());
        descriptionColonne.setCellValueFactory(cell -> cell.
                getValue().getDescriptionProperty());
                 cvColonne.setCellValueFactory(cell -> cell.
                getValue().getCvProperty());
        reclaTable.setOnMouseClicked((MouseEvent event) -> {
            idlab.setText(String.valueOf(listdata.getrecla2()
                    .get(reclaTable.getSelectionModel().getSelectedIndex())
                    .getId()));
            objetlab.setText(listdata.getrecla2()
                    .get(reclaTable.getSelectionModel().getSelectedIndex())
                    .getObjet());
            objetdes.setText(listdata.getrecla2()
                    .get(reclaTable.getSelectionModel().getSelectedIndex())
                    .getDescription());
            cvlab.setText(listdata.getrecla2()
                    .get(reclaTable.getSelectionModel().getSelectedIndex())
                    .getCv());
         });
          supprimer.setOnAction((ActionEvent event) -> {
              int x=  Integer.parseInt(idlab.getText());
              DemandeDao pdao = DemandeDao.getInstance();
              Demande p;
              p = pdao.displayById(x);
              pdao.delete(p);
         });
                 modifier.setOnAction((ActionEvent event) -> {
                     int x=  Integer.parseInt(idlab.getText());
                     Demande p1 = new Demande (Integer.parseInt(idlab.getText()), objetlab.getText(), objetdes.getText(), cvlab.getText());
                     DemandeDao pdao = DemandeDao.getInstance();
                     pdao.update(p1);
         });
                  DemandeDao pdao2=DemandeDao.getInstance();
        recla2= pdao2.displayAll();
        
        FilteredList<Demande> filteredData = new FilteredList<>(recla2, b -> true);
         filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(demande -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
                                      System.out.println(newValue);

					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (demande.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (demande.getObjet().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                    
					return true; // Filter matches last name.
				}
                                
				else if (String.valueOf(demande.getId()).indexOf(lowerCaseFilter)!=-1)
                                    
                                   
				     return true;
                                else if (String.valueOf(demande.getCv()).indexOf(lowerCaseFilter)!=-1)
                                    
                                   
				     return true;
                               
				     else  
				    	 return false; // Does not match.
			});
                });
         // 3. Wrap the FilteredList in a SortedList. 
		SortedList<Demande> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(reclaTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		reclaTable.setItems(sortedData);
    }    

    @FXML
    private String attach(MouseEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        String filename=f.getAbsolutePath();
        cv.setText(filename);
        return filename;
    }



    @FXML
    private String attach2(MouseEvent event) {
           JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        String filename=f.getAbsolutePath();
        cv.setText(filename);
        return filename;
    }

    private void back(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/frontEventView.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  


     
            } catch (IOException ex) {
                Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void showDashbord(MouseEvent event) {
    }

    @FXML
    private void eventsView(MouseEvent event) {
         FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/frontEventView.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               FrontEventViewController fev=Loader.getController();
               fev.c=this.c;
               fev.setNameUserandNotif();
               fev.afficherAll();
                Parent p=Loader.getRoot();
                Stage frontView ;
               
                    frontView= (Stage) ((Node) ((MouseEvent)event).getSource()).getScene().getWindow();
                
                Scene scene = new Scene(p);
                frontView.setScene(scene);
                frontView.show(); 
    }



    @FXML
    private void deconnecter(MouseEvent event) {
    }

    @FXML
    private void trierLesEvents(MouseEvent event) {
    }

    @FXML
    private void refreshPage(MouseEvent event) {
         FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/Demande2.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               DemandeController fev=Loader.getController();
               fev.c=this.c;
               fev.setNameUser();
               //fev.afficherAll();
                Parent p=Loader.getRoot();
                Stage frontView ;
               
                    frontView= (Stage) ((Node) ((MouseEvent)event).getSource()).getScene().getWindow();
                
               
                Scene scene = new Scene(p);
                frontView.setScene(scene);
                frontView.show(); 
    }

    @FXML
    private void recherche(KeyEvent event) {
    }

    @FXML
    private void searchByNameAndDesc(MouseEvent event) {
    }


    @FXML
    private void COURSVIEW(MouseEvent event) {
        FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/Frontcourview.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
              FrontcourviewController fev=Loader.getController();
               fev.c=this.c;
               fev.setNameUser();
               fev.afficherAll();
                Parent p=Loader.getRoot();
                Stage frontView ;
               
                    frontView= (Stage) ((Node) ((MouseEvent)event).getSource()).getScene().getWindow();
                
               
                Scene scene = new Scene(p);
                frontView.setScene(scene);
                frontView.show(); 
    }

    @FXML
    private void RECLAMTIONVIEW(MouseEvent event) {
          FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/Reclamation2.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               ReclamationController fev=Loader.getController();
               fev.c=this.c;
               fev.setNameUser();
               //fev.afficherAll();
                Parent p=Loader.getRoot();
                Stage frontView ;
               
                    frontView= (Stage) ((Node) ((MouseEvent)event).getSource()).getScene().getWindow();
                
               
                Scene scene = new Scene(p);
                frontView.setScene(scene);
                frontView.show(); 
    }


    @FXML
    private void demandeView(MouseEvent event) {
         FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/Demande2.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               DemandeController fev=Loader.getController();
               fev.c=this.c;
               fev.setNameUser();
               //fev.afficherAll();
                Parent p=Loader.getRoot();
                Stage frontView ;
               
                    frontView= (Stage) ((Node) ((MouseEvent)event).getSource()).getScene().getWindow();
                
               
                Scene scene = new Scene(p);
                frontView.setScene(scene);
                frontView.show(); 
    }

    @FXML
    private void packetview(MouseEvent event) {
         FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/packagefront.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               PackagefrontController fev=Loader.getController();
               fev.c=this.c;
               fev.setNameUser();
               fev.afficherAll();
                Parent p=Loader.getRoot();
                Stage frontView ;
               
                    frontView= (Stage) ((Node) ((MouseEvent)event).getSource()).getScene().getWindow();
                
               
                Scene scene = new Scene(p);
                frontView.setScene(scene);
                frontView.show();
    }

    @FXML
    private void examenview(MouseEvent event) {
          FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/examenview_Front.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               examenViewcontroller_Front fev=Loader.getController();
               fev.c=this.c;
               fev.setNameUser();
               fev.afficherAll();
                Parent p=Loader.getRoot();
                Stage frontView ;
               
                    frontView= (Stage) ((Node) ((MouseEvent)event).getSource()).getScene().getWindow();
                
               
                Scene scene = new Scene(p);
                frontView.setScene(scene);
                frontView.show(); 
    }
    
}
