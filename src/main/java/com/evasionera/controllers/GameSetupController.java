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

        // 石頭數量的範圍為 2 ~ 10，預設為 2，為避免畫面過於擁擠，最多只能有 10 個石頭
        numStones.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 10, 2));

        // 遊戲時長的範圍為 10 ~ 120 秒，預設為 60 秒
        gameDuration.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 120, 60, 10));
    }

    @FXML
    public void startGame() {
        // 取得使用者輸入的參數
        String ghostName = ghostNameField.getText();
        String playerName = playerNameField.getText();
        int stoneCount = numStones.getValue();
        int duration = gameDuration.getValue();

        // 設定遊戲參數
        GameController gameController = (GameController) main.getController("game");
        gameController.setupGame(playerName, ghostName, stoneCount, duration);

        // 切換到遊戲畫面
        main.switchToScene("game");
        gameController = (GameController) main.getController("game");
        gameController.startGame();
    }

    @FXML
    public void switchToHomeView() {
        main.switchToScene("home");
    }
}
