package com.evasionera.controllers;

import com.evasionera.EvasionEraApplication;
import com.evasionera.models.Ghost;
import com.evasionera.models.MyTimer;
import com.evasionera.models.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class GameController {
    @FXML
    private Pane gamePane;
    @FXML
    private ImageView playerImageView;
    @FXML
    private ImageView ghostImageView;

    private Player player;
    private Ghost ghost;
    private MyTimer myTimer;

    private EvasionEraApplication Main;


    public void setMain(EvasionEraApplication Main) {
        this.Main = Main;
    }

    public void initialize() {
        // Initialize player, ghost, and timer objects
        player = new Player(playerImageView);
        ghost = new Ghost(ghostImageView);
        myTimer = new MyTimer();
        myTimer.setOnCountdownListener(this::handleCountdown); // Add event listener for countdown updates
        myTimer.start(60); // Start the timer with a duration of 60 seconds
    }

    public void handleKeyPress(KeyEvent event) {
        // Handle key press events for player movement
        switch (event.getCode()) {
            case W:
                player.moveUp();
                break;
            case S:
                player.moveDown();
                break;
            case A:
                player.moveLeft();
                break;
            case D:
                player.moveRight();
                break;
            case SPACE:
                player.useAbility();
                break;
            default:
                break;
        }
    }

    public void handleKeyRelease(KeyEvent event) {
        // Handle key release events for player movement
        switch (event.getCode()) {
            case W:
            case S:
                player.stopVerticalMovement();
                break;
            case A:
            case D:
                player.stopHorizontalMovement();
                break;
            default:
                break;
        }
    }

    private void handleCountdown(int remainingTime) {
        // Update game state based on remaining time
        if (remainingTime == 0) {
            // Game over, handle game result
            boolean isGhostWinner = ghost.hasCaughtPlayer();
            handleGameOver(isGhostWinner);
        }
    }

    private void handleGameOver(boolean isGhostWinner) {
        // Handle game over logic (e.g., display winner, reset game, etc.)

        if(isGhostWinner){
            System.out.println("Ghost wins");
        }else{
            System.out.println("Player wins");
        }
    }

//    @FXML
//    private Pane gamePane;
//
//    @FXML
//    private ImageView playerImageView;
//
//    @FXML
//    private Label timeLabel;
//
//    @FXML
//    private Pane playerPane;
//
//
//    private Player player;
//
//    private EvasionEraApplication Main;
//
//    public void setMain(EvasionEraApplication Main) {
//        this.Main = Main;
//    }
//
//
//    public void initialize() {
//        playerImageView = new ImageView("/images/player.png");
//        player = new Player(playerImageView , "player");
//
//        gamePane.setFocusTraversable(true);
//        gamePane.setOnKeyPressed(this::handleKeyPressed);
//        gamePane.setOnKeyReleased(this::handleKeyReleased);
//
//        // TODO: Start a timer to update the remaining time
//    }
//
//    private void handleKeyPressed(KeyEvent event) {
//        switch (event.getCode()) {
//            case W -> player.moveUp();
//            case S -> player.moveDown();
//            case A -> player.moveLeft();
//            case D -> player.moveRight();
//            case SPACE -> player.speedUp();
//        }
//    }
//
//    private void handleKeyReleased(KeyEvent event) {
//        if (event.getCode() == KeyCode.SPACE) {
//            player.speedDown();
//        }
//    }
}