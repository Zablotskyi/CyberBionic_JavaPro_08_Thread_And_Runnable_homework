//Завдання 2
//Затримка потоку. Необхідно створити три потоки, кожних із цих потоків запустити (наприклад: main, second, first),
//і коли ці потоки успішно відпрацюють – вивести на екран повідомлення (завершення потім first, second і main).
public class Main {
    public static void main(String[] args) {
        Thread firstThread = new Thread();
        Thread secondThread = new Thread();

        firstThread.start();
        secondThread.start();
        try {
            firstThread.join();
            System.out.println("Завершення потоку first");
        } catch (InterruptedException e) {
            throw new RuntimeException("Потік firstThread перервано");
        }
        try {
            secondThread.join();
            System.out.println("Завершення потоку second");
        } catch (InterruptedException e) {
            throw new RuntimeException("Потік secondThread перервано");
        }
        System.out.println("Завершення потоку main");


    }
}
