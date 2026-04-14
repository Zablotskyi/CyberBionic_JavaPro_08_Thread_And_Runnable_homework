public class DeadlockDemo {
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void runDeadlockDemo() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("T1: тримаю lockA, намагаюсь взяти lockB...");
                sleep(100);
                synchronized (lockB) {
                    System.out.println("T1: взяв lockB (це майже ніколи не побачиш у дедлоці)");
                }
            }
        }, "T1");

        Thread t2 = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("T2: тримаю lockB, намагаюсь взяти lockA...");
                sleep(100);
                synchronized (lockA) {
                    System.out.println("T2: взяв lockA (це майже ніколи не побачиш у дедлоці)");
                }
            }
        }, "T2");

        t1.start();
        t2.start();

        // Чекаємо трохи, щоб побачити "зависання"
        t1.join(500);
        t2.join(500);

        if (t1.isAlive() && t2.isAlive()) {
            System.out.println("✅ Deadlock підтверджено: обидва потоки все ще живі та чекають один одного.");
        } else {
            System.out.println("⚠️ Не вийшло зловити дедлок цього разу (рідко, але можливо). Перезапусти.");
        }

        // НЕ намагаємось "лікувати" дедлок interrupt'ом — просто завершуємо демо.
        // (synchronized не реагує на interrupt під час очікування монітора)
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}