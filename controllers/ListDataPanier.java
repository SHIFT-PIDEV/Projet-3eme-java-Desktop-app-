/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Panier;
import java.util.ArrayList;
import java.util.List;
import services.PanierService;

/**
 *
 * @author Fedy
 */
public class ListDataPanier {
    private List<Panier> panier=new ArrayList();
    

    public ListDataPanier() {
        
        PanierService pserv=PanierService.getInstance();
        panier= pserv.displayAll();
          }
    
    public List<Panier> getPanier(){
        return panier;
    }
}
