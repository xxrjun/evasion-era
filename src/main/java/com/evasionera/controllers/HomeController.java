package com.evasionera.controllers;

import javafx.fxml.FXML;

public class HomeController extends BaseController {
    @FXML
    public void startGame() {
        main.switchToScene("setup");
    }
    @FXML
    public void showRules() {
        main.switchToScene("rule");
    }
}

