/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Coupon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.couponService;

/**
 *
 * @author pc
 */
public class ListeData {
     private ObservableList<Coupon> listExam=FXCollections.observableArrayList();
     
     
     public ListeData() {
        
       couponService exS=couponService.getInstance();
        listExam= (ObservableList<Coupon>) exS.displayAll();
        System.out.println(listExam);
    }

    public ObservableList<Coupon> getListExam() {
        return listExam;
    }
}