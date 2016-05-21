package controller;

import controller.CarPlayerControllers.CarPlayerController;
import gamescenes.PlayGameScene;
import model.CarPlayer;
import model.GameObject;
import model.GameVector;
import model.PoliceCar;
import view.GameDrawer;
import view.ImageDrawer;

/**
 * Created by MyComputer on 5/21/2016.
 */
public class PoliceCarController extends SingleController implements Colliable {

    private static PoliceCarController policeCarController = null;
    public PoliceCarController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        CollisionPool.getInst().add(this);
    }

    public PoliceCarController(GameObject gameObject, GameDrawer gameDrawer, GameVector gameVector) {
        super(gameObject, gameDrawer, gameVector);
        CollisionPool.getInst().add(this);
    }

    @Override
    public void run() {
        if(!PlayGameScene.pause) {
            GameVector gameVector = CarPlayerController.getCarPlayerController().gameVector;
            GameObject gameObject = CarPlayerController.getCarPlayerController().gameObject;
            if(gameVector.dx > 0) this.gameVector.dx = 1;
            else if(gameVector.dx < 0) this.gameVector.dx = -1;
            else {
                if(gameObject.getX() > this.gameObject.getX()) this.gameVector.dx = 1;
                else if(gameObject.getX() < this.gameObject.getX()) this.gameVector.dx = -1;
            }
            if(gameVector.dy > 0) this.gameVector.dy = 1;
            else if(gameVector.dy < 0) this.gameVector.dy = -1;
            else {
                if(gameObject.getY() > this.gameObject.getY()) this.gameVector.dy = 1;
                else if(gameObject.getY() < this.gameObject.getY()) this.gameVector.dy = -1;
            }
            if(this.gameObject.getY() <= 670) this.gameVector.dy = 1;
            else if(this.gameObject.getY() >= 700) this.gameVector.dy = -1;
            super.run();
        }
    }

    @Override
    public void onCollide(Colliable c) {
        if(c instanceof CarPlayerController) {
            c.getGameObject().setAlive(false);
        }
    }

    public static PoliceCarController getPoliceCarController() {
        if(policeCarController == null) {
            policeCarController = new PoliceCarController(
                    new PoliceCar(CarPlayerController.getCarPlayerController().gameObject.getX(),700,60,60),
                    new ImageDrawer("resources/police_car.png"));
        }
        return policeCarController;
    }

    //public static setNull
}
