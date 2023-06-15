package com.evasionera.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EndViewController extends BaseController {
    @FXML
    private Label messageLabel;

    public void setMessage(String message) {
        System.out.println(message);
        messageLabel.setText(message);
    }

    @FXML
    public void switchToHomeView() {
        main.switchToScene("home");
    }

}