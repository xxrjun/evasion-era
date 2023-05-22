package com.evasionera.controllers;

import com.evasionera.EvasionEraApplication;
import javafx.fxml.FXML;

public class RuleController {

    private EvasionEraApplication Main;

    public void setMain(EvasionEraApplication Main) {
        this.Main = Main;
    }

    @FXML
    public void switchToHomeView() {
        Main.switchToHomeView();
    }
}
