package com.evasionera;

import com.evasionera.controllers.GameController;
import com.evasionera.controllers.HomeController;
import com.evasionera.controllers.RuleController;
import com.evasionera.controllers.StartController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class EvasionEraApplication extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(EvasionEraApplication.class.getResource("home-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());
//        stage.setTitle("Evasion Era!");
//        stage.setScene(scene);
//        stage.show();
//    }

    public static void main(String[] args) {
        launch();
    }
    private Stage stage;
    private Scene homeScene, startScene, ruleScene, gameScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage = primaryStage;

        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/com/evasionera/home-view.fxml"));
        Parent home = homeLoader.load();
        HomeController homeController = homeLoader.getController();
        homeController.setMain(this);
        homeScene = new Scene(home, 1280, 720);

        FXMLLoader startLoader = new FXMLLoader(getClass().getResource("/com/evasionera/start-view.fxml"));
        Parent start = startLoader.load();
        StartController startController = startLoader.getController();
        startController.setMain(this);
        startScene = new Scene(start, 1280, 720);

        FXMLLoader ruleLoader = new FXMLLoader(getClass().getResource("/com/evasionera/rule-view.fxml"));
        Parent rule = ruleLoader.load();
        RuleController ruleController = ruleLoader.getController();
        ruleController.setMain(this);
        ruleScene = new Scene(rule, 1280, 720);


        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/com/evasionera/game-view.fxml"));
        Parent game = gameLoader.load();
        GameController gameController = gameLoader.getController();
        gameController.setMain(this);
        gameScene = new Scene(game, 1280, 720);

        primaryStage.setTitle("Evasion Era");
        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

    public void switchToStartView() {
        stage.setScene(startScene);
    }

    public void switchToRuleView() {
        stage.setScene(ruleScene);
    }

    public void switchToHomeView() {
        stage.setScene(homeScene);
    }
    public void switchToGameView() {
        stage.setScene(gameScene);
    }

}