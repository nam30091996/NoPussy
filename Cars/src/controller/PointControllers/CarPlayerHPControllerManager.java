package controller.PointControllers;

import controller.ControllerManager;
import model.GameConfig;
import model.GamePoint;
import model.Heart;
import util.GameUtils;
import view.ImageDrawer;

/**
 * Created by MyComputer on 5/15/2016.
 */
public class CarPlayerHPControllerManager extends ControllerManager {

//    public void updateHP(int hp) {
//        this.singleControllerVector.removeAllElements();
//        int [] digits = GameUtils.getDigit(hp);
//        int tempX = GameConfig.DEFAULT_SCREEN_WIDTH - GamePoint.WIDTH * 2;
//        for (int digit : digits) {
//            this.singleControllerVector.add(
//                    GamePointController.create(digit, tempX, GamePointController.DEFAULT_Y, GamePoint.WIDTH, GamePoint.HEIGHT )
//            );
//            tempX += GamePoint.WIDTH - 5;
//        }
//    }

    public void updateHP(int hp) {
        this.singleControllerVector.removeAllElements();
        for(int i = 0; i < hp; i ++) {
            Heart heart = new Heart(GameConfig.DEFAULT_SCREEN_WIDTH - 10 - (i + 1) * 25, GamePointController.DEFAULT_Y, Heart.WIDTH, Heart.HEIGHT);
            ImageDrawer imageDrawer = new ImageDrawer("resources/heart.png");
            CarPlayerHPController carPlayerHPController = new CarPlayerHPController(heart, imageDrawer);
            this.add(carPlayerHPController);
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
