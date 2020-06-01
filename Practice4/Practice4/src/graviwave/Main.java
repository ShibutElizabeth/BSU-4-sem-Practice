package graviwave;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame("Clock");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        ClockPanel clockPanel = new ClockPanel(new Clock(-0.1));
        clockPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        f.add(clockPanel, BorderLayout.CENTER);

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                clockPanel.getClock().setSeconds(clockPanel.getClock().getSeconds() + 0.1);
                clockPanel.repaint();
            }
        }, 0, 100);
        f.pack();
        f.setVisible(true);
    }

}
class ClockPanel extends JPanel{
    private Clock clock;

    ClockPanel(Clock Clock) {
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(600, 400));
        setClock(Clock);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
            drawSecClock(g);

    }
    private void drawCircle(Graphics g, Point center) {
        g.setColor(Color.RED);
        g.fillOval(center.x - 8 / 2, center.y - 8 / 2, 8, 8);
    }
    private Point getEndPoint(double angle, int radius) {
        Point O = new Point(getSize().width / 2, getSize().height / 2);
        int x = (int) (O.x + radius * Math.cos(angle));
        int y = (int) (O.y - radius * Math.sin(angle));
        return new Point(x, y);
    }
    private void drawSecClock(Graphics g){
        Point O = new Point(getSize().width / 2, getSize().height / 2);
        int radiusClock = Math.min(O.x, O.y) - 20;
        int radiusSeconds = radiusClock - 10;
        double angle;
        for (int i = 1; i < 13; i++) {
            angle = Math.PI / 2 - i * Math.PI / 6;
            Point point = getEndPoint(angle, radiusClock);
            drawCircle(g, point);
        }
        angle = Math.PI / 2 - clock.getSeconds() * Math.PI / 30;
        Point point = getEndPoint(angle, radiusSeconds);
        g.setColor(Color.GRAY);
        g.drawLine(O.x, O.y, point.x, point.y);
    }
    Clock getClock() {
        return clock;
    }

    private void setClock(Clock clock) {
        this.clock = clock;
    }
}

