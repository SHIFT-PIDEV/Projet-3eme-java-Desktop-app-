/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import upgradi.Entities.Panier;
import upgradi.Services.PanierService;

/**
 *
 * @author Fedy
 */
public class ListData {
       private ObservableList<Panier> panier=FXCollections.observableArrayList();
       
       public ListData(){
           
        PanierService cserv=PanierService.getInstance();
        panier= cserv.displayAll();
        System.out.println(panier);
       }
        public ObservableList<Panier> getPanier(){
        return panier;
    }
}
