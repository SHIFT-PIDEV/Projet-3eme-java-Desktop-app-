/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import entities.Coupon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.couponService;


/**
 *
 * @author pc
 */
public class ListeDatacoupon {
     private ObservableList<Coupon> listCoupons=FXCollections.observableArrayList();
     
     
     public ListeDatacoupon() {
        
       couponService exS=couponService.getInstance();
        listCoupons= (ObservableList<Coupon>) exS.displayAll();
        System.out.println(listCoupons);
    }

    public ObservableList<Coupon> getListCoupon() {
        return listCoupons;
    }
}
