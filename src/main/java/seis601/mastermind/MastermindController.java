package seis601.mastermind;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MastermindController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to Mastermind!");
    }
}