/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.PaymentMethod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.PaymentService;

/**
 *
 * @author Fedy
 */
public class ListDataPay {
    private ObservableList<PaymentMethod> payment = FXCollections.observableArrayList();

    public ListDataPay() {

        PaymentService cserv = PaymentService.getInstance();
        payment = cserv.displayAll();
        System.out.println(payment);
    }

    public ObservableList<PaymentMethod> getPaymentMethod() {
        return payment;
    }
}
