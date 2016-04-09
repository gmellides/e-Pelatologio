/**
 * = Start Screen Controller =
 * 1. ξεκιναει το ρολοι 
 * 2. κουμπι εξοδου
 * 3. κουμπι συνεχειας.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import CoreServices.DateTimeServices;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartScreenController implements Initializable {
    // ===== Objects Decleration ==========  
        DateTimeServices DateTime = new DateTimeServices();; 
        Timer ClockRefresh = new java.util.Timer();
    // ====================================
    
    //==== XML Components decleration =====
        @FXML 
        private Label lblTime;
        @FXML
        private Label lblDate;
        @FXML 
        private Label lblWaitMessage;
    // ====================================
    
   //= Default Method to Initiallize panel =     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Sets Current time on clocks
        ClockRefresh.schedule(new TimerTask() {
                @Override
                public void run() {
                 Platform.runLater(() -> {
                     lblDate.setText(DateTime.Date());
                     lblTime.setText(DateTime.Time());
                 });
               }
         }, 0, 1);
        
    }
    // ====================================
    
    //== Components Action decleration ====
    /**
     * Applications Exit.
     * @param event 
     */
    @FXML
    private void btnCloseAction(ActionEvent event) {
        System.exit(0);
    } 
    /**
    * Checks if Company and Owner Informations is stored at 
    * Database and declares the GUI file that will be on stage.
    */ 
    @FXML
    private void btnGoAction(ActionEvent event) throws IOException {
        lblWaitMessage.setText("Just a moment...");
        
            Service<Void> LoadService = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {           
                    @Override
                    protected Void call() throws IOException {
                        Platform.runLater(new Runnable() {                          
                            @Override
                            public void run() {
                                Parent NewPanelLoader;
                               try{
                                NewPanelLoader = FXMLLoader.load(getClass().getResource("/GUI/MainScreen.fxml"));                          
                                Scene PanelScene = new Scene(NewPanelLoader);
                                Stage AppStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                AppStage.setScene(PanelScene);
                                AppStage.show();
                               }catch(IOException e){
                                   e.printStackTrace();
                               }
                            }
                        });
                        return null;
                    }
                };
            }
             };
         LoadService.start();
    }
    // ====================================
}
