package graviwave.GameObjects;

import graviwave.GameDesign.Base;

class Shot {

    private Bullet bullet;

    Shot(double xPos, double yPos, double angle, int index, Base base){
        this.bullet = new Bullet(xPos, yPos, angle, index, base);
    }

    Bullet getBullet() {
        return bullet;
    }
}

