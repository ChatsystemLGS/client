package client.gui;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/*
This class is a controller class that will toggle in sign up and sign in form and also
navigate to main chat screen
 */
public class MainController implements Initializable {   

//    data members
    @FXML
    private VBox vbox;    
    private Parent fxml;

    private Alert alert;
    @Override
//    we are implementing Initializable Interface here, so that as soon as modal loads up that will run
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 20);
        t.play();
        t.setOnFinished((e) ->{
            try{
                fxml = FXMLLoader.load(getClass().getResource("/scenes/SignIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(IOException ex){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("System Error");
                alert.setContentText(ex.getLocalizedMessage());
            }
        });
    }
    @FXML
    private void SignIn_Modal(ActionEvent event){
          TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 20);
        t.play();
        t.setOnFinished((e) ->{
            try{
                fxml = FXMLLoader.load(getClass().getResource("/scenes/SignIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(IOException ex){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("System Error");
                alert.setContentText(ex.getLocalizedMessage());
            }
        });
    }   
    @FXML
    private void SignUp_Modal(ActionEvent event){
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(0);
        t.play();
        t.setOnFinished((e) ->{
            try{
                fxml = FXMLLoader.load(getClass().getResource("/scenes/SignUp.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(IOException ex){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("System Error");
                alert.setContentText(ex.getLocalizedMessage());
            }
        });
    }

    }
    

