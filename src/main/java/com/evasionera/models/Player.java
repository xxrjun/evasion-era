package com.evasionera.models;

public class Player {
    private double x, y;
    private double speed = 4.5;
    private double boostSpeed = 6.5;
    private long lastBoostTime = 0;
    private long boostCooldown = 5000; // 5 seconds in milliseconds
    private long boostDuration = 1500; // 1.5 seconds in milliseconds

    private long lastHitTime = 0;
    private long hitSlowDuration = 1200; // 1.2 seconds in milliseconds

    private static final double WIDTH = 50.0;
    private static final double HEIGHT = 50.0;
    public static final double WINDOW_WIDTH = 1600.0;
    public static final double WINDOW_HEIGHT = 900.0;

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
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

    public void boost() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastBoostTime > boostCooldown) {
            System.out.println("Player Boosting! Start: " + currentTime + " Last: " + lastBoostTime + " Cooldown: " + boostCooldown + " Duration: " + boostDuration + " Speed: " + speed + " Boost Speed: " + boostSpeed);
            speed = boostSpeed;
            lastBoostTime = currentTime;
        }
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastHitTime < hitSlowDuration) {
            speed = 2.5; // Reduced speed
        } else if (currentTime - lastBoostTime > boostDuration) {
            speed = 5.0; // Normal speed
        }
    }

    public void hitByStone() {
        lastHitTime = System.currentTimeMillis();
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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
