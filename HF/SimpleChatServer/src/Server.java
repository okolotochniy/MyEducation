import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Server extends Thread {
    ServerSocket serverSocket;

  //  private static Server instance;
/*
    public static Server getInstance() {
        if (instance == null){
            instance = new Server();
            System.out.println("Сервер запущен");
        }else {
            System.out.println("Объект уже создан, создать можно только один");
        }
        return instance;
    }

 */

    private ArrayList<PrintWriter> clientOutputStreams;
    private int nGate;
    public int getnGate() {
        return nGate;
    }
    public void setnGate(int nGate) {
        this.nGate = nGate;
    }

    public void run() {
        clientOutputStreams = new ArrayList<>();
        try {

            serverSocket = new ServerSocket(nGate);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);

                Thread thread = new Thread(new Server.ClientHandler(clientSocket));
                thread.start();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        System.out.println("Сервер остановлен");


    }

    public class ClientHandler implements Runnable {
        BufferedReader reader;
        Socket socket;

        public ClientHandler(Socket clientSocket) {
            try {
                socket = clientSocket;
                InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(isReader);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    tellEveryone(message);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void tellEveryone(String message) {
        Iterator<PrintWriter> it = clientOutputStreams.iterator();
        while (it.hasNext()) {
            try {
                PrintWriter writer = it.next();
                writer.println(message);
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void stopServer() throws IOException {
        serverSocket.close();
    }
}
