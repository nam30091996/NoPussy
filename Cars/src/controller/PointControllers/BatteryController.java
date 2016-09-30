package controller.PointControllers;

import controller.CarPlayerControllers.CarPlayerController;
import controller.SingleController;
import model.*;
import view.GameDrawer;
import view.ImageDrawer;

import java.awt.*;

/**
 * Created by MyComputer on 5/21/2016.
 */
public class BatteryController extends SingleController {
    public static final int WIDTH_BATTERY = 20;
    public static final int HEIGHT_BATTERY = 40;

    public BatteryController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
    }

    public BatteryController(GameObject gameObject, GameDrawer gameDrawer, GameVector gameVector) {
        super(gameObject, gameDrawer, gameVector);
    }

    @Override
    public void run() {
        super.run();
    }

    public void updateBattery(int battery) {
        switch (battery) {
            case 1:
                this.gameDrawer = new ImageDrawer("resources/battery/battery1.png");
                break;
            case 2:
                this.gameDrawer = new ImageDrawer("resources/battery/battery2.png");
                break;
            case 3:
                this.gameDrawer = new ImageDrawer("resources/battery/battery3.png");
                break;
            case 4:
                this.gameDrawer = new ImageDrawer("resources/battery/battery4.png");
                break;
            case 5:
                this.gameDrawer = new ImageDrawer("resources/battery/battery5.png");
                break;
        }
    }

    private static BatteryController inst;

    public static BatteryController getInst() {
        if (inst == null) {
            GameObject gameObject = new GameObject(GameConfig.DEFAULT_SCREEN_WIDTH - WIDTH_BATTERY - 10, GamePointController.DEFAULT_Y + Heart.HEIGHT + 10, WIDTH_BATTERY, HEIGHT_BATTERY);
            ImageDrawer imageDrawer = new ImageDrawer("resources/battery/battery5.png");
            inst = new BatteryController(gameObject, imageDrawer);
        }
        return inst;
    }

}
