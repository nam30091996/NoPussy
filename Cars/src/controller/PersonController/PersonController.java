package controller.PersonController;

import controller.BulletControllers.BulletController;
import controller.CarPlayerControllers.CarPlayerController;
import controller.Colliable;
import controller.CollisionPool;
import controller.EnemyCarControllers.EnemyCarController;
import controller.EnemyCarControllers.LanePosition;
import controller.SingleController;
import gamescenes.Level3GameScene;
import model.GameConfig;
import model.GameObject;
import model.GameVector;
import model.Person;
import view.GameDrawer;
import view.ImageDrawer;

import java.awt.*;

/**
 * Created by MyComputer on 5/21/2016.
 */
public class PersonController extends SingleController implements Colliable {
    public PersonController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dx = 2;
        CollisionPool.getInst().add(this);
    }

    public PersonController(GameObject gameObject, GameDrawer gameDrawer, GameVector gameVector) {
        super(gameObject, gameDrawer, gameVector);
    }

    public static PersonController create() {
        Person person = new Person(-Person.DEFAULT_X, Person.DEFAULT_Y, Person.WIDTH, Person.HEIGHT);
        ImageDrawer imageDrawer = new ImageDrawer("resources/dung.png");
        PersonController personController = new PersonController(person, imageDrawer);
        return personController;
    }

    @Override
    public void run() {
        if (!Level3GameScene.pause) {
            super.run();
            this.gameVector.dx = 2;
        }
        int x = this.gameObject.getX();
        if (x >= 0 && x < GameConfig.DEFAULT_SCREEN_WIDTH / 4) ((Person) gameObject).setLane(LanePosition.LANE1);
        else if (x >= GameConfig.DEFAULT_SCREEN_WIDTH / 4 && x < GameConfig.DEFAULT_SCREEN_WIDTH / 2)
            ((Person) gameObject).setLane(LanePosition.LANE2);
        else if (x >= GameConfig.DEFAULT_SCREEN_WIDTH / 2 && x < GameConfig.DEFAULT_SCREEN_WIDTH / 4 * 3)
            ((Person) gameObject).setLane(LanePosition.LANE3);
        else ((Person) gameObject).setLane(LanePosition.LANE4);
        if (this.gameObject.getX() > GameConfig.DEFAULT_SCREEN_WIDTH) this.gameObject.setAlive(false);
    }

    @Override
    public void paint(Graphics g) {
        if (this.getGameObject().isAlive()) {
            super.paint(g);
        }
    }

    @Override
    public void onCollide(Colliable c) {
        if ((c instanceof CarPlayerController && !CarPlayerController.isFly()) || c instanceof BulletController) {
            this.getGameObject().setAlive(false);
        } else if (c instanceof EnemyCarController) {
            this.gameVector.dx = 0;
        }
    }
}
