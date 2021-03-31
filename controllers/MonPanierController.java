/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Client;
import entities.Panier;
import entities.cour;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import services.PanierService;
import services.courservice;

/**
 * FXML Controller class
 *
 * @author Fedy
 */
public class MonPanierController implements Initializable {

    @FXML
    private Text notifNumbre;
    @FXML
    private FontAwesomeIconView notif;
    @FXML
    private Text iconUserDef;
    @FXML
    private HBox dashbord;
    @FXML
    private HBox eventsInEventView;
    @FXML
    private TextField searchBar;
    @FXML
    private Button devFormateur;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Text nbrCourdansPanier;
    @FXML
    private Text totalPrix;
    @FXML
    private Button pc;
    @FXML
    private Button vc;
    @FXML
    private HBox courpage;

    
    private final ListDataPanier panier = new ListDataPanier();
    private final List<Panier> pan = new ArrayList<>();
    private final List<Panier> panierTri = new ArrayList<>();
    public Client c;
    public Panier p;
    public float prix ;
    @FXML
    private FontAwesomeIconView refresh_btn;

    public void setNameUser() {
        this.iconUserDef.setText(this.c.getNom() + " " + this.c.getPrenom());
        System.out.println(this.c);

    }

    private void memePage(Object event) {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/monPanier.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        MonPanierController fev = Loader.getController();
        fev.c = this.c;
        fev.setNameUser();
        fev.afficherAll();
        Parent p = Loader.getRoot();
        Stage frontView;
        if (event instanceof MouseEvent) {
            frontView = (Stage) ((Node) ((MouseEvent) event).getSource()).getScene().getWindow();
        } else {
            frontView = (Stage) ((Node) ((ActionEvent) event).getSource()).getScene().getWindow();
        }
        Scene scene = new Scene(p);
        frontView.setScene(scene);
        frontView.show();
    }

    public void afficherAll() {
        
        PanierService ies = PanierService.getInstance();
        List<Panier> list = new ArrayList<>();
        list = ies.displayByIdClient(this.c.getId());
        pan.addAll(list);
        System.out.println(pan.size());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < pan.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/Item.fxml"));
                HBox hbox = fxmlLoader.load();
                courservice cserv = courservice.getInstance();
                cour co = new cour();
                co = cserv.displayById(pan.get(i).getIdcour());
                ItemController ItemController = fxmlLoader.getController();
                ItemController.setData(co.getNom_cour(), co.getPrix(), co.getImage_v(), pan.get(i));
                //eventController.setLikeIconColor();
                prix = prix + co.getPrix();
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
        nbrCourdansPanier.setText("Nombre d'elements: " + String.valueOf(pan.size()));
        nbrCourdansPanier.setTextAlignment(TextAlignment.CENTER);
        totalPrix.setText(String.valueOf(prix) + "$");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void toEventShare(MouseEvent event) {
    }

    @FXML
    private void showDashbord(MouseEvent event) {
    }

    @FXML
    private void eventsView(MouseEvent event) {
    }

    @FXML
    private void deconnecter(MouseEvent event) {
    }

    @FXML
    private void refreshPage(MouseEvent event) {
        this.memePage(event);
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

    @FXML
    private void passerCommande(ActionEvent event) {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/CarteBancaire.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(CourController.class.getName()).log(Level.SEVERE, null, ex);
       }
        CarteBancaireController cb = Loader.getController();
        cb.prixTot = this.prix;
        Parent p = Loader.getRoot();
        Stage updateStage = new Stage();
        Scene scene = new Scene(p);
        updateStage.setScene(scene);
        updateStage.show();
    }

    @FXML
    private void viderCommande(ActionEvent event) {
        Panier ie = new Panier();
        PanierService ies = PanierService.getInstance();
        ies.ViderAll(this.c.getId());
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/monPanier.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MonPanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void trierLesItems(MouseEvent event) {
        panierTri.clear();
        panierTri.addAll(panier.getPanier());
        panierTri.sort((o1, o2) -> -(o1.getIdcour() - o2.getIdcour()));

        grid.getChildren().clear();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < panierTri.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/Item.fxml"));
                HBox hbox = fxmlLoader.load();
                courservice cserv = courservice.getInstance();
                cour co = new cour();
                co = cserv.displayById(panierTri.get(i).getIdcour());
                ItemController ItemController = fxmlLoader.getController();
                ItemController.setData(co.getNom_cour(), co.getPrix(), co.getImage_v(), panierTri.get(i));

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
    private void courspage(MouseEvent event) throws IOException {
    }

}
