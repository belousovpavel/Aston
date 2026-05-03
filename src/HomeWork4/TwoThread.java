package HomeWork4;

public class TwoThread {
    private static final Object LOCK = new Object();
    private static volatile boolean isPrintOne = true;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (LOCK) {
                    while (!isPrintOne) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("1 ");
                    isPrintOne = false;
                    LOCK.notify();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (LOCK) {
                    while (isPrintOne) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("2 ");
                    isPrintOne = true;
                    LOCK.notify();
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
