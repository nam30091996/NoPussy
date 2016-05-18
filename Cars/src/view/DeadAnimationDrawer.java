package view;

import model.GameObject;

/**
 * Created by 1918 on 16-May-16.
 */
public class DeadAnimationDrawer extends AnimationDrawer {

    public DeadAnimationDrawer(String[] imageUrls) {
        super(imageUrls);
    }

    protected void onEndImageVector(GameObject gameObject) {
        gameObject.setAlive(false);
    }
}
