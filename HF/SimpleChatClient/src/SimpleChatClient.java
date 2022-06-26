import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class SimpleChatClient {

    JPanel panel;
    JTextArea textIn;
    JTextArea textOut;
    BufferedReader reader;
    PrintWriter writer;

    public static void main(String[] args) {
        SimpleChatClient chatClient = new SimpleChatClient();
        chatClient.goGui();
    }

    public void goGui() {
        JFrame frame = new JFrame();
        panel = new JPanel();
        textIn = new JTextArea(20,50);
        textIn.setLineWrap(true);
        textIn.setEditable(false);
        textOut = new JTextArea(5, 50);
        textOut.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textIn);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);
        JScrollPane scrollPaneAria = new JScrollPane(textOut);
        scrollPaneAria.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneAria.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPaneAria);

        JButton button = new JButton("Отправить");
        button.addActionListener(new SendListener());

        frame.add(BorderLayout.CENTER, panel);
        frame.add(BorderLayout.SOUTH, button);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setVisible(true);

        setUpConnect();

        Thread thread = new Thread(new IncomingReader());
        thread.start();
    }

    public class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    textIn.append(message + "\n");
                }
            } catch (Exception e) {e.printStackTrace();}
        }
    }
    public void setUpConnect() {
        try {
            Socket socket = new Socket("127.0.0.1", 5000);
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(inputStreamReader);
            writer = new PrintWriter(socket.getOutputStream());
        }catch (IOException ex) {ex.printStackTrace();}
    }

    public class SendListener implements ActionListener{
    public void actionPerformed(ActionEvent event) {
        try {
            writer.println(textOut.getText());
            writer.flush();
        } catch (Exception ex) {
            textIn.setText("Сообщение не отправлено" + "\n");
            ex.printStackTrace();}
        textOut.setText("");
        textOut.requestFocus();
        }
    }

}