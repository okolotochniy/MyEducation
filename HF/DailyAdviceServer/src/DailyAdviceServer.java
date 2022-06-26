import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DailyAdviceServer {
    String[] adviceList ={"Ешьте меньшими порциями", " Вместо того, чтобы многократно зачёркивать ненужное вам слово, " +
            "лучше напишите поверх него одно или два других слова.", "Фотографируйте визитки людей на случай. " +
            "Если вы потеряете какую-нибудь визитку, её фото есть в телефоне.", "Некоторые новогодние игрушки " +
            "можно аккуратно и надёжно хранить в картонке для яиц.", " Если у вас в сумке есть грязная одежда, " +
            "чтобы вся сумка не завоняла, храните в пакете с грязной одеждой кусок ароматного мыла.","Наполнить ведро " +
            "воды из раковины можно с помощью совка с закруглённой рукояткой."};

    public void go() {
        try {
            ServerSocket serverSocket = new ServerSocket(4242);
            while (true) {
                Socket socket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }
        }catch (IOException ex) { ex.printStackTrace();}
    }
    private String getAdvice() {
        int random = (int) (Math.random() * adviceList.length);
        return adviceList[random];
    }

    public static void main(String[] args) {
        DailyAdviceServer server = new DailyAdviceServer();
        server.go();
    }
}
