package controller;

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
    protected boolean pause = false;

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

    public GameObject getGameObject() {
        return gameObject;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    @Override
    public void run() {
        if(!this.pause) {
            gameObject.move(gameVector);
        }
    }

    @Override
    public void paint(Graphics g) {
        gameDrawer.paint(this.gameObject, g);
    }
}
