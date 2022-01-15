package client.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ChatController {
    @FXML private Label welcomeText; // remove this

    @FXML private TextField login_email;
    @FXML private TextField login_password;
    @FXML private Text login_text;

    @FXML private Button login_button;

    @FXML protected void onHelloButtonClick() { // remove this
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void login(ActionEvent event) {
        String email = login_email.getText();
        String password = login_password.getText();

        login_text.setText(email + " - " + password);
    }
}