package controller.EnemyCarControllers;

import controller.BulletControllers.BulletController;
import controller.CarPlayerControllers.CarPlayerController;
import controller.Colliable;
import controller.CollisionPool;
import controller.SingleController;
import model.EnemyCar;
import model.GameConfig;
import model.GameVector;
import model.LifeState;
import view.DeadAnimationDrawer;
import view.EnemyCarDrawer;
import view.GameDrawer;
import view.ImageDrawer;

import java.awt.*;

/**
 * Created by MyComputer on 5/12/2016.
 */
public class EnemyCarController extends SingleController implements Colliable {
    public final static int SPEED = 5; //Nam: để 3 chậm quá :v
    private static int speed = SPEED;

    public EnemyCarController(EnemyCar gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = speed;
        CollisionPool.getInst().add(this);
    }

    public static void increaseSpeed() {
        speed ++;
    }

    public static void increaseSpeed(int delta) {
        speed += delta;
    }

    public static int getSpeed() {
        return speed;
    }

    public static void setSpeed(int speed) {
        EnemyCarController.speed = speed;
    }

    @Override
    public void run() {
        super.run();
        if(((EnemyCar)this.getGameObject()).getLifeState() == LifeState.DYING) {
            CollisionPool.getInst().remove(this);
            this.gameVector.dy = 0;
        }
        else {
            this.gameVector.dy = speed;
        }
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
        if (c instanceof CarPlayerController || c instanceof BulletController) {
            ((EnemyCar)this.gameObject).decreaseHP();
        }
    }

    //DuTQ: tạo ô tô địch theo loại ô tô và làn
    public static EnemyCarController create(EnemyCarType enemyCarType, LanePosition lanePosition) {
        EnemyCar enemyCar = null;
        switch (lanePosition) {
            case LANE1:
                enemyCar = new EnemyCar(GameConfig.LANE_1.x,
                                        GameConfig.LANE_2.y,
                                        EnemyCar.WIDTH,
                                        EnemyCar.HEIGHT);
                break;
            case LANE2:
                enemyCar = new EnemyCar(GameConfig.LANE_2.x,
                                        GameConfig.LANE_2.y,
                                        EnemyCar.WIDTH,
                                        EnemyCar.HEIGHT);
                break;
            case LANE3:
                enemyCar = new EnemyCar(GameConfig.LANE_3.x,
                                        GameConfig.LANE_3.y,
                                        EnemyCar.WIDTH,
                                        EnemyCar.HEIGHT);
                break;
            case LANE4:
                enemyCar = new EnemyCar(GameConfig.LANE_4.x,
                                        GameConfig.LANE_4.y,
                                        EnemyCar.WIDTH,
                                        EnemyCar.HEIGHT);
                break;
        }
        ImageDrawer imageDrawer = null;
        switch (enemyCarType) {
            case BLUE:
                imageDrawer = new ImageDrawer("resources/blue_car.png");
                break;
            case GREEN:
                imageDrawer = new ImageDrawer("resources/green_car.png");
                break;
            case PINK:
                imageDrawer = new ImageDrawer("resources/pink_car.png");
                break;
        }
        String[] images = new String[] {"resources/explosion/Explosion1.png",
                "resources/explosion/Explosion2.png",
                "resources/explosion/Explosion3.png",
                "resources/explosion/Explosion4.png",
                "resources/explosion/Explosion5.png",
                "resources/explosion/Explosion6.png"};
        DeadAnimationDrawer deadAnimationDrawer = new DeadAnimationDrawer(images);
        EnemyCarDrawer enemyCarDrawer = new EnemyCarDrawer(imageDrawer, deadAnimationDrawer);;
        EnemyCarController enemyCarController = new EnemyCarController(enemyCar, enemyCarDrawer);
        return enemyCarController;
    }
}
