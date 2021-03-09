/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import upgradi.Entities.categorie;
import upgradi.Services.categorieservice;

/**
 *
 * @author Aziz
 */
public class listaData {
        private ObservableList<categorie> categorie=FXCollections.observableArrayList();
       
       public listaData(){
           
          categorieservice cserv=categorieservice.getInstance();
        categorie= cserv.displayAll();
        System.out.println(categorie);
       }
        public ObservableList<categorie> getcategorie(){
        return categorie;
    }
}
