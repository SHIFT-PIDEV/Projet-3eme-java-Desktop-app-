/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.EventS;

/**
 *
 * @author asus
 */
public class ListData {
     private ObservableList<Event> events=FXCollections.observableArrayList();

    public ListData() {
        
        EventS evs=EventS.getEventS();
        events= evs.displayAll();
        System.out.println(events);
    }
    
    public ObservableList<Event> getEvents(){
        return events;
    }
}
