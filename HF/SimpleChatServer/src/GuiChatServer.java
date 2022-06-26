import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GuiChatServer {
    JButton startServerButton;
    JButton stopServerButton;
    JTextField setGate;
    JLabel labelSetup;
    JLabel labelGate;
    Server server;

    public void guiBuilder() {
        startServerButton = new JButton("Server start");
        startServerButton.addActionListener(new StartServerListener());
        stopServerButton = new JButton("Stop server");
        stopServerButton.addActionListener(new StopServerListener());
        setGate = new JTextField(10);
        setGate.setText(String.valueOf(5000));
        labelSetup = new JLabel("Установлен порт сервера: ");
        labelGate = new JLabel("Установи порт сервера:");
        JFrame frame = new JFrame("Server start");
        JPanel panel = new JPanel();
        panel.add(labelGate);
        panel.add(setGate);
        panel.add(startServerButton);
        panel.add(labelSetup);
        frame.add(panel);
        frame.add(BorderLayout.SOUTH, stopServerButton);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setVisible(true);
    }


    public class StartServerListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
           // server = Server.getInstance();
            server = new Server();
            server.setnGate(Integer.parseInt(setGate.getText()));
            server.start();
            labelSetup.setText("Установлен порт сервера: " + server.getnGate());
            System.out.println(server.isAlive());
            startServerButton.setEnabled(false);
        }
    }
    public class StopServerListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                server.stopServer();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            System.out.println(server.isAlive());
            startServerButton.setEnabled(true);
        }
    }

    public static void main(String[] args) {

        new GuiChatServer().guiBuilder();
    }


}
