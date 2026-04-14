import java.util.concurrent.atomic.AtomicInteger;

//Завдання 4
//Створити 2 довільні класи з полями та методами. Необхідно синхронізувати методи цих класів між собою, тобто щоб
//виводило на екран значення один за одним (1 клас, 2 клас, 1, 2 тощо).
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        AtomicInteger turn = new AtomicInteger(1);

        FirstClass firstClass = new FirstClass(lock, turn);
        SecondClass secondClass = new SecondClass(lock, turn);

        Thread firstThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    firstClass.print(i);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });

        Thread secondThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    secondClass.print(i);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });

        firstThread.start();
        secondThread.start();
        firstThread.join();
        secondThread.join();

        System.out.println("Ready");
    }
}
