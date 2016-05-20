package controller.EnemyCarControllers;

/**
 * Created by tqdu on 5/12/2016.
 */
public enum EnemyCarType {
    BLUE,
    GREEN,
    PINK,
    BLACK;

    public static EnemyCarType getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
