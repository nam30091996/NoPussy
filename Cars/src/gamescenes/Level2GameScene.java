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
public class Level2GameScene extends GameScene {

    private CarPlayerController carPlayerController;
    private Image backgroundImage;
    private GameConfig gameConfig;
    private Vector<Controller> controllerVector;
    public static boolean pause = false;

    public Level2GameScene() {

        this.gameConfig = GameConfig.getInst();
        controllerVector = new Vector<Controller>();


        controllerVector.add(CoinControllerManager.getInst());
        controllerVector.add(StoneControllerManager.getInst());
        controllerVector.add(EnemyCarControllerManager.getInst());
        controllerVector.add(GamePointControllerManager.getInst());
        controllerVector.add(CarPlayerHPControllerManager.getInst());
        controllerVector.add(GiftControllerManager.getInst());
        controllerVector.add(PersonControllerManager.getInst());
        controllerVector.add(BatteryController.getInst());
        controllerVector.add(CarPlayerController.getCarPlayerController());

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
        for(Controller controller : controllerVector) {
            try {
                controller.run();
            } catch (NullPointerException ex) {

            }
        }

        GamePointControllerManager.getInst().updatePoint(CarPlayer.getPoint());
        if(CarPlayer.getPoint() >= 100) {
            reset();
            changeGameScene(GameSceneType.LEVEL_3);
        }
        CarPlayerHPControllerManager.getInst().updateHP(((CarPlayer) carPlayerController.getGameObject()).getHp());
        if(!carPlayerController.getGameObject().isAlive()) {
            resetAll();
            changeGameScene(GameSceneType.GAME_OVER);
        }

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backgroundImage, 0, 20, gameConfig.getScreenWidth(), gameConfig.getScreenHeight(), null);
        for (Controller controller : controllerVector) {
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
                if(!Level3GameScene.pause) carPlayerController.shoot();
                break;
            case KeyEvent.VK_P:
                pause = true;
                break;
            case KeyEvent.VK_R:
                pause = false;
                break;
            case KeyEvent.VK_U:
                reset();
                changeGameScene(GameSceneType.LEVEL_3);
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
        GiftControllerManager.setNull();
        PersonControllerManager.setNull();
        CollisionPool.getInst().reset();
        this.carPlayerController = CarPlayerController.getCarPlayerController();
    }

    public void resetAll() {
        CoinControllerManager.setNull();
        EnemyCarControllerManager.setNull();
        StoneControllerManager.setNull();
        CarPlayerController.setNull();
        GiftControllerManager.setNull();
        PersonControllerManager.setNull();
        PikachuControllerManager.setNull();
        PoliceCarController.setNull();
        EnemyCarController.setSpeed(EnemyCarController.SPEED);
        CollisionPool.getInst().resetAll();
        this.carPlayerController = CarPlayerController.getCarPlayerController();
    }
}
