package model;

/**
 * Created by MyComputer on 5/11/2016.
 */
public class CarPlayer extends GameObjectWithHP {

    private static int point = 0;
    private static int HP_DEFAULT = 5;

    private int hp = HP_DEFAULT;

    public CarPlayer(int x, int y, int width, int height) {
        super(x, y, width, height, HP_DEFAULT);
    }

    public void increasePoint() {
        this.point++;
    }

    public void increasePoint(int point) {
        this.point += point;
    }

    public void decreasePoint(int point) {
        this.point -= point;
    }

    public static int getPoint() {
        return point;
    }

    public static void setPoint(int point) {
        CarPlayer.point = point;
    }
}
