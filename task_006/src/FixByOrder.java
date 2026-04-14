public class FixByOrder {
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void run() throws InterruptedException {
        Thread t1 = new Thread(() -> safeAction("T1", lockA, lockB));
        Thread t2 = new Thread(() -> safeAction("T2", lockB, lockA));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("✅ Завершено без дедлоку (однаковий порядок lock-ів).");
    }

    private static void safeAction(String name, Object x, Object y) {
        // Визначаємо єдиний порядок для всіх потоків
        Object first = x;
        Object second = y;
        if (System.identityHashCode(first) > System.identityHashCode(second)) {
            Object tmp = first; first = second; second = tmp;
        }

        synchronized (first) {
            System.out.println(name + ": взяв first lock");
            sleep(50);
            synchronized (second) {
                System.out.println(name + ": взяв second lock");
            }
        }
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}