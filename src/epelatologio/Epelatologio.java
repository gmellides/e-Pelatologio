package epelatologio;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Epelatologio extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/StartScreen.fxml"));
        
        Scene scene = new Scene(root);
         
        stage.setHeight(465);
        stage.setWidth(683);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
          @Override
          public void handle(WindowEvent we) {
             System.exit(0);
          }
        });
        stage.setTitle("e-Customer Keeper");
        stage.setScene(scene);
        stage.setResizable(false);
        Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
        stage.getIcons().add(icon);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
