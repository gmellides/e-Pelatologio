/*
 * Main Screen Components Controller 
 * FXML : MainScreen.fxml
 */
package Controllers;

import CoreObjects.Customer;
import CoreServices.MainScreenServices;
import CoreServices.DateTimeServices;
import CoreServices.DeleteCustomerService;
import CoreServices.EditCustomerServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.Map;
import java.util.Optional;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.image.Image;

public class MainScreenController implements Initializable {
    // ===== Objects Decleration ========== 
        DateTimeServices DateTime = new DateTimeServices();; 
        Timer ClockRefresh = new java.util.Timer();
        MainScreenServices DataOperations = new MainScreenServices();
        Map SelectedData;     
    // ====================================
    
    //==== XML Components decleration =====
        // Clock Lable
        @FXML
        private Label lblClock;
        // Date Lable
        @FXML
        private Label lblDate;
       
    // ====================================
        
    // ====== Table View - Columns ========  
        // ** Custumer Table **
        @FXML
        private TableView<Map> tblCustomers;
        // ** Customer  Columns **
        @FXML
        private TableColumn ColId;
        @FXML
        private TableColumn ColFirstName;
        @FXML
        private TableColumn ColLastName;
        @FXML
        private TableColumn ColPhone;
        @FXML
        private TableColumn ColAddress;
        @FXML
        private TableColumn ColCity;
        @FXML
        private TableColumn ColPostal;
        @FXML
        private TableColumn ColCompany;
        @FXML
        private TableColumn ColMail;         
    // ==================================== 
    
    //======= Insert new Customer =========
       @FXML
       private TextField txtCFirstName; 
       @FXML
       private TextField txtCLastName;
       @FXML
       private TextField txtCPhone;
       @FXML
       private TextField txtCAddress;
       @FXML
       private TextField txtCCity;
       @FXML
       private TextField txtCPostal;
       @FXML
       private TextField txtCCompany;
       @FXML
       private TextField txtCMail;
    // ====================================   
   
       
    // = Default Method to Initiallize panel =  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Κλήση Μεθόδου για συμπληρωση πίνακα
         FillCustomers();
      
        // ανανεωνει την ημερομινια και την ωρα ανα 1 ms
        ClockRefresh.schedule(new TimerTask() {
                @Override
                public void run() {
                 Platform.runLater(() -> {
                     // Οριζει την ημερομινια 
                     lblDate.setText(DateTime.Date());
                     // Οριζει την ωρα
                     lblClock.setText(DateTime.Time()); 
                 });
               }
         }, 0, 1);     
        }    
    // =====================================
    
    //== Components Action decleration ==
        
        // τερματίζει την εφαρμογή.
        @FXML
        private void btnExitAction(ActionEvent event) {
            System.exit(0);
        } // btnExitAction()
        
        // Καθαριζει τα πεδία στην καρτελα καταχώρησης
        @FXML
        public void btnClearComp(ActionEvent event){
            ClearComponents();
        } // btnClearComp()
        
        // Αποθυκεύει τα στοιχεία στην βαση δεδομένων.
        @FXML
        public void btnSaveCust(ActionEvent event){
           if(NullCheck()){
            Customer NewCustomer = new Customer();
              NewCustomer.SetFirstName(txtCFirstName.getText());
              NewCustomer.SetLastName(txtCLastName.getText());
              NewCustomer.SetPhone(txtCPhone.getText());
              NewCustomer.SetAddress(txtCAddress.getText());
              NewCustomer.SetCity(txtCCity.getText());
              NewCustomer.SetPostalCode(Integer.parseInt(txtCPostal.getText()));
              NewCustomer.SetCompany(txtCCompany.getText());
              NewCustomer.SetMail(txtCMail.getText());
              
             DataOperations.InsertCustumer(NewCustomer);
             DataOperations = null;
             ClearComponents();
             
             Alert SuccessMessage= new Alert(AlertType.INFORMATION);
                SuccessMessage.setTitle("Success");
                SuccessMessage.setHeaderText("Successfully saved");
                SuccessMessage.setContentText("Customer is successfully saved ");
                SuccessMessage.show();
           }else{    
              Alert SuccessMessage= new Alert(AlertType.WARNING);
             SuccessMessage.setTitle("Warning");
             SuccessMessage.setHeaderText("Fill all the fields");
             SuccessMessage.setContentText("Please fill all form filds. if you don't have some informations you must type (-) in case to continue");
             SuccessMessage.show(); 
           }
        }// btnSaveCust()
        
        @FXML
        public void EditCnt(ActionEvent event) throws IOException{
            SelectedData = tblCustomers.getSelectionModel().getSelectedItem();     
            EditCustomerServices f = new EditCustomerServices();
            f.SetMap(SelectedData);
          
          Parent root = FXMLLoader.load(getClass().getResource("/GUI/EditCustomerScreen.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setHeight(402);
            stage.setWidth(683);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
              @Override
              public void handle(WindowEvent we) {
                 stage.hide();
              }
            });
            stage.setTitle("Edit Customer");
            stage.setScene(scene);
            stage.setResizable(false);
            Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
            stage.getIcons().add(icon);
          stage.show();
        }
        
        @FXML
        public void DeleteCnt(ActionEvent event){
            Alert ConfirmationDialog = new Alert(AlertType.CONFIRMATION);
            ConfirmationDialog.setTitle("Information");
            ConfirmationDialog.setHeaderText("Confirm Customer Delete");
            ConfirmationDialog.setContentText("Are you sure that you want to delete selected customer?");
            Optional<ButtonType> Selection = ConfirmationDialog.showAndWait();
            if (Selection.get() == ButtonType.OK){
                Map SelectedCustomer = tblCustomers.getSelectionModel().getSelectedItem();
                DeleteCustomerService Action = new DeleteCustomerService();
                Action.SetSelctedID(String.valueOf(SelectedCustomer.get("CID")));
                if (Action.DeleteCustomer(Action.GetSelectedID())){
                    FillCustomers();
                }
            } else {
              ConfirmationDialog.close();
            }
        }
    //===================================
    
    //====== Operation methods =========
        // Ανακτα τα δεδομένα της Βάσης δεδομενων και τα βαζει στον πινακα.
        @FXML
        public void FillCustomers() {
            DataOperations = new MainScreenServices();       
            tblCustomers.setItems(DataOperations.GetCustomerData());
            tblCustomers.setEditable(false); 
               ColId.setCellValueFactory(new MapValueFactory("CID"));
               ColFirstName.setCellValueFactory(new MapValueFactory("Cfirstname"));
               ColLastName.setCellValueFactory(new MapValueFactory("Clastname"));
               ColPhone.setCellValueFactory(new MapValueFactory("Cphone"));
               ColAddress.setCellValueFactory(new MapValueFactory("Caddress"));
               ColCity.setCellValueFactory(new MapValueFactory("Ccity"));
               ColPostal.setCellValueFactory(new MapValueFactory("Cpostalcode"));
               ColCompany.setCellValueFactory(new MapValueFactory("Ccompany"));
               ColMail.setCellValueFactory(new MapValueFactory("Cmail"));
            tblCustomers.getColumns().setAll(ColId,ColFirstName,ColLastName,ColPhone,ColAddress,ColCity,ColPostal,ColCompany,ColMail);
        }// FillCustomers()   
               
        // Καθαριζει ολα τα πεδία Στην καρτέλα εισαγωγή Πελάτη.
        public void ClearComponents(){
            txtCFirstName.setText("");
            txtCLastName.setText("");
            txtCPhone.setText("");
            txtCAddress.setText("");
            txtCCity.setText("");
            txtCPostal.setText("");
            txtCCompany.setText("");
            txtCMail.setText("");
        }// ClearComponents()    
        
       public boolean NullCheck(){
        if(txtCFirstName.getText().isEmpty()||txtCLastName.getText().isEmpty()||
           txtCPhone.getText().isEmpty()||txtCAddress.getText().isEmpty()||
           txtCCity.getText().isEmpty()|| txtCPostal.getText().isEmpty()|| 
           txtCCompany.getText().isEmpty()|| txtCMail.getText().isEmpty())
        {
            return false;
        }else{
            return true;
        }
    }
    //==================================
}