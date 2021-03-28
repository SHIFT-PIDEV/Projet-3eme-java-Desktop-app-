/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.RandomStringUtils;
import upgradi.Entities.cour;
import upgradi.Services.courservice;

/**
 * FXML Controller class
 *
 * @author Fedy
 */
public class CoursController implements Initializable {

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
    private HBox panier;
    @FXML
    private ImageView icon_panier;
    @FXML
    private Label label_panier;
    @FXML
    private HBox pack;
    @FXML
    private ImageView icon_pack;
    @FXML
    private Label label_pack;
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
    private Button btn_add_img;
    @FXML
    private TextField duration;
    @FXML
    private ChoiceBox<String> niveau;
    @FXML
    private TextField prix;
    @FXML
    private TextArea description;
    @FXML
    private TextField formateur;
    @FXML
    private ComboBox categorie;
    @FXML
    private TextField nomcour;
    @FXML
    private ImageView imgview;
    @FXML
    private Button btn_add;

    /**
     * Initializes the controller class.
     */
    private ListData listdata = new ListData();
    @FXML
    private TableView<cour> afftable;
    @FXML
    private TableColumn<cour, Integer> id;
    @FXML
    private TableColumn<cour, String> nom_cour;
    @FXML
    private TableColumn<cour, String> categorie1;
    @FXML
    private TableColumn<cour, String> formateur1;
    @FXML
    private TableColumn<cour, String> description1;
    @FXML
    private TableColumn<cour, String> image;
    @FXML
    private TableColumn<cour,Float > prix1;
    @FXML
    private TableColumn<cour, String> niveau1;
    @FXML
    private TableColumn<cour, String> duration1;
    @FXML
    private ImageView image_view1;
    @FXML
    private Button btn_supp;
    @FXML
    private TextField t_id;
    @FXML
    private Button chercher;
    @FXML
    private TextField id_t;
    @FXML
    private Button modifier;
    @FXML
    private Button trie;
    @FXML
    private TextField searchBar;
    @FXML
    private Button btn_pie;
    @FXML
    private Label label_controle;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         niveau.setItems(FXCollections.observableArrayList("beginner","advanced","expert"));
        // categorie.setItems(FXCollections.observableArrayList("Devlopement","busness","faza"));
        //categorie.setItems((ObservableList<String>) courservice.getInstance().fillCombobox());
          String dbUsername = "root";
        String dbPassword = "";
        String dbURL = "jdbc:mysql://localhost:3306/produit";
        try {
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
          
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT  nom_cat FROM categorie");
            while (rs.next()) {
                //get string from db,whichever way 
                  categorie.getItems().addAll(rs.getString("nom_cat"));
            }

        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        
                afftable.setItems(listdata.getcour());

id.setCellValueFactory( new PropertyValueFactory<>("id"));
nom_cour.setCellValueFactory( new PropertyValueFactory<>("nom_cour"));
categorie1.setCellValueFactory( new PropertyValueFactory<>("categorie"));
formateur1.setCellValueFactory( new PropertyValueFactory<>("formateur"));
description1.setCellValueFactory( new PropertyValueFactory<>("description"));
image.setCellValueFactory( new PropertyValueFactory<>("image_v"));
prix1.setCellValueFactory( new PropertyValueFactory<>("prix"));
niveau1.setCellValueFactory( new PropertyValueFactory<>("niveau"));
duration1.setCellValueFactory( new PropertyValueFactory<>("duration"));
FilteredList<cour> filteredData = new FilteredList<>(listdata.getcour(), b -> true);  
       searchBar.textProperty().addListener(((observable,oldValue,newValue) -> {

       filteredData.setPredicate(e -> {
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }    
       String lowerCaseFilter = newValue.toLowerCase();
       if (e.getNom_cour().toLowerCase().contains(lowerCaseFilter) ) {
            return true; 
        }
         else  
          return false; 
        });
       }));
    SortedList<cour> sortedData = new SortedList<>(filteredData);  
    sortedData.comparatorProperty().bind(afftable.comparatorProperty());  
    afftable.setItems(sortedData);
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
    private void add_image(ActionEvent event) {
          FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
              WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
             imgview.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public static String saveToFileImageNormal(Image image)throws SQLException, IOException  {

        String ext = "jpg";
        File dir = new File("C:\\3A\\upgradi\\src\\upgradi\\images");
        String  name;
        name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
     BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;  
    }

    @FXML
    private void ajouter_cour(ActionEvent event) throws SQLException, IOException {
        String s ="";
         

        if  ( nomcour.getText().isEmpty()||categorie.getValue()==null|| formateur.getText().isEmpty() || description.getText().isEmpty()||prix.getText().isEmpty()||niveau.getValue()==null||duration.getText().isEmpty() ){
            if (nomcour.getText().isEmpty()) 
              s+= " NB! veuillez saisir le nom du cour\n";
            
           /* else 
           s+=" nom du cour saisie";*/
            if (categorie.getValue()==null)
                s+="  NB! veuillez saisir votre categorie\n";
         /*   else 
              s+= "categorie saisie";*/
            if (formateur.getText().isEmpty())
           s+= " NB! veuillez saisir le formateur \n";
            /*else 
                s+= "formateur saisie ";*/
            if (description.getText().isEmpty())
                s+= " NB! veuillez saisir votre description\n ";
         /*   else 
               s+="description saisie";*/
            if (prix.getText().isEmpty())
                  s+= " NB !veuillez saisir votre prix\n ";
           /* else 
                s+= "prix valider ";*/
           if (niveau.getValue()== null)
              s+=" NB! veuillez saisir votre niveau\n ";
           /* else 
               s+= " niveau valider";*/
            if (duration.getText().isEmpty())
                 s+=" NB ! veuillez saisir votre duration  ";
         /*   else 
                s+="duration valider ";*/
            if (!s.isEmpty())
            {
                label_controle.setText(s);
            }
                
                        
        }
        else 
        {
           
             Image image1=null;
             image1= imgview.getImage();
             String photo = null;
             
             photo = saveToFileImageNormal(image1);
             System.out.println(photo);
       
      
            
        
       cour c;
       c = new cour (nomcour.getText(), (String) categorie.getSelectionModel().getSelectedItem(),formateur.getText(),description.getText(),photo,Float.parseFloat(prix.getText()),niveau.getSelectionModel().getSelectedItem(),duration.getText());

       
           
       
            courservice cserv= courservice.getInstance();
            cserv.insert(c);
       
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("categorie insérée avec succés!");
        alert.show();
        nomcour.setText("");
        description.setText("");
       
        prix.setText("");
        duration.setText("");
        imgview.setImage(image1);
        
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/upgradi/Views/cours.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CoursController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     
    }

    @FXML
    private void handelfonct(MouseEvent event)throws Exception {
          cour course=afftable.getItems().get(afftable.getSelectionModel().getSelectedIndex());
          String photo;
          photo = course.getImage_v();
          Image imageURL = new Image("file:///C:/3A/upgradi/src/upgradi/images/"+photo);
          image_view1.setImage(imageURL);
    }

    @FXML
    private void supp_cour(ActionEvent event) {
            int s=Integer.valueOf(t_id.getText());
            courservice cserv= courservice.getInstance();
            cserv.delete(s);
                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("categorie supprimer avec succés!");
        alert.show();
        t_id.setText("");
        
           try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/upgradi/Views/cours.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CoursController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void chercher_cour(ActionEvent event) {
            int id1=Integer.valueOf(id_t.getText());
        courservice es;
        es=courservice.getInstance();
        cour e=es.displayById(id1);
        id_t.setText(String.valueOf(e.getId()));
        nomcour.setText(e.getNom_cour());
        categorie.setValue(e.getCategorie());
        formateur.setText(e.getFormateur());
        description.setText(e.getDescription());
             Image imageURL= new Image("file:///C:/3A/upgradi/src/upgradi/images/" + e.getImage_v());
         imgview.setImage(imageURL);
         prix.setText(String.valueOf(e.getPrix()));
         niveau.setValue(e.getNiveau());
         duration.setText(e.getDuration());
         //niveau1.setText(e.);
        
    }

    @FXML
    private void modifier_cour(ActionEvent event) throws SQLException, IOException { 
         cour e=new cour();
                Image image1=null;
             image1= imgview.getImage();
             String photo = null;
             
             photo = saveToFileImageNormal(image1);
             System.out.println(photo);
        
        e.setId(Integer.parseInt(id_t.getText()));
        e .setNom_cour(nomcour.getText());
        e.setCategorie((String) categorie.getSelectionModel().getSelectedItem());
        e.setFormateur(formateur.getText());
        
        e.setDescription(description.getText());
         e.setImage_v(photo);
         e.setPrix(Float.valueOf(prix.getText()));
         e.setNiveau(niveau.getSelectionModel().getSelectedItem());
         e.setDuration(duration.getText());
         
         
         
        
      
        courservice es= courservice.getInstance();
        es.update(e);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("categorie Modifier avec succés!");
        alert.show();
          try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/upgradi/Views/cours.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CoursController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void Tri(ActionEvent event) {
             nom_cour.setSortType(TableColumn.SortType.DESCENDING);
        afftable.getSortOrder().add(nom_cour);
        afftable.sort();
    }

    @FXML
    private void pie(ActionEvent event) {
         try {
                System.out.println("testttttttttttttt");
                Parent pagePieChart=FXMLLoader.load(getClass().getResource("/upgradi/Views/PieChartView.fxml"));
                Scene scene=new Scene(pagePieChart);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CoursController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        
    


     
    
}
