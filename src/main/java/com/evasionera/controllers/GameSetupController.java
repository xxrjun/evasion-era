package com.evasionera.controllers;

import com.evasionera.EvasionEraApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class GameSetupController extends BaseController {

    @FXML
    private TextField playerNameField;

    @FXML
    private TextField ghostNameField;

    @FXML
    private Spinner<Integer> gameDuration;

    @FXML
    private Spinner<Integer> numStones;

    private EvasionEraApplication main;

    public void setMain(EvasionEraApplication main) {
        this.main = main;
    }

    @FXML
    public void initialize() {
        playerNameField.setText("Player");
        ghostNameField.setText("Ghost");
        numStones.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 5, 2));
        gameDuration.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 120, 60, 10));
    }

    @FXML
    public void startGame() {
        String ghostName = ghostNameField.getText();
        String playerName = playerNameField.getText();
        int stoneCount = numStones.getValue();
        int duration = gameDuration.getValue();

        // Pass the parameters to the GameController
        GameController gameController = (GameController) main.getController("game");
        gameController.setupGame(playerName, ghostName, stoneCount, duration);

        // Start the game
        main.switchToScene("game");
        gameController = (GameController) main.getController("game");
        gameController.startGame();
    }

    @FXML
    public void switchToHomeView() {
        main.switchToScene("home");
    }
}
