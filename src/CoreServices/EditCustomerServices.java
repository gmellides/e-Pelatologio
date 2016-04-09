package CoreServices;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import CoreObjects.Customer;

public class EditCustomerServices extends DataBaseCore{
   
    // Εισάγει στοιχεία Νέου πελάτη στην Βάση Δεδομένων.
    public void UpdateCustomer(String ID,Customer obj){
        try{
            ConnectToDB(); 
            PreparedStatement Update = DBConnection.prepareStatement("UPDATE Customer  SET FirstName = ? ,LastName = ? ,Phone = ? ,Address = ?,City = ?,PostalCode=?,Company=?,Mail=? WHERE CUSTOMERID = "+ID);
                Update.setString(1,obj.GetFirstName());
                Update.setString(2,obj.GetFirstName());
                Update.setString(3,obj.GetPhone());
                Update.setString(4,obj.GetAddress());
                Update.setString(5,obj.GetCity());
                Update.setInt(6,obj.GetPostalCode());
                Update.setString(7,obj.GetCompany());
                Update.setString(8,obj.GetMail());
           boolean d = Update.execute();
           CloseDB();
            }catch(SQLException e){
            e.printStackTrace();
        }
    } // UpdateCustomer()
  
    public void SetMap(Map SelectedData){
        this.SelectedData = SelectedData;
    }
    
    public Map GetSelectedData(){
        return SelectedData;
    }
    
    private static Map SelectedData;
} // EditCustomerServices
