/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import entities.Client;
import entities.Panier;
import entities.PaymentMethod;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import services.PanierService;
import services.PaymentService;

/**
 * FXML Controller class
 *
 * @author Fedy
 */
public class CheckoutController implements Initializable {

    private final List<PaymentMethod> pm = new ArrayList<>();
    private ListDataPanier list = new ListDataPanier();
    private ListDataPay listdata = new ListDataPay();
    public PaymentMethod py;
    public Panier pa;
    public Client c;
    public float prix;
    LocalDate date = LocalDate.now();
    private final List<Panier> pan = new ArrayList<>();
    @FXML
    private GridPane grid;
    @FXML
    private Button btn_createcour;
    @FXML
    private Button btn_resetAll;
    @FXML
    private TableView<PaymentMethod> tablepayment;
    @FXML
    private TableColumn<PaymentMethod, ?> nom1;
    @FXML
    private TableColumn<PaymentMethod, ?> prenom1;
    @FXML
    private TableColumn<PaymentMethod, ?> email1;
    @FXML
    private TableColumn<PaymentMethod, ?> pays1;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        
        tablepayment.setItems(listdata.getPaymentMethod());
        nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom1.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email1.setCellValueFactory(new PropertyValueFactory<>("email"));
        pays1.setCellValueFactory(new PropertyValueFactory<>("pays"));

    }
 

    @FXML
    private void valider(ActionEvent event) throws MessagingException, SQLException, StripeException {
        System.out.println(prix);
        //Stripe API Auth & getting Client Infos
        PaymentMethod p = new PaymentMethod();
        Stripe.apiKey = "sk_test_51IUvWkGDv01GvjnlNOkQChbbj5HQVtFKplAjyhw4FrufGbRF3HPwL1cOAFJmvH0b7uRvNgiF7X7onkeHDRwRiPeH00qEINi8FO";
        CustomerCreateParams par = CustomerCreateParams.builder()
                .setEmail(email1.getCellData(0).toString())
                .setName(nom1.getCellData(0).toString() + " " + prenom1.getCellData(0).toString())
                .setPaymentMethod("pm_card_visa").setInvoiceSettings(
                CustomerCreateParams.InvoiceSettings.builder()
                        .setDefaultPaymentMethod("pm_card_visa")
                        .build()
        ).build();

        // Mailing Service
        Properties prop = System.getProperties();
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        Session newSession = Session.getDefaultInstance(prop, null);
        String emailsubject = "Order Confirmation";
        String emailbody = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n"
                + "<head>\n"
                + "<!--[if gte mso 9]>\n"
                + "<xml>\n"
                + "  <o:OfficeDocumentSettings>\n"
                + "    <o:AllowPNG/>\n"
                + "    <o:PixelsPerInch>96</o:PixelsPerInch>\n"
                + "  </o:OfficeDocumentSettings>\n"
                + "</xml>\n"
                + "<![endif]-->\n"
                + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "  <meta name=\"x-apple-disable-message-reformatting\">\n"
                + "  <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->\n"
                + "  <title></title>\n"
                + "  \n"
                + "    <style type=\"text/css\">\n"
                + "      a { color: #0000ee; text-decoration: underline; } @media (max-width: 480px) { #u_content_menu_1 .v-padding { padding: 5px 35px !important; } #u_content_menu_2 .v-padding { padding: 5px 38px 5px 32px !important; } #u_content_menu_4 .v-padding { padding: 5px 40px 5px 50px !important; } #u_content_menu_3 .v-padding { padding: 5px 40px 5px 55px !important; } }\n"
                + "@media only screen and (min-width: 660px) {\n"
                + "  .u-row {\n"
                + "    width: 640px !important;\n"
                + "  }\n"
                + "  .u-row .u-col {\n"
                + "    vertical-align: top;\n"
                + "  }\n"
                + "\n"
                + "  .u-row .u-col-50 {\n"
                + "    width: 320px !important;\n"
                + "  }\n"
                + "\n"
                + "  .u-row .u-col-100 {\n"
                + "    width: 640px !important;\n"
                + "  }\n"
                + "\n"
                + "}\n"
                + "\n"
                + "@media (max-width: 660px) {\n"
                + "  .u-row-container {\n"
                + "    max-width: 100% !important;\n"
                + "    padding-left: 0px !important;\n"
                + "    padding-right: 0px !important;\n"
                + "  }\n"
                + "  .u-row .u-col {\n"
                + "    min-width: 320px !important;\n"
                + "    max-width: 100% !important;\n"
                + "    display: block !important;\n"
                + "  }\n"
                + "  .u-row {\n"
                + "    width: calc(100% - 40px) !important;\n"
                + "  }\n"
                + "  .u-col {\n"
                + "    width: 100% !important;\n"
                + "  }\n"
                + "  .u-col > div {\n"
                + "    margin: 0 auto;\n"
                + "  }\n"
                + "}\n"
                + "body {\n"
                + "  margin: 0;\n"
                + "  padding: 0;\n"
                + "}\n"
                + "\n"
                + "table,\n"
                + "tr,\n"
                + "td {\n"
                + "  vertical-align: top;\n"
                + "  border-collapse: collapse;\n"
                + "}\n"
                + "\n"
                + "p {\n"
                + "  margin: 0;\n"
                + "}\n"
                + "\n"
                + ".ie-container table,\n"
                + ".mso-container table {\n"
                + "  table-layout: fixed;\n"
                + "}\n"
                + "\n"
                + "* {\n"
                + "  line-height: inherit;\n"
                + "}\n"
                + "\n"
                + "a[x-apple-data-detectors='true'] {\n"
                + "  color: inherit !important;\n"
                + "  text-decoration: none !important;\n"
                + "}\n"
                + "\n"
                + "@media (max-width: 480px) {\n"
                + "  .hide-mobile {\n"
                + "    display: none !important;\n"
                + "    max-height: 0px;\n"
                + "    overflow: hidden;\n"
                + "  }\n"
                + "\n"
                + "}\n"
                + "    </style>\n"
                + "  \n"
                + "  \n"
                + "\n"
                + "</head>\n"
                + "\n"
                + "<body class=\"clean-body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #e7e7e7\">\n"
                + "  <!--[if IE]><div class=\"ie-container\"><![endif]-->\n"
                + "  <!--[if mso]><div class=\"mso-container\"><![endif]-->\n"
                + "  <table style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #e7e7e7;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\n"
                + "  <tbody>\n"
                + "  <tr style=\"vertical-align: top\">\n"
                + "    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
                + "    <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #e7e7e7;\"><![endif]-->\n"
                + "    \n"
                + "\n"
                + "<div class=\"u-row-container\" style=\"padding: 20px 0px 0px;background-color: transparent\">\n"
                + "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 640px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n"
                + "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n"
                + "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 20px 0px 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:640px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n"
                + "      \n"
                + "<!--[if (mso)|(IE)]><td align=\"center\" width=\"320\" style=\"width: 320px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "<div class=\"u-col u-col-50\" style=\"max-width: 320px;min-width: 320px;display: table-cell;vertical-align: top;\">\n"
                + "  <div style=\"width: 100% !important;\">\n"
                + "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n"
                + "  \n"
                + "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "  <tbody>\n"
                + "    <tr>\n"
                + "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:20px 20px 15px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                + "        \n"
                + "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                + "  <tr>\n"
                + "    <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"left\">\n"
                + "      \n"
                + "      <h1 align=\"left\">Upgradi</h1> \n"
                + "      \n"
                + "    </td>\n"
                + "  </tr>\n"
                + "</table>\n"
                + "\n"
                + "      </td>\n"
                + "    </tr>\n"
                + "  </tbody>\n"
                + "</table>\n"
                + "\n"
                + "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n"
                + "  </div>\n"
                + "</div>\n"
                + "<!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "<!--[if (mso)|(IE)]><td align=\"center\" width=\"320\" style=\"width: 320px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "<div class=\"u-col u-col-50\" style=\"max-width: 320px;min-width: 320px;display: table-cell;vertical-align: top;\">\n"
                + "  <div style=\"width: 100% !important;\">\n"
                + "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n"
                + "  \n"
                + "<table class=\"hide-mobile\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "  <tbody>\n"
                + "    <tr>\n"
                + "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:25px 20px 15px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                + "        \n"
                + "  <div style=\"color: #000000; line-height: 140%; text-align: left; word-wrap: break-word;\">\n"
                + "    <p style=\"font-size: 14px; line-height: 140%; text-align: right;\"><span style=\"font-size: 16px; line-height: 22.4px; color: #95a5a6;\">"+ date+"</span></p>\n"
                + "  </div>\n"
                + "\n"
                + "      </td>\n"
                + "    </tr>\n"
                + "  </tbody>\n"
                + "</table>\n"
                + "\n"
                + "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n"
                + "  </div>\n"
                + "</div>\n"
                + "<!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "    </div>\n"
                + "  </div>\n"
                + "</div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 640px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n"
                + "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n"
                + "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:640px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n"
                + "      \n"
                + "<!--[if (mso)|(IE)]><td align=\"center\" width=\"640\" style=\"width: 640px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 640px;display: table-cell;vertical-align: top;\">\n"
                + "  <div style=\"width: 100% !important;\">\n"
                + "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n"
                + "  \n"
                + "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "  <tbody>\n"
                + "    <tr>\n"
                + "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:0px 10px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                + "        \n"
                + "  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 3px solid #BBBBBB;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
                + "    <tbody>\n"
                + "      <tr style=\"vertical-align: top\">\n"
                + "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
                + "          <span>&#160;</span>\n"
                + "        </td>\n"
                + "      </tr>\n"
                + "    </tbody>\n"
                + "  </table>\n"
                + "\n"
                + "      </td>\n"
                + "    </tr>\n"
                + "  </tbody>\n"
                + "</table>\n"
                + "\n"
                + "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "  <tbody>\n"
                + "    <tr>\n"
                + "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:40px 10px 20px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                + "        \n"
                + "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                + "  <tr>\n"
                + "    <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n"
                + "      \n"
                + "      <img align=\"center\" border=\"0\" src=\"https://img.bayengage.com/assets/1613615720006-tick (1).jpg\" alt=\"Image\" title=\"Image\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 100%;max-width: 106px;\" width=\"106\"/>\n"
                + "      \n"
                + "    </td>\n"
                + "  </tr>\n"
                + "</table>\n"
                + "\n"
                + "      </td>\n"
                + "    </tr>\n"
                + "  </tbody>\n"
                + "</table>\n"
                + "\n"
                + "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "  <tbody>\n"
                + "    <tr>\n"
                + "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:20px 10px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                + "        \n"
                + "  <div style=\"color: #000000; line-height: 140%; text-align: left; word-wrap: break-word;\">\n"
                + "    <p style=\"font-size: 14px; line-height: 140%; text-align: center;\"><span style=\"font-size: 18px; line-height: 25.2px; color: #4a4a4a;\"><strong>Thank you for placing your order Mr/Ms. " + nom1.getCellData(0).toString() + " " + prenom1.getCellData(0).toString() + " </strong></span></p>\n"
                + "  </div>\n"
                + "\n"
                + "      </td>\n"
                + "    </tr>\n"
                + "  </tbody>\n"
                + "</table>\n"
                + "\n"
                + "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "  <tbody>\n"
                + "    <tr>\n"
                //                + "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 20px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                //                + "        \n"
                //                + "  <div style=\"color: #000000; line-height: 140%; text-align: left; word-wrap: break-word;\">\n"
                //                + "    <p style=\"font-size: 14px; line-height: 140%; text-align: center;\"><span style=\"font-size: 12px; line-height: 16.8px;\"><strong><span style=\"color: #4a4a4a; line-height: 16.8px; font-size: 12px;\">This email is to confirm your recent order</span></strong></span></p>\n"
                //                + "  </div>\n"
                //                + "\n"
                //                + "      </td>\n"
                + "    </tr>\n"
                + "  </tbody>\n"
                + "</table>\n"
                + "\n"
                + "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n"
                + "  </div>\n"
                + "</div>\n"
                + "<!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "    </div>\n"
                + "  </div>\n"
                + "</div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 640px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #000000;\">\n"
                + "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n"
                + "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:640px;\"><tr style=\"background-color: #000000;\"><![endif]-->\n"
                + "      \n"
                + "<!--[if (mso)|(IE)]><td align=\"center\" width=\"640\" style=\"width: 640px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 640px;display: table-cell;vertical-align: top;\">\n"
                + "  <div style=\"width: 100% !important;\">\n"
                + "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n"
                + "\n"
                + "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n"
                + "  </div>\n"
                + "</div>\n"
                + "<!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "    </div>\n"
                + "  </div>\n"
                + "</div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 640px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n"
                + "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n"
                + "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:640px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n"
                + "      \n"
                + "<!--[if (mso)|(IE)]><td align=\"center\" width=\"640\" style=\"width: 640px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 640px;display: table-cell;vertical-align: top;\">\n"
                + "  <div style=\"width: 100% !important;\">\n"
                + "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n"
                + "  \n"
                + "<table id=\"u_content_menu_2\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "  <tbody>\n"
                + "    <tr>\n"
                + "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:20px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                + "        \n"
                + "<div class=\"menu\" style=\"text-align:center\">\n"
                + "<!--[if (mso)|(IE)]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"><tr><![endif]-->\n"
                + "\n"
                + "  <!--[if (mso)|(IE)]><td style=\"padding:5px 115px 5px 105px\"><![endif]-->\n"
                + "  \n"
                + "  \n"
                + "  <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "  \n"
                + "\n"
                + "<!--[if (mso)|(IE)]></tr></table><![endif]-->\n"
                + "</div>\n"
                + "\n"
                + "      </td>\n"
                + "    </tr>\n"
                + "  </tbody>\n"
                + "</table>\n"
                + "\n"
                + "<table id=\"u_content_menu_4\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "  <tbody>\n"
                + "    <tr>\n"
                + "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:20px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                + "        \n"
                + "<div class=\"menu\" style=\"text-align:center\">\n"
                + "<!--[if (mso)|(IE)]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"><tr><![endif]-->\n"
                + "\n"
                + "  <!--[if (mso)|(IE)]><td style=\"padding:5px 120px 5px 121px\"><![endif]-->\n"
                + "  \n"
                + "  <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "  \n"
                + "\n"
                + "  <!--[if (mso)|(IE)]><td style=\"padding:5px 120px 5px 121px\"><![endif]-->\n"
                + "  \n"
                + "  <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "  \n"
                + "\n"
                + "<!--[if (mso)|(IE)]></tr></table><![endif]-->\n"
                + "</div>\n"
                + "\n"
                + "      </td>\n"
                + "    </tr>\n"
                + "  </tbody>\n"
                + "</table>\n"
                + "\n"
                + "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "  <tbody>\n"
                + "    <tr>\n"
                + "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                + "        \n"
                + "  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 2px solid #BBBBBB;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
                + "    <tbody>\n"
                + "      <tr style=\"vertical-align: top\">\n"
                + "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
                + "          <span>&#160;</span>\n"
                + "        </td>\n"
                + "      </tr>\n"
                + "    </tbody>\n"
                + "  </table>\n"
                + "\n"
                + "      </td>\n"
                + "    </tr>\n"
                + "  </tbody>\n"
                + "</table>\n"
                + "\n"
                + "<table id=\"u_content_menu_3\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "  <tbody>\n"
                + "    <tr>\n"
                + "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:20px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                + "        \n"
                + "<div class=\"menu\" style=\"text-align:center\">\n"
                + "<!--[if (mso)|(IE)]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"><tr><![endif]-->\n"
                + "\n"
                + "  <!--[if (mso)|(IE)]><td style=\"padding:5px 120px 5px 127px\"><![endif]-->\n"
                + "  \n"
                + "  <span style=\"padding:5px 120px 5px 127px;display:inline;color:#444444;font-family:arial,helvetica,sans-serif;font-size:15px\" class=\"v-padding\">\n"
                + "    Votre Achat de " + prix + "$ est validé\n"
                + "  </span>\n"
                + "  \n"
                + "  <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "  \n"
                + "\n"
                + "  <!--[if (mso)|(IE)]><td style=\"padding:5px 120px 5px 127px\"><![endif]-->\n"
                + "  \n"
                + "  </span>\n"
                + "  \n"
                + "  <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "  \n"
                + "\n"
                + "<!--[if (mso)|(IE)]></tr></table><![endif]-->\n"
                + "</div>\n"
                + "\n"
                + "      </td>\n"
                + "    </tr>\n"
                + "  </tbody>\n"
                + "</table>\n"
                + "\n"
                + "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "  <tbody>\n"
                + "    <tr>\n"
                + "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 20px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                + "        \n"
                + "  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 2px solid #BBBBBB;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
                + "    <tbody>\n"
                + "      <tr style=\"vertical-align: top\">\n"
                + "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
                + "          <span>&#160;</span>\n"
                + "        </td>\n"
                + "      </tr>\n"
                + "    </tbody>\n"
                + "  </table>\n"
                + "\n"
                + "      </td>\n"
                + "    </tr>\n"
                + "  </tbody>\n"
                + "</table>\n"
                + "\n"
                + "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "  <tbody>\n"
                + "    <tr>\n"
                + "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:15px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                + "        \n"
                + "<div align=\"center\">\n"
                + "  <div style=\"display: table; max-width:140px;\">\n"
                + "  <!--[if (mso)|(IE)]><table width=\"140\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"border-collapse:collapse;\" align=\"center\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse; mso-table-lspace: 0pt;mso-table-rspace: 0pt; width:140px;\"><tr><![endif]-->\n"
                + "  \n"
                + "    \n"
                + "    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 15px;\" valign=\"top\"><![endif]-->\n"
                + "    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 15px\">\n"
                + "      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
                + "        <a href=\"https://facebook.com/\" title=\"Facebook\" target=\"_blank\">\n"
                + "          <img src=\"https://cdn.tools.unlayer.com/social/icons/circle/facebook.png\" alt=\"Facebook\" title=\"Facebook\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n"
                + "        </a>\n"
                + "      </td></tr>\n"
                + "    </tbody></table>\n"
                + "    <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "    \n"
                + "    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 15px;\" valign=\"top\"><![endif]-->\n"
                + "    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 15px\">\n"
                + "      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
                + "        <a href=\"https://twitter.com/\" title=\"Twitter\" target=\"_blank\">\n"
                + "          <img src=\"https://cdn.tools.unlayer.com/social/icons/circle/twitter.png\" alt=\"Twitter\" title=\"Twitter\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n"
                + "        </a>\n"
                + "      </td></tr>\n"
                + "    </tbody></table>\n"
                + "    <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "    \n"
                + "    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 0px;\" valign=\"top\"><![endif]-->\n"
                + "    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 0px\">\n"
                + "      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
                + "        <a href=\"https://instagram.com/\" title=\"Instagram\" target=\"_blank\">\n"
                + "          <img src=\"https://cdn.tools.unlayer.com/social/icons/circle/instagram.png\" alt=\"Instagram\" title=\"Instagram\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n"
                + "        </a>\n"
                + "      </td></tr>\n"
                + "    </tbody></table>\n"
                + "    <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "    \n"
                + "    \n"
                + "    <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "  </div>\n"
                + "</div>\n"
                + "\n"
                + "      </td>\n"
                + "    </tr>\n"
                + "  </tbody>\n"
                + "</table>\n"
                + "\n"
                + "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "  <tbody>\n"
                + "    <tr>\n"
                + "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:20px 20px 40px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
                + "        \n"
                + "  <div style=\"color: #000000; line-height: 140%; text-align: left; word-wrap: break-word;\">\n"
                + "    <p style=\"font-size: 14px; line-height: 140%; text-align: center;\">This email was sent <span style=\"color: #000000; font-size: 14px; line-height: 19.6px;\"><span style=\"font-size: 14px; line-height: 19.6px;\">By Upgradi</span>. </span></p>\n"
                + "<p style=\"font-size: 14px; line-height: 140%; text-align: center;\">To ensure delivery to your inbox (not bulk or junk folders), you can add <span style=\"color: #e03e2d; font-size: 14px; line-height: 19.6px;\">upgradi@gmail.com</span> to your address book or safe list.</p>\n"
                + "  </div>\n"
                + "\n"
                + "      </td>\n"
                + "    </tr>\n"
                + "  </tbody>\n"
                + "</table>\n"
                + "\n"
                + "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n"
                + "  </div>\n"
                + "</div>\n"
                + "<!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "    </div>\n"
                + "  </div>\n"
                + "</div>\n"
                + "\n"
                + "\n"
                + "    <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n"
                + "    </td>\n"
                + "  </tr>\n"
                + "  </tbody>\n"
                + "  </table>\n"
                + "  <!--[if mso]></div><![endif]-->\n"
                + "  <!--[if IE]></div><![endif]-->\n"
                + "</body>\n"
                + "\n"
                + "</html>";
        Message message = new MimeMessage(newSession);
        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email1.getCellData(0).toString()));
        } catch (AddressException ex) {
            Logger.getLogger(CheckoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        message.setSubject(emailsubject);
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(emailbody, "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        String fromuser = "upgradiapp@gmail.com";
        String pass = "upgradi2021";
        String emailhost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailhost, fromuser, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        long pr = (long) prix * 100;
        Customer customer = Customer.create(par);
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(pr)
                .setCurrency("USD")
                .setCustomer(customer.getId())
                .setDescription(list.getPanier().toString())
                .build();
        PaymentIntentConfirmParams confirm = PaymentIntentConfirmParams.builder()
                .setPaymentMethod("pm_card_visa")
                .build();
        PaymentIntent token = PaymentIntent.create(params);
        PaymentIntent confirmedIntent = token.confirm(confirm);
        System.out.println(confirmedIntent.getId());
        System.out.println(confirmedIntent.getStatus());
      
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/cmdvalide.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    
        PaymentService cserv = PaymentService.getInstance();
        cserv.ViderAll();
        PanierService ies = PanierService.getInstance();
        ies.delete();
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        PaymentService pserv = PaymentService.getInstance();
        pserv.ViderAll();
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/CarteBancaire.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(CourController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parent p = Loader.getRoot();
        Stage updateStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(p);
        updateStage.setScene(scene);
        updateStage.show();
    }

}
