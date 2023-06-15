// GameController.java
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

import java.util.*;


public class GameController extends BaseController {
    private static final Set<KeyCode> keys = new HashSet<>();
    private static final double WINDOW_WIDTH = 1280.0;
    private static final double WINDOW_HEIGHT = 720.0;
    private static boolean isGameOver = false;
    private static Player player;
    private static Ghost ghost;
    private static long startTime;
    private static String playerName;
    private static String ghostName;
    boolean playerHitByStone = false;
    boolean ghostHitByStone = false;
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
    private int stoneCount;
    @FXML
    private List<ImageView> stoneImages = new ArrayList<>();
    private List<Stone> stones = new ArrayList<>();
    private AnimationTimer gameTimer;
    private int gameDuration = 60 * 1000 + 4 * 1000; // 遊戲預設時長為 60 秒，多加4秒是遊戲開始的誤差秒數

    /**
     * Reset game state to initial state
     */
    public void reset() {
        isGameOver = false;
        player = new Player(0, 400);
        ghost = new Ghost(1200, 400);
        clearStones();
        startTime = System.currentTimeMillis();
        keys.clear();
    }

    private void clearStones() {
        stones.clear();
        for (ImageView stoneImage : stoneImages) {
            gamePane.getChildren().remove(stoneImage);
        }
        stoneImages.clear();
    }
    @FXML
    public void initialize() {
        player = new Player(0, 400);
        ghost = new Ghost(1200, 400);
        startTime = System.currentTimeMillis();

        gamePane.setFocusTraversable(true);
        Platform.runLater(() -> {
            gamePane.requestFocus();
            gamePane.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> keys.add(event.getCode()));
            gamePane.getScene().addEventFilter(KeyEvent.KEY_RELEASED, event -> keys.remove(event.getCode()));
        });

        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                handleKeys();
                player.update();
                updatePositions();
                updateStones();
                updateTime();
                checkGameOver();
            }
        };
    }

    public void startGame() {
        startTime = System.currentTimeMillis();
        gameTimer.start();
    }

    public void setupGame(String playerName, String ghostName, int stoneCount, int duration) {
        // Set up the game with the given parameters
        System.out.println("Player: " + playerName);
        System.out.println("Ghost: " + ghostName);
        System.out.println("Stone Count: " + stoneCount);
        System.out.println("Duration: " + duration);

        GameController.playerName = playerName;
        GameController.ghostName = ghostName;
        this.stoneCount = stoneCount;
        this.gameDuration = duration * 1000 + 4 * 1000; // Convert to milliseconds

        playerNameLabel.setText(playerName);
        ghostNameLabel.setText(ghostName);

        // Create stones
        for (int i = 0; i < stoneCount; i++) {
            Stone stone = new Stone(Math.random() * WINDOW_WIDTH, Math.random() * WINDOW_HEIGHT);
            stones.add(stone);

            ImageView stoneImage = new ImageView(new Image("/images/stone.png"));
            stoneImage.setFitWidth(50);
            stoneImage.setFitHeight(50);
            stoneImages.add(stoneImage);
            gamePane.getChildren().add(stoneImage);
        }
    }

    /**
     * Handle key presses
     */
    private void handleKeys() {
        if (ghost.canMove()) {
            if (keys.contains(KeyCode.UP)) {
                ghost.moveUp();
            }
            if (keys.contains(KeyCode.DOWN)) {
                ghost.moveDown();
            }
            if (keys.contains(KeyCode.LEFT)) {
                ghost.moveLeft();
                ghostImage.setScaleX(-1); // Flip image horizontally
            }
            if (keys.contains(KeyCode.RIGHT)) {
                ghost.moveRight();
                ghostImage.setScaleX(1); // Reset image to original orientation
            }
        }

        ghostImage.setOpacity(ghost.isTransparent() ? 0.5 : 1.0);

        if (keys.contains(KeyCode.W)) {
            player.moveUp();
        }
        if (keys.contains(KeyCode.S)) {
            player.moveDown();
        }
        if (keys.contains(KeyCode.A)) {
            player.moveLeft();
            playerImage.setScaleX(-1); // Flip image horizontally
        }
        if (keys.contains(KeyCode.D)) {
            player.moveRight();
            playerImage.setScaleX(1); // Reset image to original orientation
        }
        if (keys.contains(KeyCode.SPACE)) {
            player.boost();
        }

    }

    /**
     * Update player and ghost positions
     */
    private void updatePositions() {
        playerImage.setTranslateX(player.getX());
        playerImage.setTranslateY(player.getY());
        playerNameLabel.setTranslateX(player.getX());
        playerNameLabel.setTranslateY(player.getY() - 30); // Position label above image


        ghostImage.setTranslateX(ghost.getX());
        ghostImage.setTranslateY(ghost.getY());
        ghostNameLabel.setTranslateX(ghost.getX());
        ghostNameLabel.setTranslateY(ghost.getY() - 30); // Position label above image

        for (int i = 0; i < stones.size(); i++) {
            Stone stone = stones.get(i);
            ImageView stoneImage = stoneImages.get(i);

            stoneImage.setTranslateX(stone.getX());
            stoneImage.setTranslateY(stone.getY());
        }
    }

    private void updateStones() {
        for (Stone stone : stones) {
            stone.update();
        }
    }

    /**
     * Update time label
     */
    private void updateTime() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;
        long remainingTime = gameDuration - elapsedTime;
        timeLabel.setText("Time: " + remainingTime / 1000);
    }

    /**
     * Check if game is over
     */
    private void checkGameOver() {
        if (isGameOver) {
            return;
        }

        for (Stone stone : stones) {
            if (Math.abs(player.getX() - stone.getX()) < 50 && Math.abs(player.getY() - stone.getY()) < 50) {
                System.out.println("Player hit by stone");
                isGameOver = true;
                reset();
                main.switchToScene("end", "Ghost is Win!\nTime consuming: " + (System.currentTimeMillis() - startTime) / 1000 + " seconds");
                break;
            } else if (Math.abs(ghost.getX() - stone.getX()) < 50 && Math.abs(ghost.getY() - stone.getY()) < 50) {
                // Ghost is hit by stone, make it transparent and unable to move for 3 seconds
                ghost.setTransparent(true);
                ghost.setCanMove(false);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        ghost.setTransparent(false);
                        ghost.setCanMove(true);
                    }
                }, 3000);
            }
        }

        boolean playerCaught = Math.abs(player.getX() - ghost.getX()) < 50 && Math.abs(player.getY() - ghost.getY()) < 50;
        boolean timeUp = System.currentTimeMillis() - startTime >= gameDuration;

        if (playerCaught) {
            System.out.println("Game Over, Ghost is Win!");
            isGameOver = true;
            reset();
            main.switchToScene("end", "Ghost is Win!\nTime consuming: " + (System.currentTimeMillis() - startTime) / 1000 + " seconds");
        } else if (timeUp) {
            System.out.println("Game Over, Player is Win!");
            isGameOver = true;
            reset();
            main.switchToScene("end", "Player is Win!");
        }


    }
}