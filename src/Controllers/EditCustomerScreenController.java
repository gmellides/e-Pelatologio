/*
 * 
 */
package Controllers;

import CoreObjects.Customer;
import CoreServices.EditCustomerServices;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class EditCustomerScreenController implements Initializable {
       EditCustomerServices Action = new EditCustomerServices();
       String SelectedID;
       
    // = Components Decleration ========
       @FXML
       private Button btnClose;
       @FXML
       private TextField txtFirstNameEdit;
       @FXML
       private TextField txtLastNameEdit;
       @FXML
       private TextField txtPhoneEdit;
       @FXML
       private TextField txtAddressEdit;
       @FXML
       private TextField txtCityEdit;
       @FXML
       private TextField txtPostalEdit;
       @FXML
       private TextField txtCompanyEdit;
       @FXML
       private TextField txtMailEdit;      
    // ================================

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       DataToComponents();
    }    
    
    // Operations Action ===================================
        public void DataToComponents(){          
          Map SelectedData = Action.GetSelectedData();
          txtFirstNameEdit.setText(SelectedData.get("Cfirstname").toString());
          txtLastNameEdit.setText(SelectedData.get("Clastname").toString());
          txtPhoneEdit.setText(SelectedData.get("Cphone").toString());
          txtAddressEdit.setText(SelectedData.get("Caddress").toString());
          txtCityEdit.setText(SelectedData.get("Ccity").toString());
          txtPostalEdit.setText(SelectedData.get("Cpostalcode").toString());
          txtCompanyEdit.setText(SelectedData.get("Ccompany").toString());
          txtMailEdit.setText(SelectedData.get("Cmail").toString());
          SelectedID = SelectedData.get("CID").toString();
        } // DataToComponents()
    // ===================================================== 
    
    
    // Components Action ===================================
        
        // Αντιδραση Κουμπιου κλεισηματος.    
        @FXML
        public void btnCloseEdit(ActionEvent event){
            Stage thisstage = (Stage) btnClose.getScene().getWindow();
            thisstage.close();
        }
        // Αντιδραση κουμπιου Αποθυκευσης.
        @FXML
        public void btnSaveEdit(ActionEvent event){
           if(NullCheck()){
            Customer NewInfos = new Customer();
                NewInfos.SetFirstName(txtFirstNameEdit.getText());
                NewInfos.SetLastName(txtLastNameEdit.getText());
                NewInfos.SetCity(txtCityEdit.getText());
                NewInfos.SetPhone(txtPhoneEdit.getText());
                NewInfos.SetPostalCode(Integer.parseInt(txtPostalEdit.getText()));
                NewInfos.SetCompany(txtCompanyEdit.getText());
                NewInfos.SetAddress(txtAddressEdit.getText());
                NewInfos.SetMail(txtMailEdit.getText());         
            Action.UpdateCustomer(SelectedID,NewInfos);
                Stage thisstage = (Stage) btnClose.getScene().getWindow();
                thisstage.close();
           }
           else{
              Alert SuccessMessage= new Alert(Alert.AlertType.WARNING);
                 SuccessMessage.setTitle("Warning");
                 SuccessMessage.setHeaderText("Fill all fields");
                 SuccessMessage.setContentText("Please fill all form filds. if you don't have some informations you must type (-) in case to continue");
                 SuccessMessage.show();
           }
        }

        @FXML
        public void PostalCodeValid(KeyEvent evt,ActionEvent event){
            
        }
    // ===================================================== 
        
        // Ελεγχει εαν ολα τα πεδία ειναι συμπληρομένα.
        public boolean NullCheck(){
            if(txtFirstNameEdit.getText().isEmpty()||txtLastNameEdit.getText().isEmpty()||
               txtCityEdit.getText().isEmpty()||txtPhoneEdit.getText().isEmpty()||txtPostalEdit.getText().isEmpty()||
               txtCompanyEdit.getText().isEmpty()||txtAddressEdit.getText().isEmpty()||txtMailEdit.getText().isEmpty())
            {
                return false;
            }else{
                return true;
            }
        }

}