/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Client;
import entities.Event;
import entities.Examen;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ExamenService;
import upgradi.Upgradi;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class examenViewcontroller_Front implements Initializable {

    @FXML
    private Text iconUserDef;
    @FXML
    private HBox dashbord;
    @FXML
    private HBox eventsInEventView;
    @FXML
    private HBox HB_examen;
    @FXML
    private TextField searchBar;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    Client c=new Client();
    
     private final ListeData listedata = new ListeData();
     private final List<Examen> exams = new ArrayList<>();
    private final List<Examen> examsSorted = new ArrayList<>();
    @FXML
    private HBox HB_deconnexion;
    @FXML
    private Button mes_exams;
    
    public void setNameUser(){
              this.iconUserDef.setText(this.c.getNom()+" "+this.c.getPrenom());
          }
    private void memePage(Object event){
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
                if(event instanceof MouseEvent){
                    frontView= (Stage) ((Node) ((MouseEvent)event).getSource()).getScene().getWindow();
                }
                else{
                    frontView = (Stage) ((Node) ((ActionEvent)event).getSource()).getScene().getWindow();
                }
                Scene scene = new Scene(p);
                frontView.setScene(scene);
                frontView.show(); 
          }
      public void afficherAll(){
     
        exams.addAll(listedata.getListExam());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <exams.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/OneExamenView_Front.fxml"));
                
                AnchorPane anchorPane = fxmlLoader.load();

                OneExamenViewController_Front oneExamCF = fxmlLoader.getController();
                oneExamCF.setData(exams.get(i),this.c);

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
        List<Examen> examsL=new ArrayList();
          ExamenService  exS=ExamenService.getInstance();
          if(!"".equals(searchBar.getText())){
              grid.getChildren().clear();
        examsL= exS.displayAllAfterSearch(searchBar.getText()); 
         int column = 0;
        int row = 1;
        try {
                for (int i = 0; i <examsL.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/OneExamenView_Front.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                OneExamenViewController_Front oneExamC = fxmlLoader.getController();
                oneExamC.setData(examsL.get(i),this.c);

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
         FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/frontEventView.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               FrontEventViewController fev=Loader.getController();
               fev.c=this.c;
               fev.setNameUser();
               fev.afficherAll();
                Parent p=Loader.getRoot();
                Stage frontView=new Stage();
                Scene scene = new Scene(p);
                frontView.setScene(scene);
                Stage s=(Stage)this.eventsInEventView.getScene().getWindow();
                s.close();
                frontView.show();
    }

    @FXML
    private void examenview(MouseEvent event) {
           this.memePage(event);
    }

    @FXML
    private void deconnexion(MouseEvent event) {
         Upgradi u=new Upgradi();
        Stage s=(Stage)this.HB_deconnexion.getScene().getWindow();
        s.close();
        u.callStart();
    }

    @FXML
    private void trierLesExamens(MouseEvent event) {
          examsSorted.clear();
        examsSorted.addAll(listedata.getListExam());
        examsSorted.sort((o1, o2) -> -(int) (o1.getPrixE()-o2.getPrixE()));
       
        grid.getChildren().clear();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <examsSorted.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/OneExamenView_Front.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                

                OneExamenViewController_Front oneExamC = fxmlLoader.getController();
                oneExamC.setData(examsSorted.get(i),this.c);

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
    private void refreshPage(MouseEvent event) {
       this.memePage(event);
    }


    @FXML
    private void searchBytitre(MouseEvent event) {
         this.afficherAfterSearch();
    }
    
    
    @FXML
    private void recherche(KeyEvent event) {
         this.afficherAfterSearch();
    }

    @FXML
    private void afficher_mes_exams(ActionEvent event) {
        
         FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/myExams.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               MyExamsController myexC=Loader.getController();
               myexC.c=this.c;
               myexC.setNameUser();
               myexC.afficherAll();
                Parent p=Loader.getRoot();
                Stage myEvents;
               myEvents= (Stage) ((Node) event.getSource()).getScene().getWindow();
               Scene scene = new Scene(p);
                myEvents.setScene(scene);
                myEvents.show();
    }

    
    
}
