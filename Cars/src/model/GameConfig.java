package model;

import java.awt.*;

/**
 * Created by qhuydtvt on 5/6/2016.
 */
public class GameConfig {
    //DuTQ: điểm xuất phát của xe địch
    public static final Point LANE_1 = new Point(15,-CarEnemy.HEIGHT);
    public static final Point LANE_2 = new Point(85,-CarEnemy.HEIGHT);
    public static final Point LANE_3 = new Point(165,-CarEnemy.HEIGHT);
    public static final Point LANE_4 = new Point(235,-CarEnemy.HEIGHT);

    public static final int DEFAULT_SCREEN_WIDTH = 300;
    public static final int DEFAULT_SCREEN_HEIGHT = 500;
    public static final int DEFAULT_THREAD_DELAY = 17;

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
