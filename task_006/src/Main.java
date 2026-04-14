//Завдання 6
//Змінити завдання №5. Усі можливі способи (які Ви знаєте) вирішити проблему взаємного блокування.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 0) DEMO: Deadlock (але з таймаутом, щоб не висіло назавжди) ===");
        DeadlockDemo.runDeadlockDemo();
        System.out.println();

        System.out.println("=== 1) FIX: Однаковий порядок захоплення локів ===");
        FixByOrder.run();
        System.out.println();

        System.out.println("=== 2) FIX: Один спільний lock замість двох ===");
        FixBySingleLock.run();
        System.out.println();

        System.out.println("=== 3) FIX: ReentrantLock.tryLock() з таймаутом ===");
        FixByTryLock.run();
        System.out.println();

        System.out.println("=== 4) FIX: SingleThreadExecutor (без ручних lock) ===");
        FixByExecutor.run();
        System.out.println("\nГотово.");
    }
}
