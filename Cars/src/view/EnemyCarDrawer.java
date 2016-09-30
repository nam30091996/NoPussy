package view;

import model.GameObject;
import model.GameObjectWithHP;
import model.LifeState;

import java.awt.*;

/**
 * Created by 1918 on 16-May-16.
 */
public class EnemyCarDrawer implements GameDrawer {

    private GameDrawer aliveGameDrawer;
    private GameDrawer dyingGameDrawer;

    public EnemyCarDrawer(GameDrawer aliveGameDrawer, GameDrawer dyingGameDrawer) {
        this.aliveGameDrawer = aliveGameDrawer;
        this.dyingGameDrawer = dyingGameDrawer;
    }

    @Override
    public void paint(GameObject gameObject, Graphics g) {
        GameObjectWithHP gameObjectWithHP = (GameObjectWithHP) gameObject;
        switch ((gameObjectWithHP.getLifeState())) {
            case ALIVE:
                aliveGameDrawer.paint(gameObject, g);
                break;
            case DYING:
                if (dyingGameDrawer == null) {
                    gameObjectWithHP.setLifeState(LifeState.DEAD);
                } else {
                    dyingGameDrawer.paint(gameObject, g);
                }
                break;
        }
    }
}