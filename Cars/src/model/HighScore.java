package model;

/**
 * Created by MyComputer on 5/16/2016.
 */
public class HighScore {
    private static int highScore = 0;

    public static int getHighScore() {
        return highScore;
    }

    public static void setHighScore(int highScore) {
        HighScore.highScore = highScore;
    }
}
