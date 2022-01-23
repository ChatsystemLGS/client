package client.gui;

import client.ProtocolException;
import client.ServerHandler;
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
import java.util.Objects;


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
    private void signup(ActionEvent event) throws IOException, ProtocolException {
        //if fields are empty throw error

        if (userName.getText().equals("") || userEmail.getText().equals("") || userPassword.getText().equals("")) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setContentText("Bitte fülle alle Felder aus.");
            alert.showAndWait();

        } else {

            String response = ServerHandler.sh.register(userEmail.getText(), userPassword.getText(), userName.getText());

            if (response.equals(ProtocolException.Status.OK.toString())) {

                User newUser = new User(userName.getText(), userEmail.getText(), userPassword.getText(), "default.png"); // TODO: load image from database
                users.addUser(newUser); //add new user to users list

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erfolgreich registriert: " + users.getUsers());
                alert.setContentText("Bitte logge dich ein, um fortzufahren.");
                alert.showAndWait();

                //now show login Screen to user
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/Main.fxml")));
                Stage stage = new Stage();
                stage.setTitle("Home");
                Scene scene = new Scene(fxml);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                fxml.getStylesheets().add("style.css");
                stage.setScene(scene);
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();

            } else if (response.equals(ProtocolException.Status.EMAIL_ALREADY_REGISTERED.toString())) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("E-Mail existiert bereits.");
                alert.setContentText("E-Mail Adresse  " + userEmail.getText() + " existiert bereits. Bitte logge dich ein.");
                alert.showAndWait();

            } else if (response.equals(ProtocolException.Status.PASSWORD_REQ_NOT_MET.toString())) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Passwort Anforderungen nicht erfüllt");
                alert.setContentText("Passwort Anforderungen nicht erfüllt. Bitte verwende ein stärkeres Passwort."); // get actual requirements
                alert.showAndWait();

            } else {
                throw new ProtocolException.UnknownException(response);
            }
        }

    }

    //profile picture chooser
    // TODO: check filetype, implement default picture (maybe from db side?)
    @FXML
    public void chooseProfilePicture(ActionEvent actionEvent) {
        Stage stage = new Stage();
        File file = chooser.showOpenDialog(stage);

        if (file != null) {
            userProfile = file.getAbsolutePath(); // TODO: image into db
        }
    }
}
