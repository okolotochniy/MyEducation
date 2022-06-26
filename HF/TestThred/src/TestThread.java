public class TestThread {
    static volatile int count = 100;

    public static void main(String[] args) {
        Thread myThread1 = new Thread(new DownCount());
        myThread1.start();

        Thread myThread2 = new Thread(new UpCount());
        myThread2.start();

    }
}
class DownCount implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i <100 ; i++) {
            System.out.println(Thread.currentThread().getName());
            System.out.println(TestThread.count);
            TestThread.count--;
            System.out.println(TestThread.count);

        }

    }
}
class UpCount implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i <100 ; i++) {
            System.out.println(Thread.currentThread().getName());
            System.out.println(TestThread.count);
            TestThread.count++;
            System.out.println(TestThread.count);

        }

    }
}