package CoreServices;

import java.io.File;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseCore {
    String DBPath = "jdbc:ucanaccess://"+ new File("CostumerAppData1.accdb").getAbsolutePath();
  
    Connection DBConnection;
    ResultSet DataRetrive;
    Statement DBStatement;
    
    public void ConnectToDB(){
         try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            DBConnection = DriverManager.getConnection(DBPath);
        }catch(ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }
    
    public void CloseDB(){
        try{
            if(DBConnection != null ){
                DBConnection.close();
            }
            if (DataRetrive != null){
                DataRetrive.close();
            }
            if (DBStatement != null){
                DBStatement.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
