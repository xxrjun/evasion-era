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

    // 視窗寬度與高度
    public static final double WINDOW_WIDTH = 1600.0;
    public static final double WINDOW_HEIGHT = 900.0;

    // 主要的舞台
    private Stage stage;

    // 儲存所有場景的HashMap
    private Map<String, Scene> scenes = new HashMap<>();

    public static void main(String[] args) {
        launch(); // 啟動 JavaFX 程式
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage; // 設定主要的視窗
        primaryStage.setWidth(WINDOW_WIDTH); // 設定視窗寬度
        primaryStage.setHeight(WINDOW_HEIGHT); // 設定視窗高度

        try {
            // 載入所有場景
            scenes.put("home", loadScene("/com/evasionera/home-view.fxml"));
            scenes.put("setup", loadScene("/com/evasionera/game-setup-view.fxml"));
            scenes.put("rule", loadScene("/com/evasionera/rule-view.fxml"));
            scenes.put("game", loadScene("/com/evasionera/game-view.fxml"));
            scenes.put("end", loadScene("/com/evasionera/end-view.fxml"));
        } catch (IOException e) {
            // 載入場景失敗，印出錯誤訊息
            System.out.println("Error loading scene: " + e.getMessage());
        }

        primaryStage.setTitle("Evasion Era"); // 設定視窗標題
        primaryStage.setScene(scenes.get("home")); // 設定初始場景為首頁(home)
        primaryStage.show(); // 顯示視窗
    }

    /**
     * 載入場景
     *
     * @param fxml 場景的 FXML 檔案路徑
     * @return 載入的場景 Scene
     * @throws IOException 載入失敗時拋出 IOException
     */
    private Scene loadScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml)); // 建立 FXMLLoader
        Parent root = loader.load(); // 載入 FXML 場景
        BaseController controller = loader.getController(); // 取得場景的 Controller
        controller.setMain(this); // 設定 Controller 的主要類別

        Scene scene = new Scene(root, WINDOW_WIDTH , WINDOW_HEIGHT); // 建立場景 Scene
        scene.setUserData(controller);  // 設定場景的 UserData 為 Controller，使控制器物件可以在場景切換時取得


        return scene;
    }

    /**
     * 切換場景
     *
     * @param name 場景名稱
     */
    public void switchToScene(String name) {
        Scene scene = scenes.get(name); // 取得場景
        stage.setScene(scene); // 切換場景
    }

    /**
     * 切換場景
     *
     * @param name 場景名稱
     * @param message 於切換場景時傳遞的訊息
     */
    public void switchToScene(String name, String message) {
        Scene scene = scenes.get(name); // 取得場景
        BaseController controller = (BaseController) scene.getUserData(); // 取得場景的控制器

        // 如果是 EndViewController，則設定訊息(結束訊息)
        if (controller instanceof EndViewController) {
            ((EndViewController) controller).setMessage(message);
        }

        // 切換場景
        stage.setScene(scene);
    }

    /**
     * 取得場景的控制器
     *
     * @param name 場景名稱
     * @return 場景的控制器
     */
    public BaseController getController(String name) {
        Scene scene = scenes.get(name); // 取得場景
        return (BaseController) scene.getUserData(); // 取得場景的控制器
    }

}