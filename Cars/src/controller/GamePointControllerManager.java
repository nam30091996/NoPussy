package controller;

import model.GameConfig;
import model.GamePoint;
import util.GameUtils;

import java.awt.*;

/**
 * Created by tqdu on 5/14/2016.
 */
public class GamePointControllerManager extends ControllerManager {

    private GamePointControllerManager(){

    };


    @Override
    public void run() {
        super.run();
    }

    public void updatePoint(int point) {
        this.singleControllerVector.removeAllElements();
        int [] digits = GameUtils.getDigit(point);
        int tempX = GamePointController.DEFAULT_X;
        for (int digit : digits) {
            this.singleControllerVector.add(
                    GamePointController.create(digit, tempX, GamePointController.DEFAULT_Y, GamePoint.WIDTH, GamePoint.HEIGHT)
            );
            tempX += GamePoint.WIDTH - 3;
        }
    }

    public void updatePointGameOver(int point) {
        this.singleControllerVector.removeAllElements();
        int [] digits = GameUtils.getDigit(point);
        int tempX = GameConfig.DEFAULT_SCREEN_WIDTH / 4;
        for (int digit : digits) {
            this.singleControllerVector.add(
                    GamePointController.create(digit, tempX, GameConfig.DEFAULT_SCREEN_HEIGHT / 4, GamePoint.WIDTH * 4, GamePoint.HEIGHT * 4)
            );
            tempX += GamePoint.WIDTH * 4 - 12;
        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    private static GamePointControllerManager inst;
    public static GamePointControllerManager getInst() {
        if (inst == null) {
            inst = new GamePointControllerManager();
        }
        return inst;
    }

    public static void setNull() {
        inst = null;
    }
}
