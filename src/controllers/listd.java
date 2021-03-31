/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.packge;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.packageservice;

/**
 *
 * @author Aziz
 */
public class listd {
       private ObservableList<packge> packge=FXCollections.observableArrayList();
       
       public listd(){
           
         packageservice  pa=packageservice.getInstance();
       packge =  (ObservableList<packge>) pa.displayAll();
        System.out.println(packge);
       }
        public ObservableList<packge> getpackge(){
        return packge;
    }
    
}
