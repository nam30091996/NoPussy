package controller.PointControllers;

import controller.ControllerManager;
import model.GameConfig;
import model.GamePoint;
import util.GameUtils;

import java.awt.*;

/**
 * Created by tqdu on 5/14/2016.
 */
public class GamePointControllerManager extends ControllerManager {
    public final int DEFAULT_X = GameConfig.DEFAULT_SCREEN_WIDTH / 8 * 3;
    public final int DEFAULT_Y = GameConfig.DEFAULT_SCREEN_HEIGHT / 7 * 2 + 10;

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

    public void paintPointGameOver(int point) {
        this.singleControllerVector.removeAllElements();
        int [] digits = GameUtils.getDigit(point);
        int tempX = DEFAULT_X ;
        for (int digit : digits) {
            this.singleControllerVector.add(
                    GamePointController.create(digit, tempX, DEFAULT_Y, GamePoint.WIDTH * 2, GamePoint.HEIGHT * 2)
            );
            tempX += GamePoint.WIDTH * 2 - 10;
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
