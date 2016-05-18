package view;

import model.GameObject;

import java.awt.*;

/**
 * Created by qhuydtvt on 4/29/2016.
 */
public interface GameDrawer {
    void paint(GameObject gameObject, Graphics g);
}
