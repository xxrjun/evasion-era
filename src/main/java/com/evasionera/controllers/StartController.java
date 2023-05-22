package com.evasionera.controllers;

import com.evasionera.EvasionEraApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class StartController {

    public TextField playerName;
    public TextField ghostName;


    private EvasionEraApplication Main;


    public void setMain(EvasionEraApplication Main) {
        this.Main = Main;
    }

    @FXML
    private TextField ghostNameField;
    @FXML
    private TextField personNameField;
    @FXML
    private Spinner<Integer> numStones;




    @FXML
    public void initialize() {
        numStones.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 5, 2));
    }

    @FXML
    public void startGame() {
        Main.switchToGameView();

//        String ghostName = ghostNameField.getText();
//        String personName = personNameField.getText();
//        int stoneCount = numStones.getValue();

        // Start the game with the given parameters

    }

    @FXML
    public void switchToHomeView() {
        Main.switchToHomeView();
    }
}
