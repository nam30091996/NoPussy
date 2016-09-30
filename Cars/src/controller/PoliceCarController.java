package controller;

import controller.CarPlayerControllers.CarPlayerController;
import gamescenes.Level3GameScene;
import model.*;
import util.GameUtils;
import view.GameDrawer;
import view.ImageDrawer;

/**
 * Created by MyComputer on 5/21/2016.
 */
public class PoliceCarController extends SingleController implements Colliable {

    private static PoliceCarController policeCarController = null;
    private int time = 0;

    public PoliceCarController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        CollisionPool.getInst().add(this);
    }

    public PoliceCarController(GameObject gameObject, GameDrawer gameDrawer, GameVector gameVector) {
        super(gameObject, gameDrawer, gameVector);
        CollisionPool.getInst().add(this);
    }

    @Override
    public void run() {
        if (!Level3GameScene.pause) {
            time++;
            if (GameConfig.getInst().durationInSeconds(time) >= 10) {
                GameObject carGameObject = CarPlayerController.getCarPlayerController().getGameObject();
                GameVector carGameVector = CarPlayerController.getCarPlayerController().getGameVector();

                if (carGameObject.getX() > this.getGameObject().getX()) this.gameVector.dx = 2;
                else if (carGameObject.getX() < this.getGameObject().getX()) this.gameVector.dx = -2;
                else this.gameVector.dx = 0;

                if (carGameObject.getY() > this.getGameObject().getY()) this.gameVector.dy = 2;
                else if (carGameObject.getY() < this.getGameObject().getY()) this.gameVector.dy = -2;
                else this.gameVector.dy = 0;

                if (GameConfig.getInst().durationInSeconds(time) >= 13) {
                    time = 0;
                    this.gameVector.dy = 2;
                    this.gameVector.dx = 0;
                }
            }
            if (this.gameObject.getY() >= GameConfig.DEFAULT_SCREEN_HEIGHT)
                this.gameObject.setY(GameConfig.DEFAULT_SCREEN_HEIGHT);
            super.run();
        }
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof CarPlayerController && !CarPlayerController.isFly()) {
            GameUtils.playSound("resources/die_sound.wav", false);
            c.getGameObject().setAlive(false);
        }
    }

    public static PoliceCarController getPoliceCarController() {
        if (policeCarController == null) {
            policeCarController = new PoliceCarController(
                    new PoliceCar(CarPlayerController.getCarPlayerController().gameObject.getX(), GameConfig.DEFAULT_SCREEN_HEIGHT, EnemyCar.WIDTH, EnemyCar.HEIGHT),
                    new ImageDrawer("resources/police.png"));
        }
        return policeCarController;
    }

    public static void setNull() {
        policeCarController = null;
    }
}
