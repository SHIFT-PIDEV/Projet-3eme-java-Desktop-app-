/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.cour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.courservice;

/**
 *
 * @author Aziz
 */
public class listdat {
         private ObservableList<cour> cour=FXCollections.observableArrayList();
       
       public listdat(){
           
          courservice cserv=courservice.getInstance();
        cour= cserv.displayAll();
        System.out.println(cour);
       }
        public ObservableList<cour> getcour(){
        return cour;
    }
    
}
