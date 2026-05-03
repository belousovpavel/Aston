package HomeWork4;

public class DeadLock {
    private static final Object RESOURCE_1 = new Object();
    private static final Object RESOURCE_2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (RESOURCE_1) {
                System.out.println("Thread 1: locked RESOURCE_1");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (RESOURCE_2) {
                    System.out.println("Thread 1: locked RESOURCE_2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (RESOURCE_2) {
                System.out.println("Thread 2: locked RESOURCE_2");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (RESOURCE_1) {
                    System.out.println("Thread 2: locked RESOURCE_1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
