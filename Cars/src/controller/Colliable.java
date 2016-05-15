package controller;

import model.GameObject;

/**
 * Created by qhuydtvt on 5/6/2016.
 */
public interface Colliable {
    void onCollide(Colliable c);
    GameObject getGameObject();
}
