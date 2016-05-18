package controller.CarPlayerControllers;

import controller.*;
import controller.BulletControllers.BulletController;
import controller.BulletControllers.BulletControllerManager;
import controller.CoinControllers.CoinController;
import controller.CoinControllers.CoinType;
import controller.EnemyCarControllers.EnemyCarController;
import controller.GiftControllers.GiftController;
import controller.StoneControllers.StoneController;
import gamescenes.PlayGameScene;
import model.*;
import util.GameUtils;
import view.AnimationDrawer;
import view.CarPlayerDrawer;
import view.GameDrawer;
import view.ImageDrawer;

import java.awt.*;

/**
 * Created by MyComputer on 5/11/2016.
 */
public class CarPlayerController extends SingleController implements Colliable {
    public final int SPEED = 7;
    public final int TIME_SHOOT = 10;
    private boolean ableToShoot = false;
    private boolean shield = false;
    private BulletControllerManager bulletControllerManager;
    private static final int MAX_BULLET_COUNT = 1;
    private int count = 0;

    public CarPlayerController(CarPlayer gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        bulletControllerManager = new BulletControllerManager();
        CollisionPool.getInst().add(this);
    }

    public void move(CarPlayerDirection carPlayerDirection) {
        switch (carPlayerDirection) {
            case NONE:
                break;
            case UP:
                this.gameVector.dy = -SPEED;
                break;
            case DOWN:
                this.gameVector.dy = SPEED;
                break;
            case LEFT:
                this.gameVector.dx = -SPEED;
                break;
            case RIGHT:
                this.gameVector.dx = SPEED;
                break;
            case STOP_X:
                this.gameVector.dx = 0;
                break;
            case STOP_Y:
                this.gameVector.dy = 0;
                break;
        }

    }

    public void shoot() {
        if(ableToShoot) {
            if (bulletControllerManager.size() < MAX_BULLET_COUNT) {
                Bullet bullet = new Bullet(
                        gameObject.getX() + gameObject.getWidth() / 2 - Bullet.DEFAULT_WIDTH / 2,
                        gameObject.getY(),
                        Bullet.DEFAULT_WIDTH,
                        Bullet.DEFAULT_HEIGHT
                );
                ImageDrawer imageDrawer = new ImageDrawer("resources/bullet.png");
                BulletController bulletController = new BulletController(
                        bullet,
                        imageDrawer
                );

                this.bulletControllerManager.add(bulletController);
            }
        }
    }

    private static CarPlayerController carPlayerController;
    public static CarPlayerController getCarPlayerController() {
        if (carPlayerController == null) {
            CarPlayer carPlayer = new CarPlayer((GameConfig.DEFAULT_SCREEN_WIDTH - EnemyCar.WIDTH) / 2, GameConfig.DEFAULT_SCREEN_HEIGHT - EnemyCar.HEIGHT - 10, EnemyCar.WIDTH, EnemyCar.HEIGHT);
            ImageDrawer carPlayerDrawer = new ImageDrawer("resources/white_car.png");
            AnimationDrawer carPlayerShootDrawer = new AnimationDrawer(new String[]{"resources/white_car.png", "resources/white_car_shoot.png"});
            AnimationDrawer carPlayerShieldDrawer = new AnimationDrawer(new String[]{"resources/shield/white_car.png", "resources/shield/blue_car.png", "resources/shield/green_car.png", "resources/shield/pink_car.png"});
            carPlayerController = new CarPlayerController(carPlayer, new CarPlayerDrawer(carPlayerDrawer, carPlayerShieldDrawer, carPlayerShootDrawer ));
        }
        return carPlayerController;
    }

    public static void setNull() {
        carPlayerController = null;
    }

    @Override
    public void run() {
        if (this.gameObject.isAlive()) {
            if(!PlayGameScene.pause) count++;
            Rectangle rectangle=this.gameObject.getNextRect(this.gameVector);
            if(GameConfig.getInst().durationInSeconds(count) >= TIME_SHOOT && this.ableToShoot) {
                count = 0;
                this.ableToShoot = false;
                this.shield = false;
                ((CarPlayer)this.gameObject).setCarPlayerStatus(CarPlayerStatus.NORMAL);
            }
            if(GameConfig.getInst().isInScreen(rectangle)) {
                super.run();
                this.bulletControllerManager.run();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        if (this.gameObject.isAlive()) {
            super.paint(g);
            this.bulletControllerManager.paint(g);
        }
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof EnemyCarController || c instanceof StoneController) {
            GameUtils.playSound("resources/die_sound.wav", false);
            if(!this.shield) {
                ((CarPlayer) gameObject).decreaseHP();
            }
            if(((CarPlayer)gameObject).getHp() <= 0) {
                this.gameObject.setAlive(false);
            }
        } else if(c instanceof CoinController) {
            GameUtils.playSound("resources/get_score_sound.wav", false);
            if(((Coin)c.getGameObject()).getCoinType() == CoinType.RED) EnemyCarController.increaseSpeed(-1);
        } else if(c instanceof GiftController) {
            count = 0;
            switch (((Gift)c.getGameObject()).getGiftType()) {
                case SHOOT:
                    this.ableToShoot = true;
                    ((CarPlayer)this.gameObject).setCarPlayerStatus(CarPlayerStatus.SHOOT);
                    break;
                case SHIELD:
                    this.shield = true;
                    ((CarPlayer)this.gameObject).setCarPlayerStatus(CarPlayerStatus.SHIELD);
                    break;
                case HEART:
                    if(((GameObjectWithHP)this.getGameObject()).getHp() < CarPlayer.HP_MAX) {
                        ((GameObjectWithHP) this.getGameObject()).increaseHP();
                    }
                    break;
            }
            this.ableToShoot = true;
        }
    }
}
