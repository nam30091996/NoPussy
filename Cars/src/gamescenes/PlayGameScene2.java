package gamescenes;

import controller.CarPlayerControllers.CarPlayerController;
import controller.CarPlayerControllers.CarPlayerDirection;
import controller.CoinControllers.CoinControllerManager;
import controller.CollisionPool;
import controller.Controller;
import controller.EnemyCarControllers.EnemyCarController;
import controller.EnemyCarControllers.EnemyCarControllerManager;
import controller.GiftControllers.GiftControllerManager;
import controller.PersonController.PersonControllerManager;
import controller.PersonController.PikachuControllerManager;
import controller.PointControllers.BatteryController;
import controller.PointControllers.CarPlayerHPControllerManager;
import controller.PointControllers.GamePointControllerManager;
import controller.PoliceCarController;
import controller.StoneControllers.StoneControllerManager;
import model.CarPlayer;
import model.GameConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by MyComputer on 5/21/2016.
 */
public class PlayGameScene2 extends GameScene {

    private CarPlayerController carPlayerController;
    private Image backgroundImage;
    private GameConfig gameConfig;
    private Vector<Controller> controllerVect;
    private int count = 0;
    public static boolean pause = false;

    public PlayGameScene2() {

        this.gameConfig = GameConfig.getInst();
        controllerVect = new Vector<Controller>();


        controllerVect.add(CoinControllerManager.getInst());
        controllerVect.add(StoneControllerManager.getInst());
        controllerVect.add(EnemyCarControllerManager.getInst());
        controllerVect.add(GamePointControllerManager.getInst());
        controllerVect.add(CarPlayerHPControllerManager.getInst());
        controllerVect.add(GiftControllerManager.getInst());
        controllerVect.add(PersonControllerManager.getInst());
//        controllerVect.add(PoliceCarController.getPoliceCarController());
//        controllerVect.add(PikachuControllerManager.getInst());
        controllerVect.add(BatteryController.getInst());
        controllerVect.add(CarPlayerController.getCarPlayerController());

        try {
            this.backgroundImage = ImageIO.read(new File("resources/background2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.carPlayerController = CarPlayerController.getCarPlayerController();
    }


    @Override
    public void run() {

        CollisionPool.getInst().run();
        for(Controller controller : controllerVect) {
            try {
                controller.run();
            } catch (NullPointerException ex) {

            }
        }

        GamePointControllerManager.getInst().updatePoint(((CarPlayer) carPlayerController.getGameObject()).getPoint());
        if(((CarPlayer) carPlayerController.getGameObject()).getPoint() >= 100) {
            reset();
            changeGameScene(GameSceneType.PLAY);
        }
        CarPlayerHPControllerManager.getInst().updateHP(((CarPlayer) carPlayerController.getGameObject()).getHp());
        if(!carPlayerController.getGameObject().isAlive()) {
            reset1();
            changeGameScene(GameSceneType.GAMEOVER);
        }

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backgroundImage, 0, 20, gameConfig.getScreenWidth(), gameConfig.getScreenHeight(), null);
        for (Controller controller : controllerVect) {
            try {
                controller.paint(g);
            } catch (Exception ex) {

            }
        }
    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        CarPlayerDirection carPlayerDirection = CarPlayerDirection.NONE;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                carPlayerDirection = CarPlayerDirection.UP;
                break;
            case KeyEvent.VK_DOWN:
                carPlayerDirection = CarPlayerDirection.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                carPlayerDirection = CarPlayerDirection.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                carPlayerDirection = CarPlayerDirection.RIGHT;
                break;
            case KeyEvent.VK_SPACE:
                if(!PlayGameScene.pause) carPlayerController.shoot();
                break;
            case KeyEvent.VK_P:
                this.pause = true;
                break;
            case KeyEvent.VK_R:
                this.pause = false;
                break;
            case KeyEvent.VK_U:
                reset();
                changeGameScene(GameSceneType.PLAY);
                break;
        }

        carPlayerController.move(carPlayerDirection);

    }

    @Override
    public void onKeyReleased(KeyEvent e) {
        CarPlayerDirection carPlayerDirection = CarPlayerDirection.NONE;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                carPlayerDirection = CarPlayerDirection.STOP_Y;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                carPlayerDirection = CarPlayerDirection.STOP_X;
                break;
        }
        carPlayerController.move(carPlayerDirection);
    }

    @Override
    public void onMouseClicked(MouseEvent e) {

    }

    public void reset() {
        CoinControllerManager.setNull();
        EnemyCarControllerManager.setNull();
        StoneControllerManager.setNull();
//        CarPlayerController.setNull();
        GiftControllerManager.setNull();
        PersonControllerManager.setNull();
//        PikachuControllerManager.setNull();
//        PoliceCarController.setNull();
//        EnemyCarController.setSpeed(EnemyCarController.SPEED);
        CollisionPool.getInst().reset1();
        this.carPlayerController = CarPlayerController.getCarPlayerController();
    }

    public void reset1() {
        CoinControllerManager.setNull();
        EnemyCarControllerManager.setNull();
        StoneControllerManager.setNull();
        CarPlayerController.setNull();
        GiftControllerManager.setNull();
        PersonControllerManager.setNull();
        PikachuControllerManager.setNull();
        PoliceCarController.setNull();
        EnemyCarController.setSpeed(EnemyCarController.SPEED);
        CollisionPool.getInst().reset();
        this.carPlayerController = CarPlayerController.getCarPlayerController();
    }
}
