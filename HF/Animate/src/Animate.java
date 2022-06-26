import javax.swing.*;
import java.awt.*;

import static java.awt.Frame.MAXIMIZED_BOTH;

public class Animate {
   int x = 1;
   int y = 1;

    public static void main(String[] args) {
        Animate gui = new Animate();
        gui.go();
    }
    public void go() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyDrawP drawP = new MyDrawP();
        frame.getContentPane().add(BorderLayout.CENTER, drawP);
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setExtendedState(MAXIMIZED_BOTH);
        //centreWindow(frame);


        for (int i = 0; i < 124; i++, x++, y++) {
            x++;
            drawP.repaint();
            try {
                Thread.sleep(50);
            }catch (Exception exception){}
        }




    }
   /* public void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    */



    class MyDrawP extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(x, y, 500, 250);
            g.setColor(Color.blue);
            g.fillRect(0, 0, 500-x*2, 250-y*2);
        }

    }
}
