package com.evasionera.controllers;

import javafx.fxml.FXML;

public class RuleController extends BaseController {
    @FXML
    public void switchToHomeView() {
        main.switchToScene("home");
    }
}

