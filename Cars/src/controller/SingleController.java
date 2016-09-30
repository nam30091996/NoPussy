package controller;

import gamescenes.Level3GameScene;
import gamescenes.Level1GameScene;
import gamescenes.Level2GameScene;
import model.GameObject;
import model.GameVector;
import view.GameDrawer;

import java.awt.*;

/**
 * Created by MyComputer on 5/11/2016.
 */
public class SingleController implements Controller {

    protected GameObject gameObject;
    protected GameDrawer gameDrawer;
    protected GameVector gameVector;

    public SingleController(GameObject gameObject, GameDrawer gameDrawer) {
        this.gameObject = gameObject;
        this.gameDrawer = gameDrawer;
        this.gameVector = new GameVector();
    }

    public SingleController(GameObject gameObject, GameDrawer gameDrawer, GameVector gameVector) {
        this.gameObject = gameObject;
        this.gameDrawer = gameDrawer;
        this.gameVector = gameVector;
    }

    public GameVector getGameVector() {
        return gameVector;
    }

    public void setGameVector(GameVector gameVector) {
        this.gameVector = gameVector;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    @Override
    public void run() {
        if (!Level3GameScene.pause && !Level1GameScene.pause && !Level2GameScene.pause) {
            gameObject.move(gameVector);
        }
    }

    @Override
    public void paint(Graphics g) {
        gameDrawer.paint(this.gameObject, g);
    }
}
