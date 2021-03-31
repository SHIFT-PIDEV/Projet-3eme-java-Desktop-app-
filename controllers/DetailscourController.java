/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.darkprograms.speech.translator.GoogleTranslate;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.cour;
import java.io.File;
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
import services.courservice;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class DetailscourController implements Initializable {

    @FXML
    private Text courname;
    @FXML
    private ImageView courPic;
    @FXML
    private Text niveau;
    @FXML
    private Text categorie;
    @FXML
    private TextArea desc;
    @FXML
    private TextField formateur;
    @FXML
    private TextField duree;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox<String> langue;
  private final listdat listdata = new listdat();
  public cour cours;
   private ObservableList<cour> cour=FXCollections.observableArrayList();
    public void setinfoCour(cour c)
 {
      courservice es= courservice.getInstance();
        courname.setText(c.getNom_cour());
        formateur.setText(c.getFormateur());
        desc.setText(c.getDescription());
        categorie.setText(c.getCategorie());
        niveau.setText(c.getNiveau());
         String photo;
          photo = cours.getImage_v();
          Image imageURL = new Image("file:///D:/--Etudes--/3eme/S2/PI-DEV/JAVA/upgradi skander/upgradi/src/image/"+photo);
          courPic.setImage(imageURL);
      prix.setText(Float.toString(cours.getPrix()));
      duree.setText(cours.getDuration());

      
     
 }
 
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                 langue.setItems(FXCollections.observableArrayList("Arabe","Anglais","Espagniol"));


    }   


    @FXML
    private void generatepdf(ActionEvent event) {
        try {
            String dir= System.getProperty("user.dir");
            File name =new File(dir+"\\src\\PDFS\\"+cours.getNom_cour()+".pdf");
            System.out.println(dir);
          //  File droppath = new File (dir + "\\upgradi\\src\\PDFS"+cours.getNom_cour()+".pdf");
            
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(name));
            document.open();
            Paragraph title = new Paragraph(cours.getNom_cour());
            Paragraph description = new Paragraph(cours.getDescription());
            Paragraph formateur = new Paragraph(cours.getFormateur());
            
            Paragraph header1 = new Paragraph("COPYRIGHT : UPGRADI");
            Paragraph titleheader = new Paragraph("cour title");
            Paragraph descriptionheader = new Paragraph("cour description");
            Paragraph formateure = new Paragraph("formateur nom");
            Paragraph imageheader = new Paragraph("cour images :");
            
            document.addTitle("UPGRADI COUR:");
            document.add(header1);
            document.add(titleheader);
            document.add(title);
            document.add(descriptionheader);
            document.add(description);
            document.add(formateure);
            document.add(formateur);
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
    private void traduire(ActionEvent event) throws IOException {
         try {
			//English to IGBO
                        if (langue.getSelectionModel().getSelectedItem()=="Arabe"){
                            desc.setText(GoogleTranslate.translate("ar", cours.getDescription()));
                            
                            
                        }
                        else if (langue.selectionModelProperty().getValue().getSelectedItem()=="Anglais")
                        {
                             desc.setText(GoogleTranslate.translate("en", cours.getDescription()));
                        }
                        else if (langue.selectionModelProperty().getValue().getSelectedItem()=="Espagniol")
                        {
                               desc.setText(GoogleTranslate.translate("es", cours.getDescription()));
                        }
                        else 
                        {
                             desc.setText(GoogleTranslate.translate("fr", cours.getDescription()));
                        }
                            
                        
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }


  
}

   // @FXML
   /* private void speak(ActionEvent event) {
          InputStream sound = null;
                try {
                    System.out.println("Hello World!");
                    Audio audio = Audio.getInstance();
                    sound = audio.getAudio("Hello World", Language.ENGLISH);
                    audio.play(sound);
                } catch (IOException | JavaLayerException ex) {
                    Logger.getLogger(DetailscourController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        sound.close();
                    } catch (IOException ex) {
                        Logger.getLogger(DetailscourController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }*/
    
 
        //desc.setText(GoogleTranslate.translate("en", cours.getDescription()));
//   try {
//			//English to IGBO
//			
//			
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

