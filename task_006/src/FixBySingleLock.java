public class FixBySingleLock {
    private static final Object GLOBAL_LOCK = new Object();

    public static void run() throws InterruptedException {
        Thread t1 = new Thread(() -> action("T1"));
        Thread t2 = new Thread(() -> action("T2"));

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("✅ Завершено без дедлоку (один спільний lock).");
    }

    private static void action(String name) {
        synchronized (GLOBAL_LOCK) {
            System.out.println(name + ": працюю в критичній секції");
            sleep(80);
        }
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}