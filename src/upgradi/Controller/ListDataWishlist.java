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
import upgradi.Services.WishlistService;

/**
 *
 * @author Fedy
 */
public class ListDataWishlist {
       private ObservableList<Panier> panier=FXCollections.observableArrayList();
       
       public ListDataWishlist(){
           
        WishlistService wserv=WishlistService.getInstance();
        Panier p = null;
        panier= wserv.displayAll(p);
        System.out.println(panier);
       }
        public ObservableList<Panier> getPanier(){
        return panier;
    }
}
