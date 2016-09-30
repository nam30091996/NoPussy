package controller.BulletControllers;

import controller.*;
import controller.CarPlayerControllers.CarPlayerController;
import controller.EnemyCarControllers.EnemyCarController;
import controller.PersonController.PersonController;
import controller.StoneControllers.StoneController;
import model.Bullet;
import model.CarPlayer;
import model.GameConfig;
import model.GameVector;
import view.ImageDrawer;


/**
 * Created by qhuydtvt on 4/29/2016.
 */
public class BulletController extends SingleController implements Colliable {

    public static final int SPEED = 21;

    public BulletController(Bullet gameObject, ImageDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        gameVector.dy = -SPEED;
        CollisionPool.getInst().add(this);
    }

    public BulletController(Bullet gameObject, ImageDrawer gameDrawer, GameVector gameVector) {
        super(gameObject, gameDrawer);
        this.gameVector = gameVector;
        CollisionPool.getInst().add(this);
    }

    @Override
    public void run() {
        super.run();
        if (!GameConfig.getInst().isInScreen(this.gameObject)) {
            this.gameObject.setAlive(false);
        }
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof EnemyCarController || c instanceof StoneController) {
            this.getGameObject().setAlive(false);
        } else if (c instanceof PersonController) {
            ((CarPlayer) CarPlayerController.getCarPlayerController().getGameObject()).decreaseHP(2);
            this.getGameObject().setAlive(false);
        }
    }
}







