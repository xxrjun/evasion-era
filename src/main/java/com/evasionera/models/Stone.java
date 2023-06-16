package com.evasionera.models;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Stone {
    public static final double WINDOW_WIDTH = 1600.0;
    public static final double WINDOW_HEIGHT = 900.0;
    private static final double WIDTH = 50.0;
    private static final double HEIGHT = 50.0;
    private static final double MIN_EDGE_DISTANCE = 80.0;  // 離牆壁的最小距離，避免石頭一開始延邊界不停加速
    private double x, y;
    private double dx, dy; // velocity in x and y directions
    private double SPEED = 1.2;

    public Stone() {
        // 隨機產生石頭的位置
        Random rand = new Random();

        // 設定石頭的初始位置
        this.x = MIN_EDGE_DISTANCE + rand.nextDouble() * (WINDOW_WIDTH - 2 * MIN_EDGE_DISTANCE);
        this.y = MIN_EDGE_DISTANCE + rand.nextDouble() * (WINDOW_HEIGHT - 2 * MIN_EDGE_DISTANCE);

        // 設定石頭初始移動方向
        double angle = rand.nextDouble() * 2 * Math.PI;
        dx = Math.cos(angle) * SPEED;
        dy = Math.sin(angle) * SPEED;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * 更新石頭的位置、碰到邊界時的反彈、反彈後的加速
     */
    public void update() {
        // 更新石頭的位置
        x += dx * SPEED;
        y += dy * SPEED;

        // 碰到邊界時反彈並加速
        if (x <= 0 || x >= WINDOW_WIDTH - WIDTH) {
            dx *= -1; // 反彈(逆轉方向)
            SPEED *= 1.05; // 反彈後加速，因為視窗寬度較寬，所以加速較快
        }
        if (y <= 0 || y >= WINDOW_HEIGHT - HEIGHT) {
            dy *= -1; // 反彈(逆轉方向)
            SPEED *= 1.02; // 反彈後加速，所以加速較慢
        }
    }

    /**
     * 檢查石頭是否打到玩家或鬼
     *
     * @param player 玩家
     * @param ghost  鬼
     */
    public void checkCollision(Player player, Ghost ghost) {
        // 檢查玩家是否被石頭打到
        if (Math.abs(player.getX() - this.x) < 50 && Math.abs(player.getY() - this.y) < 50) {
            player.hitByStone();
        }

        // 檢查鬼是否被石頭打到
        if (Math.abs(ghost.getX() - this.x) < 50 && Math.abs(ghost.getY() - this.y) < 50) {
            // 鬼被石頭打到後，會變透明並且無法移動，3秒後恢復正常
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


}
