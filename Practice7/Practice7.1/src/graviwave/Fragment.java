package graviwave;

import javax.swing.*;

class Fragment{
    private Icon icon;
    private int i, j;

    Fragment(JLabel label, int i, int j){
        this.icon = label.getIcon();
        this.i = i;
        this.j = j;
    }

    boolean getRightPlace(int i, int j, Icon icon){
        return this.i == i && this.j == j && this.icon == icon;
    }

}
