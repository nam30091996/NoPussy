package gamescenes;

import controller.*;
import controller.CarPlayerControllers.CarPlayerController;
import controller.CarPlayerControllers.CarPlayerDirection;
import controller.CarPlayerControllers.CarPlayerHPControllerManager;
import controller.CoinControllers.CoinControllerManager;
import controller.EnemyCarControllers.EnemyCarController;
import controller.EnemyCarControllers.EnemyCarControllerManager;
import controller.GiftControllers.GiftControllerManager;
import controller.PointControllers.GamePointControllerManager;
import controller.StoneControllers.StoneControllerManager;
import model.CarPlayer;
import model.GameConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by qhuydtvt on 5/13/2016.
 */
public class PlayGameScene extends GameScene {

    private CarPlayerController carPlayerController;
    private Image backgroundImage;
    private GameConfig gameConfig;
    private Vector<Controller> controllerVect;
    private int count = 0;
    public static boolean pause = false;

    public PlayGameScene() {


        this.gameConfig = GameConfig.getInst();

        controllerVect = new Vector<Controller>();
        controllerVect.add(CarPlayerController.getCarPlayerController());
        controllerVect.add(CoinControllerManager.getInst());
        controllerVect.add(StoneControllerManager.getInst());
        controllerVect.add(EnemyCarControllerManager.getInst());
        controllerVect.add(GamePointControllerManager.getInst());
        controllerVect.add(CarPlayerHPControllerManager.getInst());
        controllerVect.add(GiftControllerManager.getInst());



        this.carPlayerController = CarPlayerController.getCarPlayerController();

        try {
            this.backgroundImage = ImageIO.read(new File("resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        CarPlayerHPControllerManager.getInst().updateHP(((CarPlayer) carPlayerController.getGameObject()).getHp());
        if(!carPlayerController.getGameObject().isAlive()) {
            reset();
            changeGameScene(GameSceneType.GAMEOVER);
        }

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, gameConfig.getScreenWidth(), gameConfig.getScreenHeight(), null);
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
//                for (Controller controller : controllerVect) {
//                    if(controller instanceof SingleController) {
//                        ((SingleController) controller).setPause(true);
//                    }
//                    else {
//                        ((ControllerManager) controller).setPause(true);
//                    }
//                }
                break;
            case KeyEvent.VK_R:
                this.pause = false;
//                for (Controller controller : controllerVect) {
//                    if(controller instanceof SingleController) {
//                        ((SingleController) controller).setPause(false);
//                    }
//                    else {
//                        ((ControllerManager) controller).setPause(false);
//                    }
//                }
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

    public void reset() {
        CoinControllerManager.setNull();
        EnemyCarControllerManager.setNull();
        StoneControllerManager.setNull();
        CarPlayerController.setNull();
        GiftControllerManager.setNull();
        EnemyCarController.setSpeed(EnemyCarController.SPEED);
        CollisionPool.getInst().reset();
        this.carPlayerController = CarPlayerController.getCarPlayerController();
    }
}
