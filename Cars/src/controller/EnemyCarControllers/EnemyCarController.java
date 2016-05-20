package controller.EnemyCarControllers;

import controller.BulletControllers.BulletController;
import controller.CarPlayerControllers.CarPlayerController;
import controller.Colliable;
import controller.CollisionPool;
import controller.PersonController.PersonController;
import controller.PersonController.PersonControllerManager;
import controller.SingleController;
import gamescenes.PlayGameScene;
import model.*;
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
    private int count = 0;
    private EnemyCarType enemyCarType;
    private LanePosition lanePosition;


    public EnemyCarController(EnemyCar gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = speed;
        CollisionPool.getInst().add(this);
    }

    public EnemyCarController(EnemyCar gameObject, GameDrawer gameDrawer, EnemyCarType enemyCarType) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = speed;
        this.enemyCarType = enemyCarType;
        CollisionPool.getInst().add(this);
    }

    public EnemyCarController(EnemyCar gameObject, GameDrawer gameDrawer, EnemyCarType enemyCarType, LanePosition lanePosition) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = speed;
        this.enemyCarType = enemyCarType;
        this.lanePosition = lanePosition;
        CollisionPool.getInst().add(this);
    }

    public EnemyCarType getEnemyCarType() {
        return enemyCarType;
    }

    public void setEnemyCarType(EnemyCarType enemyCarType) {
        this.enemyCarType = enemyCarType;
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
        if ((!PlayGameScene.pause) &&
                (PersonControllerManager.getInst().size() == 0 ||
                        this.lanePosition != ((Person)PersonControllerManager.getInst().getSingleControllerVector().get(0).getGameObject()).getLane() ||
                        (this.lanePosition == ((Person)PersonControllerManager.getInst().getSingleControllerVector().get(0).getGameObject()).getLane() && (this.gameObject.getY() <= (350 - EnemyCar.HEIGHT - 10) || this.gameObject.getY() >= (350 - EnemyCar.HEIGHT))))){
            super.run();
            count ++;
        }
        if(GameConfig.getInst().durationInSeconds(count) >= 2 && this.enemyCarType == EnemyCarType.BLACK) {
            count = 0;
            int lane;
            do {
                lane = (int) (Math.random() * 4);
                this.getGameObject().setX(GameConfig.LANE[lane].x);
            } while (this.check() == false);

        }
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

    private boolean check() {
        for(SingleController enemyCarController : EnemyCarControllerManager.getInst().getSingleControllerVector()) {
            if(((EnemyCarController)enemyCarController).getEnemyCarType() != EnemyCarType.BLACK && this.getGameObject().getRect().intersects(enemyCarController.getGameObject().getRect())) return false;
        }
        return true;
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
//        if(c instanceof EnemyCarController) {
//            if(this.enemyCarType == EnemyCarType.BLACK) {
//
//            }
//        }
    }

    //DuTQ: tạo ô tô địch theo loại ô tô và làn
    public static EnemyCarController create(EnemyCarType enemyCarType, LanePosition lanePosition) {
        EnemyCarController enemyCarController = null;
        if (PersonControllerManager.getInst().size() == 0 || lanePosition != ((Person)PersonControllerManager.getInst().getSingleControllerVector().get(0).getGameObject()).getLane()) {
            EnemyCar enemyCar = null;
            switch (lanePosition) {
                case LANE1:
                    enemyCar = new EnemyCar(GameConfig.LANE[0].x,
                            GameConfig.LANE[0].y,
                            EnemyCar.WIDTH,
                            EnemyCar.HEIGHT);
                    break;
                case LANE2:
                    enemyCar = new EnemyCar(GameConfig.LANE[1].x,
                            GameConfig.LANE[1].y,
                            EnemyCar.WIDTH,
                            EnemyCar.HEIGHT);
                    break;
                case LANE3:
                    enemyCar = new EnemyCar(GameConfig.LANE[2].x,
                            GameConfig.LANE[2].y,
                            EnemyCar.WIDTH,
                            EnemyCar.HEIGHT);
                    break;
                case LANE4:
                    enemyCar = new EnemyCar(GameConfig.LANE[3].x,
                            GameConfig.LANE[3].y,
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
                case BLACK:
                    imageDrawer = new ImageDrawer("resources/black_car.png");
                    break;
            }
            String[] images = new String[]{"resources/explosion/Explosion1.png",
                    "resources/explosion/Explosion2.png",
                    "resources/explosion/Explosion3.png",
                    "resources/explosion/Explosion4.png",
                    "resources/explosion/Explosion5.png",
                    "resources/explosion/Explosion6.png"};
            DeadAnimationDrawer deadAnimationDrawer = new DeadAnimationDrawer(images);
            EnemyCarDrawer enemyCarDrawer = new EnemyCarDrawer(imageDrawer, deadAnimationDrawer);
            enemyCarController = new EnemyCarController(enemyCar, enemyCarDrawer, enemyCarType, lanePosition);

        }
        return enemyCarController;
    }

}
