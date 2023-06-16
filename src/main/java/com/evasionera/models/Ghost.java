package com.evasionera.models;

public class Ghost {
    // 視窗寬度和高度
    public static final double WINDOW_WIDTH = 1600.0;
    public static final double WINDOW_HEIGHT = 900.0;
    // 鬼的大小
    private static final double WIDTH = 50.0;

    // 鬼的高度
    private static final double HEIGHT = 50.0;
    // 鬼的位置
    private double x, y;
    // 鬼的速度
    private final double speed = 6.75;
    // 鬼是否透明(被石頭砸中後透明)
    private boolean isTransparent;
    // 鬼是否可以移動(被石頭砸中後不可移動)
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

    /**
     * 檢查鬼是否超出視窗範圍，如果超出則將鬼移回視窗範圍內
     */
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

    /**
     * 鬼是否為透明狀態
     *
     * @return true: 透明，false: 不透明
     */
    public boolean isTransparent() {
        return isTransparent;
    }

    /**
     * 設定鬼是否為透明狀態
     *
     * @param transparent true: 透明，false: 不透明
     */
    public void setTransparent(boolean transparent) {
        isTransparent = transparent;
    }

    /**
     * 鬼是否可以移動
     *
     * @return true: 可以移動，false: 不可移動
     */
    public boolean canMove() {
        return canMove;
    }

    /**
     * 設定鬼是否可以移動
     *
     * @param canMove true: 可以移動，false: 不可移動
     */
    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
