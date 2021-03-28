/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Examen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.ExamenService;

/**
 *
 * @author mahdi
 */
public class ListeData {
     private ObservableList<Examen> listExam=FXCollections.observableArrayList();
       public ListeData() {
        
        ExamenService exS=ExamenService.getInstance();
        listExam= (ObservableList<Examen>) exS.displayAll();
        System.out.println(listExam);
    }
  

    public ObservableList<Examen> getListExam() {
        return listExam;
    }
    
}
