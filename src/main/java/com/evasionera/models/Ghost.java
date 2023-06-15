// Ghost.java
package com.evasionera.models;


public class Ghost {
    private double x, y;
    private final double speed = 5;

    private static final double WIDTH = 50.0;
    private static final double HEIGHT = 50.0;
    public static final double WINDOW_WIDTH = 1600.0;
    public static final double WINDOW_HEIGHT = 900.0;

    private boolean isTransparent;
    private boolean canMove;

    public Ghost(double x, double y) {
        this.x = x;
        this.y = y;
        canMove = true;
    }

    public void moveUp() {
        y -= speed;
        checkBounds();
    }

    public void moveDown() {
        y += speed;
        checkBounds();
    }

    public void moveLeft() {
        x -= speed;
        checkBounds();
    }

    public void moveRight() {
        x += speed;
        checkBounds();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    private void checkBounds() {
        if (x < 0) {
            x = 0;
        } else if (x > WINDOW_WIDTH - WIDTH) {
            x = WINDOW_WIDTH - WIDTH;
        }

        if (y < 0) {
            y = 0;
        } else if (y > WINDOW_HEIGHT - HEIGHT) {
            y = WINDOW_HEIGHT - HEIGHT;
        }
    }

    public boolean isTransparent() {
        return isTransparent;
    }

    public void setTransparent(boolean transparent) {
        isTransparent = transparent;
    }

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }


}
