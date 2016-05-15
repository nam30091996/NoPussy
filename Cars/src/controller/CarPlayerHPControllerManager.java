package controller;

import model.GameConfig;
import model.GamePoint;
import util.GameUtils;

/**
 * Created by MyComputer on 5/15/2016.
 */
public class CarPlayerHPControllerManager extends ControllerManager {

    public void updateHP(int hp) {
        this.singleControllerVector.removeAllElements();
        int [] digits = GameUtils.getDigit(hp);
        int tempX = GameConfig.DEFAULT_SCREEN_WIDTH - GamePoint.WIDTH * 2;
        for (int digit : digits) {
            this.singleControllerVector.add(
                    GamePointController.create(digit, tempX, GamePointController.DEFAULT_Y, GamePoint.WIDTH, GamePoint.HEIGHT )
            );
            tempX += GamePoint.WIDTH - 5;
        }
    }

    private static CarPlayerHPControllerManager inst;
    public static CarPlayerHPControllerManager getInst() {
        if (inst == null) {
            inst = new CarPlayerHPControllerManager();
        }
        return inst;
    }

    public static void setNull() {
        inst = null;
    }
}
