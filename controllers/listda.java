/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.categorie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.categorieservice;

/**
 *
 * @author Aziz
 */
public class listda {
      private ObservableList<categorie> categorie=FXCollections.observableArrayList();
       
       public listda(){
           
          categorieservice cserv=categorieservice.getInstance();
        categorie= cserv.displayAll();
        System.out.println(categorie);
       }
        public ObservableList<categorie> getcategorie(){
        return categorie;
    }
    
}
