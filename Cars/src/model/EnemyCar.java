package model;

/**
 * Created by MyComputer on 5/11/2016.
 */
public class EnemyCar extends GameObjectWithHP {

    public static final int WIDTH = 80;
    public static final int HEIGHT = 155;
    public static final int DEFAULT_HP = 1;

    public EnemyCar(int x, int y, int width, int height) {
        super(x, y, width, height, DEFAULT_HP);
    }
}
