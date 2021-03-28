/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import upgradi.Entities.cour;
import upgradi.Services.courservice;

/**
 *
 * @author Aziz
 */
public class ListData {
        private ObservableList<cour> cour=FXCollections.observableArrayList();
       
       public ListData(){
           
          courservice cserv=courservice.getInstance();
        cour= cserv.displayAll();
        System.out.println(cour);
       }
        public ObservableList<cour> getcour(){
        return cour;
    }
    
}
