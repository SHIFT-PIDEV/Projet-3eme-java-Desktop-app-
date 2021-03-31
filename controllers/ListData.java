/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Event;
import java.util.ArrayList;
import java.util.List;
import services.EventS;

/**
 *
 * @author asus
 */
public class ListData {
     private List<Event> events=new ArrayList();
    

    public ListData() {
        
        EventS evs=EventS.getEventS();
        events= evs.displayAll();
          }
    
    public List<Event> getEvents(){
        return events;
    }
}
