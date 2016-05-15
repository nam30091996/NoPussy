package controller;

import model.CarEnemy;
import model.Coin;
import model.GameConfig;
import view.GameDrawer;
import view.ImageDrawer;

import java.awt.*;

/**
 * Created by MyComputer on 5/12/2016.
 */
public class EnemyCarController extends SingleController implements Colliable {
    public final static int SPEED = 4; //Nam: để 3 chậm quá :v
    private static int speed = SPEED;

    public EnemyCarController(CarEnemy gameObject, GameDrawer gameDrawer) {
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

    public static void setSpeed(int speed) {
        EnemyCarController.speed = speed;
    }

    @Override
    public void run() {
        super.run();
        //System.out.println(this.speed);
        this.gameVector.dy = speed;
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
        if (c instanceof CarPlayerController) {
            this.getGameObject().setAlive(false);
        } else if(c instanceof BulletController) {
            c.getGameObject().setAlive(false);
        }
    }

    //DuTQ: tạo ô tô địch theo loại ô tô và làn
    public static EnemyCarController create(EnemyCarType enemyCarType, LanePosition lanePosition) {
        CarEnemy carEnemy = null;
        switch (lanePosition) {
            case LANE1:
                carEnemy = new CarEnemy(GameConfig.LANE_1.x,
                                        GameConfig.LANE_2.y,
                                        CarEnemy.WIDTH,
                                        CarEnemy.HEIGHT);
                break;
            case LANE2:
                carEnemy = new CarEnemy(GameConfig.LANE_2.x,
                                        GameConfig.LANE_2.y,
                                        CarEnemy.WIDTH,
                                        CarEnemy.HEIGHT);
                break;
            case LANE3:
                carEnemy = new CarEnemy(GameConfig.LANE_3.x,
                                        GameConfig.LANE_3.y,
                                        CarEnemy.WIDTH,
                                        CarEnemy.HEIGHT);
                break;
            case LANE4:
                carEnemy = new CarEnemy(GameConfig.LANE_4.x,
                                        GameConfig.LANE_4.y,
                                        CarEnemy.WIDTH,
                                        CarEnemy.HEIGHT);
                break;
        }
        ImageDrawer imageDrawer = null;
        switch (enemyCarType) {
            case BLUE:
                imageDrawer = new ImageDrawer("resources/blue_car.png");
                break;
            case RED:
                imageDrawer = new ImageDrawer("resources/red_car.png");
                break;
            case GREEN:
                imageDrawer = new ImageDrawer("resources/green_car.png");
                break;
            case ORANGE:
                imageDrawer = new ImageDrawer("resources/orange_car.png");
                break;
        }
        EnemyCarController enemyCarController = new EnemyCarController(carEnemy,imageDrawer);
        return enemyCarController;
    }
}
