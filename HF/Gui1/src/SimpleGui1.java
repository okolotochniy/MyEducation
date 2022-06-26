import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

public class SimpleGui1 implements ActionListener {
    static JButton button;




    public static void main(String[] args) {
        SimpleGui1 gui = new SimpleGui1();
        gui.go();
        System.out.println(button.getText());


    }

    public void go() {
        JFrame frame = new JFrame();
        button = new JButton("Нажми меня");
        button.addActionListener(this);
        frame.getContentPane().add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setVisible(true);
        System.out.println(button.getText());


    }


    public void actionPerformed(ActionEvent event) {
        button.setText("Меня нажали");
        if (button.getText().equals("Меня нажали")) {
            System.out.println(button.getText());
            go2();
        }

    }
    public void go2() {
        try {
                TimeUnit.SECONDS.sleep(3);
                button.setText("Нажми ещё");
        } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
}