package com.evasionera;

import com.evasionera.controllers.BaseController;
import com.evasionera.controllers.EndViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EvasionEraApplication extends Application {

    private Stage stage;
    private Map<String, Scene> scenes = new HashMap<>();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;

        try {
            scenes.put("home", loadScene("/com/evasionera/home-view.fxml"));
            scenes.put("setup", loadScene("/com/evasionera/game-setup-view.fxml"));
            scenes.put("rule", loadScene("/com/evasionera/rule-view.fxml"));
            scenes.put("game", loadScene("/com/evasionera/game-view.fxml"));
            scenes.put("end", loadScene("/com/evasionera/end-view.fxml"));
        } catch (IOException e) {

        }

        primaryStage.setTitle("Evasion Era");
        primaryStage.setScene(scenes.get("home"));
        primaryStage.show();
    }

    private Scene loadScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        BaseController controller = loader.getController();
        controller.setMain(this);

        Scene scene = new Scene(root, 1280, 720);
        scene.setUserData(controller);  // Set the controller as the user data of the scene


        return scene;
    }

    public void switchToScene(String name) {
        Scene scene = scenes.get(name);
        stage.setScene(scene);
    }

    public void switchToScene(String name, String message) {
        Scene scene = scenes.get(name);
        BaseController controller = (BaseController) scene.getUserData();


        if (controller instanceof EndViewController) {
            ((EndViewController) controller).setMessage(message);
        }

        stage.setScene(scene);
    }

    public BaseController getController(String name) {
        Scene scene = scenes.get(name);
        return (BaseController) scene.getUserData();
    }

}