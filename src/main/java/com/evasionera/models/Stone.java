package com.evasionera.models;

import java.util.Random;

public class Stone {
    private double x, y;
    private double dx, dy; // velocity in x and y directions
    private double SPEED = 1.2;
    public static final double WINDOW_WIDTH = 1600.0;
    public static final double WINDOW_HEIGHT = 900.0;
    private static final double WIDTH = 50.0;
    private static final double HEIGHT = 50.0;
    private static final double MIN_EDGE_DISTANCE = 50.0;  // 離牆壁的最小距離，避免石頭一開始延邊界不停加速

    public Stone() {
        Random rand = new Random();

        // Generate random coordinates, ensuring they are at least MIN_EDGE_DISTANCE away from the edges
        this.x = MIN_EDGE_DISTANCE + rand.nextDouble() * (WINDOW_WIDTH - 2 * MIN_EDGE_DISTANCE);
        this.y = MIN_EDGE_DISTANCE + rand.nextDouble() * (WINDOW_HEIGHT - 2 * MIN_EDGE_DISTANCE);

        // Set initial direction to a random angle
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
            SPEED *= 1.05; // Increase speed
        }
        if (y <= 0 || y >= WINDOW_HEIGHT - HEIGHT) {
            dy *= -1; // Reverse direction
            SPEED *= 1.02; // Increase speed
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
