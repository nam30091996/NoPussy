package view;

import model.CarPlayer;
import model.GameObject;

import java.awt.*;

/**
 * Created by MyComputer on 5/16/2016.
 */
public class CarPlayerDrawer implements GameDrawer{
    private GameDrawer normal;
    private GameDrawer shield;
    private GameDrawer shoot;

    public CarPlayerDrawer(GameDrawer normal, GameDrawer shield, GameDrawer shoot) {
        this.normal = normal;
        this.shield = shield;
        this.shoot = shoot;
    }


    @Override
    public void paint(GameObject gameObject, Graphics g) {
        CarPlayer carPlayer = (CarPlayer)gameObject;
        switch(carPlayer.getCarPlayerStatus()) {
            case NORMAL:
                normal.paint(gameObject, g);
                break;
            case SHIELD:
                shield.paint(gameObject, g);
                break;
            case SHOOT:
                shoot.paint(gameObject, g);
                break;
        }
    }
}
