package com.evasionera.controllers;

import com.evasionera.models.Ghost;
import com.evasionera.models.Player;
import com.evasionera.models.Stone;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GameController extends BaseController {
    // 視窗寬度和高度
    public static final double WINDOW_WIDTH = 1600.0;
    public static final double WINDOW_HEIGHT = 900.0;

    // 用於存放當前按下的按鍵
    private static final Set<KeyCode> keys = new HashSet<>();

    // 遊戲是否結束的flag
    private static boolean isGameOver = false;

    // 玩家和鬼的物件
    private static Player player;
    private static Ghost ghost;

    // 遊戲開始的時間
    private static long startTime;

    // 遊戲中的石頭
    private final List<Stone> stones = new ArrayList<>();

    // 遊戲計時器
    private AnimationTimer gameTimer;

    // 遊戲時長，預設為 60 秒
    private int gameDuration = 60 * 1000;

    /**
     * FXML 元件
     */
    @FXML
    private ImageView playerImage;
    @FXML
    private ImageView ghostImage;
    @FXML
    private Label timeLabel;
    @FXML
    private AnchorPane gamePane;
    @FXML
    private Label playerNameLabel;
    @FXML
    private Label ghostNameLabel;
    @FXML
    private List<ImageView> stoneImages = new ArrayList<>();

    /**
     * 初始化遊戲，設定玩家和鬼的名字、石頭數量、遊戲時長，開始遊戲計時器
     */
    @FXML
    public void initialize() {
        // 初始化玩家和鬼的物件與位置
        player = new Player(0, WINDOW_HEIGHT / 2);
        ghost = new Ghost(WINDOW_WIDTH, WINDOW_HEIGHT / 2);

        // 設定開始時間
        startTime = System.currentTimeMillis();


        gamePane.setFocusTraversable(true);
        Platform.runLater(() -> {
            gamePane.requestFocus();
            gamePane.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> keys.add(event.getCode()));
            gamePane.getScene().addEventFilter(KeyEvent.KEY_RELEASED, event -> keys.remove(event.getCode()));
        });

        gameTimer = new AnimationTimer() {
            long currentTime = System.currentTimeMillis();

            @Override
            public void handle(long now) {
                currentTime = System.currentTimeMillis();
                handleKeys();       // 處理按鍵
                player.update();    // 更新玩家位置
                updatePositions();  // 更新玩家、鬼、石頭位置
                updateTime(currentTime);       // 更新時間
                checkCollisions();  // 檢查石頭與玩家、鬼的碰撞
                checkGameOver(currentTime);    // 檢查遊戲是否結束
            }
        };
    }

    /**
     * 設定遊戲(玩家名稱、鬼的名稱、石頭數量、遊戲時長)
     *
     * @param playerName 玩家名稱
     * @param ghostName  鬼的名稱
     * @param stoneCount 石頭數量
     * @param duration   遊戲時長
     */
    public void setupGame(String playerName, String ghostName, int stoneCount, int duration) {
        // 顯示遊戲設定參數
        System.out.println("Player: " + playerName);
        System.out.println("Ghost: " + ghostName);
        System.out.println("Stone Count: " + stoneCount);
        System.out.println("Duration: " + duration);


        // 初始化玩家和鬼的物件與位置
        player = new Player(0, 400);
        ghost = new Ghost(1540, 400);
        clearStones();
        keys.clear();

        this.gameDuration = duration * 1000; // Convert to milliseconds

        playerNameLabel.setText(playerName);
        ghostNameLabel.setText(ghostName);

        // Create stones
        for (int i = 0; i < stoneCount; i++) {
            Stone stone = new Stone();
            stones.add(stone);

            ImageView stoneImage = new ImageView(new Image("/images/stone.png"));
            stoneImage.setFitWidth(50);
            stoneImage.setFitHeight(50);
            stoneImages.add(stoneImage);
            gamePane.getChildren().add(stoneImage);
        }
    }

    /**
     * 開始遊戲
     */
    public void startGame() {
        isGameOver = false;
        startTime = System.currentTimeMillis();
        gameTimer.start();
    }


    /**
     * 處理使用者輸入按鍵
     */
    private void handleKeys() {

        // 如果鬼可以移動，則根據按鍵移動鬼的位置(上下左右)
        if (ghost.canMove()) {
            if (keys.contains(KeyCode.UP)) {
                ghost.moveUp();
            }
            if (keys.contains(KeyCode.DOWN)) {
                ghost.moveDown();
            }
            if (keys.contains(KeyCode.LEFT)) {
                ghost.moveLeft();
                ghostImage.setScaleX(-1); // 水平翻轉圖片，讓鬼面向左邊
            }
            if (keys.contains(KeyCode.RIGHT)) {
                ghost.moveRight();
                ghostImage.setScaleX(1); // 恢復圖片原本的方向，面向右邊
            }
        }

        // 如果鬼處於透明狀態(背石頭打到)，則降低圖片的透明度
        ghostImage.setOpacity(ghost.isTransparent() ? 0.5 : 1.0);

        // 根據按鍵移動玩家的位置(WASD、空白鍵)
        if (keys.contains(KeyCode.W)) {
            player.moveUp();
        }
        if (keys.contains(KeyCode.S)) {
            player.moveDown();
        }
        if (keys.contains(KeyCode.A)) {
            player.moveLeft();
            playerImage.setScaleX(-1); // 水平翻轉圖片，讓玩家面向左邊
        }
        if (keys.contains(KeyCode.D)) {
            player.moveRight();
            playerImage.setScaleX(1); // 恢復圖片原本的方向，面向右邊
        }
        if (keys.contains(KeyCode.SPACE)) {
            player.boost(); // 玩家按下空白鍵，啟動加速
        }

    }

    /**
     * 更新元件位置
     */
    private void updatePositions() {
        // 更新玩家位置
        playerImage.setTranslateX(player.getX());
        playerImage.setTranslateY(player.getY());
        playerNameLabel.setTranslateX(player.getX());
        playerNameLabel.setTranslateY(player.getY() - 30); // 玩家名稱標籤在玩家圖片上方

        // 更新鬼位置
        ghostImage.setTranslateX(ghost.getX());
        ghostImage.setTranslateY(ghost.getY());
        ghostNameLabel.setTranslateX(ghost.getX());
        ghostNameLabel.setTranslateY(ghost.getY() - 30); // 鬼名稱標籤在鬼圖片上方

        // 更新每顆石頭
        for (int i = 0; i < stones.size(); i++) {
            Stone stone = stones.get(i);
            ImageView stoneImage = stoneImages.get(i);

            // 更新石頭狀態(速度、位置、方向)
            stone.update();

            // 更新石頭位置
            stoneImage.setTranslateX(stone.getX());
            stoneImage.setTranslateY(stone.getY());
        }
    }


    /**
     * 更新時間標籤
     *
     * @param currentTime 當前時間
     */
    private void updateTime(long currentTime) {
        // 首先計算經過的時間
        long elapsedTime = currentTime - startTime;

        // 透過經過的時間計算剩餘時間
        long remainingTime = gameDuration - elapsedTime;

        // 更新時間標籤
        timeLabel.setText("Time: " + remainingTime / 1000);
    }


    /**
     * 檢查任一石頭是否與玩家或鬼碰撞
     */
    private void checkCollisions() {
        for (Stone stone : stones) {
            stone.checkCollision(player, ghost);
        }
    }

    /**
     * 清除所有石頭
     */
    private void clearStones() {
        stones.clear();
        for (ImageView stoneImage : stoneImages) {
            gamePane.getChildren().remove(stoneImage);
        }
        stoneImages.clear();
    }


    /**
     * 檢查遊戲是否結束
     *
     * @param currentTime 當前時間
     */
    private void checkGameOver(long currentTime) {
        if (isGameOver) {
            return;
        }

        boolean playerCaught = Math.abs(player.getX() - ghost.getX()) < 50 && Math.abs(player.getY() - ghost.getY()) < 50;
        boolean timeUp = currentTime - startTime >= gameDuration;
        long timeConsuming = (currentTime - startTime) / 1000;

        // 如果玩家被抓到或時間到，則結束遊戲
        if (playerCaught) {
            endGame("Ghost", timeConsuming);
        } else if (timeUp) {
            endGame("Player", timeConsuming);
        }
    }

    /**
     * 結束遊戲，顯示結束畫面
     *
     * @param winner        勝利者
     * @param timeConsuming 時間消耗
     */
    private void endGame(String winner, long timeConsuming) {
        System.out.println("Game Over, " + winner + " is Win!");
        isGameOver = true;
        main.switchToScene("end", winner + " is Win!\nTime consuming: " + timeConsuming + " seconds");
    }
}