package com.evasionera.models;

import java.util.Random;

public class Stone {
    private double x, y;
    private double dx, dy; // velocity in x and y directions
    private static double SPEED = 1.0;
    private static final double WINDOW_WIDTH = 1280.0;
    private static final double WINDOW_HEIGHT = 720.0;
    private static final double WIDTH = 50.0;
    private static final double HEIGHT = 50.0;

    public Stone(double x, double y) {
        this.x = x;
        this.y = y;

        // Set initial direction to a random angle
        Random rand = new Random();
        double angle = rand.nextDouble() * 2 * Math.PI;
        dx = Math.cos(angle) * SPEED;
        dy = Math.sin(angle) * SPEED;
    }

    public void update() {
        // Update position
        x += dx * SPEED;
        y += dy * SPEED;

        // Check for collision with edges and bounce
        if (x <= 0 || x >= WINDOW_WIDTH - WIDTH) {
            dx *= -1; // Reverse direction
//            SPEED *= 1.1; // Increase speed
        }
        if (y <= 0 || y >= WINDOW_HEIGHT - HEIGHT) {
            dy *= -1; // Reverse direction
//            SPEED *= 1.1; // Increase speed
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
