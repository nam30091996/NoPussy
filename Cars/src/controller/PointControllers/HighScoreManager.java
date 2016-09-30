package controller.PointControllers;

import controller.ControllerManager;
import gamescenes.GameOverScene;
import model.GameConfig;
import model.GamePoint;
import util.GameUtils;

import java.awt.*;

/**
 * Created by MyComputer on 5/16/2016.
 */
public class HighScoreManager extends ControllerManager {
    public final int DEFAULT_X = 130;

    public void updateHighScore(int highScore) {
        this.singleControllerVector.removeAllElements();
        int[] digits = GameUtils.getDigit(highScore);
        int tempX = GameOverScene.DEFAULT_X[digits.length - 1];
        for (int digit : digits) {
            this.singleControllerVector.add(
                    GamePointController.create(digit, tempX, GameOverScene.DEFAULT_HIGHSCORE_Y, GamePoint.WIDTH * 2, GamePoint.HEIGHT * 2)
            );
            tempX += GamePoint.WIDTH * 2 - 10;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    private static HighScoreManager inst;

    public static HighScoreManager getInst() {
        if (inst == null) {
            inst = new HighScoreManager();
        }
        return inst;
    }

    public static void setNull() {
        inst = null;
    }
}
