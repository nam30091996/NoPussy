package model;

import controller.EnemyCarControllers.LanePosition;

/**
 * Created by MyComputer on 5/21/2016.
 */
public class Person extends GameObject {
    private LanePosition lane;

    public Person(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public LanePosition getLane() {
        return lane;
    }

    public void setLane(LanePosition lane) {
        this.lane = lane;
    }
}
