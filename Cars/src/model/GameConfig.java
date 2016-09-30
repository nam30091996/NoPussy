package model;

import java.awt.*;

/**
 * Created by qhuydtvt on 5/6/2016.
 */
public class GameConfig {
    public static final int DEFAULT_SCREEN_WIDTH = 420;
    public static final int DEFAULT_SCREEN_HEIGHT = 720;
    public static final int DEFAULT_THREAD_DELAY = 17;
    //DuTQ: điểm xuất phát của xe địch
    public static final Point[] LANE = new Point[]{new Point(GameConfig.DEFAULT_SCREEN_WIDTH / 8 - EnemyCar.WIDTH / 2, -EnemyCar.HEIGHT),
            new Point(GameConfig.DEFAULT_SCREEN_WIDTH / 8 * 3 - EnemyCar.WIDTH / 2, -EnemyCar.HEIGHT),
            new Point(GameConfig.DEFAULT_SCREEN_WIDTH / 8 * 5 - EnemyCar.WIDTH / 2 + 8, -EnemyCar.HEIGHT),
            new Point(GameConfig.DEFAULT_SCREEN_WIDTH / 8 * 7 - EnemyCar.WIDTH / 2, -EnemyCar.HEIGHT)};


    private int screenWidth;
    private int screenHeight;
    private int threadDelay;

    private GameConfig(int screenWidth, int screenHeight, int threadDelay) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.threadDelay = threadDelay;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getThreadDelay() {
        return threadDelay;
    }

    public int durationInMiliseconds(int count) {
        return count * threadDelay;
    }

    public int durationInSeconds(int count) {
        return count * threadDelay / 1000;
    }

    public boolean isInScreen(GameObject gameObject) {
        return gameObject.getX() > 0 && gameObject.getX() < this.screenWidth
                && gameObject.getY() > 0 && gameObject.getY() < this.screenHeight;
    }


    public boolean isInStartPosition(GameObject gameObject) {
        //Nam: sửa thành <= 0, tại nếu speed của EnemyCar là ước của cái HEIGHT thì nó có trường hợp = 0, sẽ bị mất luôn
        return gameObject.getY() <= 0;
    }

    public boolean isInScreen(Rectangle rect) {
        return rect.getX() > 0 && rect.getX() + rect.getWidth() < this.screenWidth
                && rect.getY() > 25 && rect.getY() + rect.getHeight() < this.screenHeight;
    }

    private static GameConfig inst;

    public static GameConfig getInst() {
        if (inst == null) {
            inst = new GameConfig(DEFAULT_SCREEN_WIDTH,
                    DEFAULT_SCREEN_HEIGHT,
                    DEFAULT_THREAD_DELAY);
        }
        return inst;
    }
}
