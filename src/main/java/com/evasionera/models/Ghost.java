package com.evasionera.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Ghost {
    private static final double MOVEMENT_SPEED = 6.0;

    private ImageView imageView;
    private boolean hasCaughtPlayer;
    // Add other necessary properties

    public Ghost(ImageView imageView) {
        this.imageView = imageView;
        // Initialize other properties

        initializeImage();
    }

    private void initializeImage() {
        Image ghostImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/ghost.png")));
        imageView.setImage(ghostImage);
    }

    public void moveUp() {
        // Move the ghost up
    }

    public void moveDown() {
        // Move the ghost down
    }

    public void moveLeft() {
        // Move the ghost left
    }

    public void moveRight() {
        // Move the ghost right
    }

    public boolean hasCaughtPlayer() {
        // Return whether the ghost has caught the player
        return hasCaughtPlayer;
    }
}
