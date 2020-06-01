package graviwave;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

class MyPanel extends JPanel implements MouseListener, MouseMotionListener {
    private JLabel[][] labels;
    private ArrayList<Change> components = new ArrayList<>(2);

    MyPanel(JLabel[][] labels) {
        this.labels = labels;
        this.setLayout(new FlowLayout());
        addMouseMotionListener(this);
        addMouseListener(this);
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                labels[i][j] =  new JLabel();
                add(labels[i][j]);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getX() > 0 && e.getX() < getWidth()/3){
            if(e.getY() > 0 && e.getY() < this.getHeight()/3){
                components.add(new Change(labels[0][0], 0, 0)); }
            else if(e.getY() > this.getHeight()/3 && e.getY() < 2*this.getHeight()/3){
                components.add(new Change(labels[1][0], 1, 0)); }
            else if(e.getY() > 2*this.getHeight()/3 && e.getY() < this.getHeight()){
                components.add(new Change(labels[2][0], 2, 0)); }
        }
        else if(e.getX()< 2*this.getWidth()/3 && e.getX()>this.getWidth()/3){
            if(e.getY() > 0 && e.getY() < this.getHeight()/3){
                components.add(new Change(labels[0][1], 0, 1)); }
            else if(e.getY() > this.getHeight()/3 && e.getY() < 2*this.getHeight()/3){
                components.add(new Change(labels[1][1], 1, 1)); }
            else if(e.getY() > 2*this.getHeight()/3 && e.getY() < this.getHeight()){
                components.add(new Change(labels[2][1], 2, 1)); }
        }
        else if(e.getX()> 2*this.getWidth()/3 && e.getX()<this.getWidth()){
            if(e.getY() > 0 && e.getY() < getHeight()/3){
                components.add(new Change(labels[0][2], 0, 2)); }
            else if(e.getY() > this.getHeight()/3 && e.getY() < 2*this.getHeight()/3){
                components.add(new Change(labels[1][2], 1, 2)); }
            else if(e.getY() > 2*this.getHeight()/3 && e.getY() < this.getHeight()){
                components.add(new Change(labels[2][2], 2, 2)); }
        }

        if(components.size()==2){
            int iF = components.get(0).getI();
            int iS = components.get(1).getI();
            int jF = components.get(0).getJ();
            int jS = components.get(1).getJ();
            Icon temp0 =components.get(0).getComponent().getIcon();
            Icon temp1 =components.get(1).getComponent().getIcon();
            labels[iF][jF].setIcon(temp1);
            labels[iS][jS].setIcon(temp0);
            components.clear();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
    @Override
    public void mouseDragged(MouseEvent e) { }
    @Override
    public void mouseMoved(MouseEvent e) { }
}
