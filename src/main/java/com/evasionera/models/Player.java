package com.evasionera.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Player {

    private static final double MOVEMENT_SPEED = 5.0;
    private static final double ABILITY_DURATION = 1.5;
    private static final double ABILITY_COOLDOWN = 5.0;

    private final ImageView imageView;
    private boolean isUsingAbility;
    private boolean isAbilityCooldown;
    // Add other necessary properties

    public Player(ImageView imageView) {
        this.imageView = imageView;
        // Initialize other properties

        initializeImage();
    }

    private void initializeImage() {
        Image playerImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/player.png")));
        imageView.setImage(playerImage);
    }

    public void moveUp() {
        // Move the player up
    }

    public void moveDown() {
        // Move the player down
    }

    public void moveLeft() {
        // Move the player left
    }

    public void moveRight() {
        // Move the player right
    }

    public void stopVerticalMovement() {
        // Stop the player's vertical movement
    }

    public void stopHorizontalMovement() {
        // Stop the player's horizontal movement
    }

    public void useAbility() {
        // Use the player's ability if not on cooldown
    }

//    private String name;
//    private String imagePath;
//    private double x;
//    private double y;
//    private double speed;
//    private boolean isBoosted;
//    private double boostDuration;
//    private double boostCooldown;
//
//    public Player(String name, String imagePath) {
//        this.name = name;
//        this.imagePath = imagePath;
//        this.x = 0;
//        this.y = 0;
//        this.speed = 1;
//        this.isBoosted = false;
//        this.boostDuration = 1.5;
//        this.boostCooldown = 5;
//    }
//
//    public void boost() {
//        if (!isBoosted) {
//            this.speed *= 2;
//            this.isBoosted = true;
//
//            // Implement logic to reset speed after boostDuration
//            // Implement logic to prevent boosting again until after boostCooldown
//        }
//    }
//
//    public void moveUp() {
//        this.y -= speed;
//    }
//
//    public void moveDown() {
//        this.y += speed;
//    }
//
//    public void moveLeft() {
//        this.x -= speed;
//    }
//
//    public void moveRight() {
//        this.x += speed;
//    }
//
//
//    public void speedUp() {
//        if (!boosted) {
//            speed *= 2;
//            boosted = true;
//            boostTimer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    speedDown();
//                }
//            }, 1500);
//            boostCooldownTimer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    boosted = false;
//                }
//            }, 5000);
//        }
//    }
//
//    public void speedDown() {
//        speed /= 2;
//    }
}
