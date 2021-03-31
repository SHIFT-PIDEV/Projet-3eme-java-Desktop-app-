/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Client;
import entities.cour;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.courservice;
import upgradi.Upgradi;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class FrontcourviewController implements Initializable {

    @FXML
    private Text iconUserDef;
    @FXML
    private HBox dashbord;
    @FXML
    private HBox eventsInEventView;
    @FXML
    private HBox cours;
    @FXML
    private FontAwesomeIconView refresh_btn;
    @FXML
    private TextField searchBar;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    private final listdat listdata = new listdat();
    private final List<cour> cour= new ArrayList<>();
        private final List<cour> coursTrier = new ArrayList<>();

    Client c=new Client();
    @FXML
    private HBox HB_deconnexion;
    @FXML
    private HBox HB_examen;
    public void setNameUser(){
              this.iconUserDef.setText(this.c.getNom()+" "+this.c.getPrenom());
              //System.out.println(this.c);
               //
          }

    /**
     * Initializes the controller class.
     */
//     private void memePage(Object event){
//              FXMLLoader Loader=new FXMLLoader();
//        Loader.setLocation(getClass().getResource("/views/Frontcourview.fxml"));
//        try {
//            Loader.load();  
//        } catch (IOException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//             FrontcourviewController fev=Loader.getController();
//               fev.c=this.c;
//               fev.setNameUser();
//         
//                Parent p=Loader.getRoot();
//                Stage frontView ;
//                if(event instanceof MouseEvent){
//                    frontView= (Stage) ((Node) ((MouseEvent)event).getSource()).getScene().getWindow();
//                }
//                else{
//                    frontView = (Stage) ((Node) ((ActionEvent)event).getSource()).getScene().getWindow();
//                }
//                Scene scene = new Scene(p);
//                frontView.setScene(scene);
//                frontView.show(); 
//          }
     public void afficherAll(){    
       cour.addAll(listdata.getcour());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <cour.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/cour2.fxml"));
                HBox hbox = fxmlLoader.load();

                Cour2Controller eventController = fxmlLoader.getController();
                eventController.setData(cour.get(i),this.c);
                eventController.setuserinfo(this.c);
        //        eventController.setLikeIconColor();
                
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
                grid.add(hbox, column++, row); 
                GridPane.setMargin(hbox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      public void afficherAfterSearch(){
         List<cour> eventsSearch=new ArrayList();
          courservice evs=courservice.getInstance();
          if(!"".equals(searchBar.getText())){
              grid.getChildren().clear();
        eventsSearch= evs.displayAllAfterSearch(searchBar.getText()); 
         int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <eventsSearch.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/cour2.fxml"));
                HBox hbox = fxmlLoader.load();
                
                Cour2Controller courC = fxmlLoader.getController();
                courC.c=this.c;
               
                courC.setData(eventsSearch.get(i),this.c);
               // eventController.setLikeIconColor();
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
                grid.add(hbox, column++, row); 
                GridPane.setMargin(hbox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
          }           
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        //this.afficherAll();
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
          Upgradi u=new Upgradi();
        Stage s=(Stage)this.HB_deconnexion.getScene().getWindow();
        s.close();
        u.callStart();
    }

    @FXML
    private void trierLesEvents(MouseEvent event) {
               coursTrier.clear();
        coursTrier.addAll(listdata.getcour());
        coursTrier.sort((o1, o2) -> -(int) (o1.getPrix()-o2.getPrix()));
       
        grid.getChildren().clear();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <coursTrier.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/cour2.fxml"));
                HBox hbox = fxmlLoader.load();

                Cour2Controller eventController = fxmlLoader.getController();
                eventController.setData(coursTrier.get(i),this.c);
             //   eventController.setLikeIconColor();

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
                grid.add(hbox, column++, row); 
                GridPane.setMargin(hbox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }

    @FXML
    private void refreshPage(MouseEvent event) {
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
                Stage frontView=new Stage();
                Scene scene = new Scene(p);
                frontView.setScene(scene);
                Stage s=(Stage)this.refresh_btn.getScene().getWindow();
                s.close();
                frontView.show();
    }

    @FXML
    private void recherche(KeyEvent event) {
        this.afficherAfterSearch();
    }

    @FXML
    private void searchByNameAndDesc(MouseEvent event) {
        this.afficherAfterSearch();
    }

    @FXML
    private void packg(MouseEvent event) {
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
                if(event instanceof MouseEvent){
                    frontView= (Stage) ((Node) ((MouseEvent)event).getSource()).getScene().getWindow();
                }
                else{
                    frontView = (Stage) ((Node) ((MouseEvent)event).getSource()).getScene().getWindow();
                }
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

    @FXML
    private void all_packet(MouseEvent event) {
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
    
    
}
