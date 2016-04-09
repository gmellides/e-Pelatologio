/*
 *.
 */
package CoreServices;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.HashMap;
import java.util.Map;
import CoreObjects.Customer;

public class MainScreenServices extends DataBaseCore{
    
    // Ανακτα τα δεδομένα των πελατών και τα τοποθετει σε HashMap.
    public ObservableList<Map> GetCustomerData(){
      ObservableList<Map> Data = FXCollections.observableArrayList();
        try{
            ConnectToDB();
            DBStatement = DBConnection.createStatement();
            DataRetrive = DBStatement.executeQuery("SELECT * FROM Customer");
            while (DataRetrive.next()){
             Map<String,String> RowData = new HashMap<>();
               RowData.put("CID",String.valueOf(DataRetrive.getInt("CUSTOMERID")));
               RowData.put("Cfirstname",DataRetrive.getString("FirstName"));
               RowData.put("Clastname",DataRetrive.getString("LastName"));
               RowData.put("Cphone",DataRetrive.getString("Phone"));
               RowData.put("Caddress",DataRetrive.getString("Address"));
               RowData.put("Ccity",DataRetrive.getString("City"));
               RowData.put("Cpostalcode",DataRetrive.getString("PostalCode"));
               RowData.put("Ccompany",DataRetrive.getString("Company"));
               RowData.put("Cmail",DataRetrive.getString("Mail"));          
             Data.add(RowData);
            }
            CloseDB();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return Data;
    } // GetCustomerData()
   
    // Εισάγει στοιχεία Νέου πελάτη στην Βάση Δεδομένων.
    public void InsertCustumer(Customer obj){
        try{
            ConnectToDB(); 
            PreparedStatement Insert = DBConnection.prepareStatement("INSERT INTO Customer(CUSTOMERID,FirstName,LastName,Phone,Address,City,PostalCode,Company,Mail)VALUES(?,?,?,?,?,?,?,?,?)");
                Insert.setInt(1,0);
                Insert.setString(2,obj.GetFirstName());
                Insert.setString(3,obj.GetLastName());
                Insert.setString(4,obj.GetPhone());
                Insert.setString(5,obj.GetAddress());
                Insert.setString(6,obj.GetCity());
                Insert.setInt(7,obj.GetPostalCode());
                Insert.setString(8,obj.GetCompany());
                Insert.setString(9,obj.GetMail());
           boolean d = Insert.execute();
           CloseDB();
            }catch(SQLException e){
            e.printStackTrace();
        }
    }// InsertCustumer()  
    
}//MainScreenServices
