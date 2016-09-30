package controller.PersonController;

import controller.CarPlayerControllers.CarPlayerController;
import controller.Colliable;
import controller.CollisionPool;
import controller.SingleController;
import gamescenes.Level3GameScene;
import model.*;
import view.GameDrawer;
import view.ImageDrawer;

import java.awt.*;

/**
 * Created by 1918 on 21-May-16.
 */
public class PikachuController extends SingleController implements Colliable {
    public PikachuController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        CollisionPool.getInst().add(this);
    }

    public PikachuController(GameObject gameObject, GameDrawer gameDrawer, GameVector gameVector) {
        super(gameObject, gameDrawer, gameVector);
    }

    public void run() {
        if (!Level3GameScene.pause) {
            GameObject carGameObject = CarPlayerController.getCarPlayerController().getGameObject();
            GameVector carGameVector = CarPlayerController.getCarPlayerController().getGameVector();
            if (this.getGameObject().getX() < carGameObject.getX()
                    && this.getGameObject().getY() - EnemyCar.HEIGHT < carGameObject.getY()
                    && this.gameVector.dy != 0) gameVector.dx = 1;
            else if (this.gameObject.getX() > carGameObject.getX()
                    && this.getGameObject().getY() - EnemyCar.HEIGHT < carGameObject.getY()
                    && this.gameVector.dy != 0) gameVector.dx = -1;
            else gameVector.dx = 0;

            if (carGameVector.dy < 0) {
                gameVector.dy = 2;
            }
            super.run();
        }
        if (this.gameObject.getY() > GameConfig.DEFAULT_SCREEN_HEIGHT) gameObject.setAlive(false);
    }

    public void paint(Graphics g) {
        if (this.getGameObject().isAlive()) {
            super.paint(g);
        }
    }

    public static PikachuController create() {
        Pikachu pikachu = new Pikachu(Pikachu.DEFAULT_X, Pikachu.DEFAULT_Y, Pikachu.WIDTH, Pikachu.HEIGHT);
        ImageDrawer imageDrawer = new ImageDrawer("resources/pikachu.png");
        PikachuController pikachuController = new PikachuController(pikachu, imageDrawer);
        return pikachuController;
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof CarPlayerController && !CarPlayerController.isFly()) {
            this.getGameObject().setAlive(false);
        }
    }
}
