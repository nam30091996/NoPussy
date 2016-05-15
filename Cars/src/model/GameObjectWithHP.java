package model;

/**
 * Created by MyComputer on 5/15/2016.
 */
public class GameObjectWithHP extends GameObject {
    private int hp;
    public GameObjectWithHP(int x, int y, int width, int height, int hp) {
        super(x, y, width, height);
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void increaseHP() {
        this.increaseHP(1);
    }

    public void increaseHP(int delta) {
        this.hp += delta;
    }

    public void decreaseHP(int delta) {
        this.hp -= delta;
    }

    public void decreaseHP() {
        this.decreaseHP(1);
    }
}
