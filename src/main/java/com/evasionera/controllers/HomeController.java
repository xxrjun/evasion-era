package com.evasionera.controllers;

import com.evasionera.EvasionEraApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomeController {

    private EvasionEraApplication Main;

    public void setMain(EvasionEraApplication Main) {
        this.Main = Main;
    }

    @FXML
    private Button startButton;

    @FXML
    private Button rulesButton;


    @FXML
    public void startGame() {
        Main.switchToStartView();
    }

    @FXML
    public void showRules() {
        Main.switchToRuleView();
    }
}

