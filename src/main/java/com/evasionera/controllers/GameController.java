// GameController.java
package com.evasionera.controllers;

import com.evasionera.models.Ghost;
import com.evasionera.models.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.util.HashSet;
import java.util.Set;

public class GameController extends BaseController {
    private static final Set<KeyCode> keys = new HashSet<>();
    @FXML
    private ImageView playerImage;
    @FXML
    private ImageView ghostImage;
    @FXML
    private Label timeLabel;
    @FXML
    private AnchorPane gamePane;
    private static boolean isGameOver = false;
    private static Player player;
    private static Ghost ghost;
    private static long startTime;

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

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                handleKeys();
                player.update();
                updatePositions();
                updateTime();
                checkGameOver();
            }
        }.start();
    }


    /**
     * Handle key presses
     */
    private void handleKeys() {
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

    /**
     * Update player and ghost positions
     */
    private void updatePositions() {
        playerImage.setTranslateX(player.getX());
        playerImage.setTranslateY(player.getY());
        ghostImage.setTranslateX(ghost.getX());
        ghostImage.setTranslateY(ghost.getY());
    }

    /**
     * Update time label
     */
    private void updateTime() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;
        long remainingTime = 60000 - elapsedTime; // 60 seconds in milliseconds
        timeLabel.setText("Time: " + remainingTime / 1000);
    }

    /**
     * Check if game is over
     */
    private void checkGameOver() {
        if (isGameOver) {
            return;
        }

        if (Math.abs(player.getX() - ghost.getX()) < 50 && Math.abs(player.getY() - ghost.getY()) < 50) {
            System.out.println("Game Over, Ghost is Win!");
            isGameOver = true;
            main.switchToScene("end", "Ghost is Win!\nTime consuming: " + (System.currentTimeMillis() - startTime) / 1000 + " seconds");
        } else if (System.currentTimeMillis() - startTime >= 60000) {
            System.out.println("Game Over, Player is Win!");
            isGameOver = true;
            main.switchToScene("end", "Player is Win!");
        }
    }

    /**
     * Reset game state to initial state
     */
    public static void reset() {
        isGameOver = false;
        player = new Player(0, 400);
        ghost = new Ghost(1200, 400);
        startTime = System.currentTimeMillis();
        keys.clear();
    }
}
