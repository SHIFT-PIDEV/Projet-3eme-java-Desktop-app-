/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Examen;
import entities.InscripExam;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import services.EventS;
import services.ExamenService;
import services.InscriptionExService;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class OneExamenViewController implements Initializable {

    @FXML
    private Text dateLabel;
    @FXML
    private ImageView detail;
    @FXML
    private Text Titre_examen;
    @FXML
    private Button modifButton;
    @FXML
    private Button suppButton;
    @FXML
    private Button inscritsButton;
    @FXML
    private TextArea titre_examen2;
    @FXML
    private TextField date_ex;
    @FXML
    private TextField prix_ex;
    @FXML
    private Text link_ex;
    @FXML
    private TextField ID_ex;
    @FXML
    private WebView quiz_view;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    
    private Examen  exam;
    public void setData(Examen ex) {
        this.exam = ex;
         
        ID_ex.setText(toString().valueOf(exam.getIdE()));
        titre_examen2.setText(exam.getTitreE());
        prix_ex.setText(String.valueOf(exam.getPrixE()));
        date_ex.setText(exam.getDate().toString());
        link_ex.setText(exam.getSupport());
        
        Titre_examen.setText(exam.getTitreE());
        dateLabel.setText(exam.getDate().toString());
        
        final WebEngine web = quiz_view.getEngine();
       String urlweb = exam.getSupport();
       web.load(urlweb);
        
     
    }
    

    @FXML
    private void modifier_examen(ActionEvent event) {
         FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/add_examen.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(EventsViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
              Add_examenController exC=Loader.getController();
              exC.setetxt(exam);
                Parent p=Loader.getRoot();
                Stage updateStage=new Stage();
                Scene scene = new Scene(p);
                updateStage.setScene(scene);
                updateStage.show();
        
        
    }

    @FXML
    private void supprimer_examen(ActionEvent event) {
         ExamenService es ;
        es=ExamenService.getInstance();
        es.delete(this.exam.getIdE());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Examen supprimée avec succés!");
        alert.show();
        
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
    private void lesInscriptions(ActionEvent event) {
         InscriptionExService iex= InscriptionExService.getInstance();
         iex.displayAllIdExam(this.exam.getIdE());
         List<InscripExam> listinsc= new ArrayList<>(); 
         listinsc=iex.displayAllIdExam(this.exam.getIdE());
         System.out.println(listinsc);
         System.out.println(listinsc.size());

        
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/inscriptionEXBack.fxml"));
        try {
            loader.load();
           
               
        } catch (IOException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
         inscriptionEXBackController ilc=loader.getController();
               ilc.setText(listinsc.size(),listinsc);
               ilc.afficher();
        Parent p=loader.getRoot();
                Stage lesInscrits=new Stage();
                Scene scene = new Scene(p);
                lesInscrits.setScene(scene);
                lesInscrits.show();
    }
    
    

    @FXML
    private void Go_to_Quiz(MouseEvent event)   throws IOException {
        //System.out.println("hhhhhhhhhhhhhhhhhhh");
         String exLink=link_ex.getText();
                
                   System.out.println(exLink);
                       Desktop d=Desktop.getDesktop();
           try {
               d.browse(new URI(exLink));
           } catch (URISyntaxException ex) {
               Logger.getLogger(OneExamenViewController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}
