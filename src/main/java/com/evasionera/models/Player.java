package com.evasionera.models;

public class Player {
    public static final double WINDOW_WIDTH = 1600.0;
    public static final double WINDOW_HEIGHT = 900.0;
    private static final double WIDTH = 50.0;
    private static final double HEIGHT = 50.0;
    // 玩家的正常速度
    private final double normalSpeed = 6;
    // 玩家被石頭砸中後的減速速度
    private final double reducedSpeed = 3.5;
    // 玩家使用技能後的加速速度
    private final double boostSpeed = 6.5;
    // 技能冷卻時間
    private final long boostCooldown = 5000;
    // 技能持續時間
    private final long boostDuration = 1200;
    // 被石頭砸中後的減速時間
    private final long hitSlowDuration = 450;
    // 玩家的位置
    private double x, y;
    // 玩家的速度(當前)
    private double speed = 6;
    // 技能最後使用時間
    private long lastBoostTime = 0;
    // 玩家被石頭砸中的時間
    private long lastHitTime = 0;

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

    /**
     * 使用技能: 加速
     */
    public void boost() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastBoostTime > boostCooldown) {
            System.out.println("Player Boosting! Start: " + currentTime + " Last: " + lastBoostTime + " Cooldown: " + boostCooldown + " Duration: " + boostDuration + " Speed: " + speed + " Boost Speed: " + boostSpeed);
            speed = boostSpeed;
            lastBoostTime = currentTime;
        }
    }

    /**
     * 更新玩家的狀態
     */
    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastHitTime < hitSlowDuration) {
            speed = reducedSpeed; // Reduced speed
        } else if (currentTime - lastBoostTime > boostDuration) {
            speed = normalSpeed; // Normal speed
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
