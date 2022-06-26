import javax.swing.*;
import java.awt.*;

public class Animation {
    int x = 70;
    int y = 70;

    public static void main(String[] args) {
        Animation gui = new Animation();
        gui.go();
    }
    public void go() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyDrawPanel1 drawPanel = new MyDrawPanel1();

        frame.getContentPane().add(drawPanel);
        frame.setSize(300, 300);
        frame.setVisible(true);



            for (int j = 0; j < 130; j++) {
                x++;
                y++;

                drawPanel.repaint();

                try {
                    Thread.sleep(30);
                } catch (Exception ex) {
                }

        }
    }
    class MyDrawPanel1 extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.green);
            g.fillOval(x, y, 40, 40);
        }
    }

}
