package com.evasionera.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController extends BaseController{

    @FXML
    private Button startButton;

    @FXML
    private Button rulesButton;


    @FXML
    public void startGame() {
        main.switchToScene("setup");
    }

    @FXML
    public void showRules() {
        main.switchToScene("rule");
    }
}

