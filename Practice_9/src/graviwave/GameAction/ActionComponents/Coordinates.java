package graviwave.GameAction.ActionComponents;

import java.util.Random;

public class Coordinates {
    private int x, y;

    public Coordinates(){
        Random randX = new Random();
        this.x = randX.nextInt(1200);
        Random randY = new Random();
        this.y = randY.nextInt(300);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
