
package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;


public class SignupController {

    //data members
    @FXML private Parent fxml;
    @FXML private TextField userName;
    @FXML private TextField userEmail;
    @FXML private TextField userPassword;

    private UsersList users = new UsersList();
    private Alert alert;
    private FileChooser chooser = new FileChooser();
    private String userProfile  = "";

    //this function will add new User to list, this function also checks if user already exits
    //it will throw error
    @FXML
    private void signup(ActionEvent event) throws IOException {
        //if fields are empty throw error

        if(userName.getText().equals("") || userEmail.getText().equals("") || userPassword.getText().equals("")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setContentText("Bitte fülle alle Felder aus.");
            alert.showAndWait();
        }
        else {
            //instantiaing user object

            User newUser = new User(userName.getText(), userEmail.getText(), userPassword.getText(), "/1.png"); // TODO: load image into database
            //checking if user with email exits, we will not let him her sign up

            if (users.exists(newUser)) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Nutzer existiert bereits.");
                alert.setContentText("E-Mail Adresse  " + userEmail.getText() + " existiert bereits.");
                alert.showAndWait();
            } else {

                //means user credentials are ok
                users.addUser(newUser); //add new user to users list
                //give feedback to user
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erfolgreich registriert: " + users.getUsers());
                alert.setContentText("Bitte logge dich ein, um fortzufahren.");
                alert.showAndWait();


                //now show login Screen to user
                fxml = FXMLLoader.load(getClass().getResource("/Main.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Home");
                Scene scene = new Scene(fxml);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                fxml.getStylesheets().add("style.css");
                stage.setScene(scene);
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
        }

    }
    //profile picture chooser
    @FXML
    public void chooseProfilePicture(ActionEvent actionEvent) {
        // get the file selected
        Stage stage = new Stage();
        File file = chooser.showOpenDialog(stage);

        if (file != null) {
            userProfile = file.getAbsolutePath(); // TODO: image from db
        }
    }
}
