package graviwave;

import javax.swing.*;

class Change{
    private int i, j;
    private JLabel component;
    Change(JLabel component, int i, int j){
        this.component = component;
        this.i = i;
        this.j = j;
    }
    JLabel getComponent() {
        return component;
    }
    int getI() {
        return i;
    }
    int getJ() {
        return j;
    }
}
