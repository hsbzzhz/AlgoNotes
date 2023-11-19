package Demo;

public class Tsynchronized implements Runnable{
    /**
     * synchronized 修饰实例方法，实例方法是属于类的实例。synchronized 修饰的实例方法相当于是对象锁。
     */
    private static volatile int i = 0;

    public static synchronized void increase() {
        i++;
        System.out.println(Thread.currentThread().getName());
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Tsynchronized tSynchronized = new Tsynchronized();
        Thread aThread = new Thread(tSynchronized);
        Thread bThread = new Thread(tSynchronized);
        aThread.start();
        bThread.start();
        aThread.join();
        bThread.join();
        System.out.println("i = " + i);
    }
}
