/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import upgradi.Entities.cour;
import upgradi.Services.Iservice;
import upgradi.Services.courservice;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class PieChartViewController implements Initializable {

    @FXML
    private PieChart pieChart;
    ObservableList<PieChart.Data> list=FXCollections.
            observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            courservice  pdao=courservice.getInstance();
        List<cour> co=pdao.displayAll();
        
        for(cour p:co) {
           
            
            list.addAll(
                new PieChart.Data(p.getCategorie(), 12.0)             
        );
        
        pieChart.setAnimated(true);
        pieChart.setData(list);
        } 
    }
}
    

