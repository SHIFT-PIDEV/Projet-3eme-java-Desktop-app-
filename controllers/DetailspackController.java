/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.darkprograms.speech.translator.GoogleTranslate;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.packge;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.controlsfx.control.Notifications;
import services.packageservice;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class DetailspackController implements Initializable {

    @FXML
    private Text courname;
    @FXML
    private ImageView courPic;
    @FXML
    private Text categorie;
    @FXML
    private TextArea desc;
    @FXML
    private TextField nbcour;
    @FXML
    private TextField duree;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox<String> langue;

     /**
     * Initializes the controller class.
     */
                 private final listd listdata = new listd();
                  private ObservableList<packge> pckge=FXCollections.observableArrayList();
                        public packge packges;
             public void setinfoCour(packge c){
      packageservice es= packageservice.getInstance();
        courname.setText(c.getNompackage());
        nbcour.setText(Integer.toString(packges.getNbr_cour()));
        desc.setText(c.getDescription());
        categorie.setText(c.getCategorie());
        
         String photo;
          photo = packges.getImage();
          Image imageURL = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/image/"+photo);
          courPic.setImage(imageURL);
      prix.setText(Float.toString(packges.getPrix()));
      duree.setText(packges.getDuree());

      
     
 }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
      langue.setItems(FXCollections.observableArrayList("Arabe","Anglais","Espagniol"));

    }    

    @FXML
    private void generatepdf(ActionEvent event) throws FileNotFoundException, DocumentException {
        try {
            String dir= System.getProperty("user.dir");
            File name =new File(dir+"\\src\\PDFS\\"+packges.getNompackage()+".pdf");
            System.out.println(dir);
          //  File droppath = new File (dir + "\\upgradi\\src\\PDFS"+cours.getNom_cour()+".pdf");
            
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(name));
            document.open();
            Paragraph title = new Paragraph(packges.getNompackage());
            Paragraph description = new Paragraph(packges.getDescription());
            Paragraph nbcour = new Paragraph(packges.getNbr_cour());
            
            Paragraph header1 = new Paragraph("COPYRIGHT : UPGRADI");
            Paragraph titleheader = new Paragraph("package title");
            Paragraph descriptionheader = new Paragraph("package description");
            Paragraph nbcoure = new Paragraph("Nombre de cour");
            Paragraph imageheader = new Paragraph("package images :");
            
            document.addTitle("UPGRADI COUR:");
            document.add(header1);
            document.add(titleheader);
            document.add(title);
            document.add(descriptionheader);
            document.add(description);
            document.add(nbcoure);
            document.add(nbcour);
            document.add(imageheader);
            document.close();
            
            Notifications.create()
                 .title("pdf generer")
                 .text("pdf generer  avec success")
                 .darkStyle().position(Pos.BOTTOM_RIGHT).showConfirm();
           // DbxRequestConfig config = DbxRequestConfig.newBuilder
           }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println(ex);
        }
    }

    @FXML
    private void traduire(ActionEvent event) {
        try{
             if (langue.getSelectionModel().getSelectedItem()=="Arabe"){
                            desc.setText(GoogleTranslate.translate("ar", packges.getDescription()));
                            
                            
                        }
                        else if (langue.selectionModelProperty().getValue().getSelectedItem()=="Anglais")
                        {
                             desc.setText(GoogleTranslate.translate("en", packges.getDescription()));
                        }
                        else if (langue.selectionModelProperty().getValue().getSelectedItem()=="Espagniol")
                        {
                               desc.setText(GoogleTranslate.translate("es", packges.getDescription()));
                        }
                        else 
                        {
                             desc.setText(GoogleTranslate.translate("fr", packges.getDescription()));
                        }
                            
                        
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
    }

