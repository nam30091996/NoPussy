package controller.StoneControllers;

import controller.BulletControllers.BulletController;
import controller.CarPlayerControllers.CarPlayerController;
import controller.Colliable;
import controller.CollisionPool;
import controller.SingleController;
import model.GameConfig;
import model.GameObject;
import model.Stone;
import view.GameDrawer;
import view.ImageDrawer;

import java.awt.*;

/**
 * Created by 1918 on 15-May-16.
 */
public class StoneController extends SingleController implements Colliable {
    private static final int SPEED = 1;
    private int speed = SPEED;

    public StoneController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = speed;
        CollisionPool.getInst().add(this);
    }

    @Override
    public void run() {
        super.run();
        if (!GameConfig.getInst().isInScreen(this.gameObject) && !GameConfig.getInst().isInStartPosition(this.gameObject)) {
            this.gameObject.setAlive(false);
        }
    }

    @Override
    public void paint(Graphics g) {
        if (this.gameObject.isAlive()) {
            super.paint(g);
        }
    }

    @Override
    public void onCollide(Colliable c) {
        if ((c instanceof CarPlayerController && !CarPlayerController.isFly()) || c instanceof BulletController) {
            this.gameObject.setAlive(false);
        }
    }

    public static StoneController create() {
        Stone stone = new Stone((int) (Math.random() * (GameConfig.DEFAULT_SCREEN_WIDTH - Stone.STONE_WIDTH)), -Stone.STONE_HEIGHT, Stone.STONE_WIDTH, Stone.STONE_HEIGHT);
        ImageDrawer imageDrawer = new ImageDrawer("resources/stone.png");
        StoneController stoneController = new StoneController(stone, imageDrawer);
        return stoneController;
    }

}
