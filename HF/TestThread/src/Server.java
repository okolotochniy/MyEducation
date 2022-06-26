public class Server extends Thread {
    private static Server instance;

    public static Server getInstance() {
        if (instance == null){
            instance = new Server();
            System.out.println("Сервер запущен");
        }else {
            System.out.println("Объект уже создан, создать можно только один");
        }
        return instance;
    }

    private int count = 0;
    @Override
    public void run() {
        while (!Thread.interrupted()){
            count++;
            System.out.println(Thread.currentThread().getName() + " " + "Значение: " + count);

            try {
                sleep(1000);
            }catch (InterruptedException ie){
                System.out.println("Исключение");
                return;}
        }
        System.out.println("Сервер всё");

    }
}
