import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class FixByTryLock {
    private static final ReentrantLock lockA = new ReentrantLock();
    private static final ReentrantLock lockB = new ReentrantLock();

    public static void run() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try { safeWork("T1", lockA, lockB); } catch (InterruptedException ignored) {}
        });

        Thread t2 = new Thread(() -> {
            try { safeWork("T2", lockB, lockA); } catch (InterruptedException ignored) {}
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("✅ Завершено без дедлоку (tryLock + відступ/повтор).");
    }

    private static void safeWork(String name, ReentrantLock first, ReentrantLock second) throws InterruptedException {
        while (true) {
            boolean gotFirst = first.tryLock(200, TimeUnit.MILLISECONDS);
            if (!gotFirst) continue;

            try {
                System.out.println(name + ": взяв перший lock");
                Thread.sleep(50);

                boolean gotSecond = second.tryLock(200, TimeUnit.MILLISECONDS);
                if (!gotSecond) {
                    System.out.println(name + ": не взяв другий lock -> відпускаю перший і пробую знову");
                    continue;
                }

                try {
                    System.out.println(name + ": взяв другий lock -> робота виконана");
                    break;
                } finally {
                    second.unlock();
                }

            } finally {
                first.unlock();
            }
        }
    }
}