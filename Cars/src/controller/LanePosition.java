package controller;

/**
 * Created by tqdu on 5/12/2016.
 */
public enum LanePosition {
    LANE1,
    LANE2,
    LANE3,
    LANE4;

    public static LanePosition getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
