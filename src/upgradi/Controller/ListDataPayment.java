
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgradi.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import upgradi.Entities.PaymentMethod;
import upgradi.Services.PaymentService;

/**
 *
 * @author Fedy
 */
public class ListDataPayment {
       private ObservableList<PaymentMethod> payment=FXCollections.observableArrayList();
       
       public ListDataPayment(){
           
        PaymentService cserv=PaymentService.getInstance();
        payment= cserv.displayAll();
        System.out.println(payment);
       }
        public ObservableList<PaymentMethod> getPaymentMethod(){
        return payment;
    }
}