package HomeWork4;

public class LiveLock {
    static class Person {
        private String name;
        private boolean isPolite = true;

        public Person(String name) {
            this.name = name;
        }

        public void stepAside(Person other) {
            System.out.println(name + " уступает дорогу " + other.name);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void tryToPass(Person other) {
            while (isPolite) {
                System.out.println(name + ": пытается пройти, но видит " + other.name);
                stepAside(other);
                isPolite = !isPolite;
            }
        }
    }

    public static void main(String[] args) {
        Person person1 = new Person("Pasha");
        Person person2 = new Person("Ivan");

        Thread thread1 = new Thread(() -> person1.tryToPass(person2));
        Thread thread2 = new Thread(() -> person2.tryToPass(person1));

        thread1.start();
        thread2.start();
    }
}