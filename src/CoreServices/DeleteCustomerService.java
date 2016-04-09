
package CoreServices;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DeleteCustomerService extends DataBaseCore{
    
    public boolean DeleteCustomer(String ID){
     boolean Flag = false;
      try{
          ConnectToDB();
           PreparedStatement Delete = DBConnection.prepareStatement("DELETE FROM Customer WHERE CUSTOMERID = ?");
                Delete.setString(1, ID);        
           Delete.execute();
           Flag = true;
          CloseDB();
      }catch(SQLException e){
          e.printStackTrace();
      }
      return Flag;
    }
      
    public void SetSelctedID(String SelectedID){
        this.SelectedID = SelectedID;
    }
    
    public String GetSelectedID(){
        return SelectedID;
    }
    
    private static String SelectedID; 
    
}
