/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DemandeDao;
import dao.ReclamationDao;
import entities.Demande;
import entities.Event;
import entities.Reclamation;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;



/**
 *
 * @author wiemhjiri
 */
public class ListData2 {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<Reclamation> recla=FXCollections.observableArrayList();
    private ObservableList<Demande> recla2=FXCollections.observableArrayList();

    public ListData2() {
        
        ReclamationDao pdao=ReclamationDao.getInstance();
        recla= pdao.displayAll();
         DemandeDao pdao2=DemandeDao.getInstance();
        recla2= pdao2.displayAll();
    }
    
    public ObservableList<Reclamation>getrecla(){
        return recla;
    }
    public ObservableList<Demande>getrecla2(){
        return recla2;
    }  

    Collection<? extends Event> getEvents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
