package graviwave;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new Form();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(800, 800);
                    frame.setVisible(true);
                    frame.setBackground(Color.BLACK);
                }
            });
    }
}
