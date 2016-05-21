package model;

/**
 * Created by MyComputer on 5/11/2016.
 */
public class CarPlayer extends GameObjectWithHP {

    private static int point = 0;
    private static int HP_DEFAULT = 10;
    public static final int HP_MAX = 100;

    private int hp = HP_DEFAULT;
    private int battery = 5;
    private CarPlayerStatus carPlayerStatus;

    public CarPlayer(int x, int y, int width, int height) {
        super(x, y, width, height, HP_DEFAULT);
        this.carPlayerStatus = CarPlayerStatus.NORMAL;
    }

    public CarPlayerStatus getCarPlayerStatus() {
        return carPlayerStatus;
    }

    public void setCarPlayerStatus(CarPlayerStatus carPlayerStatus) {
        this.carPlayerStatus = carPlayerStatus;
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

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public void decreaseBattery() {
        this.battery -= 1;
    }
}
