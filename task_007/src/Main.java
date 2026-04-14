public class Main {
    public static void main(String[] args) throws InterruptedException {

        Thread daemon = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(300);
                    System.out.println("[DAEMON] працюю...");
                } catch (InterruptedException e) {
                    System.out.println("[DAEMON] перервано");
                    return;
                }
            }
        }, "MyDaemonThread");

        daemon.setDaemon(true);
        daemon.setPriority(Thread.NORM_PRIORITY);

        printThreadInfo("До start()", daemon);

        daemon.start();

        Thread.sleep(700);

        printThreadInfo("Після start()", daemon);

        System.out.println("\nMain завершується. Daemon-потік теж завершиться автоматично.");
    }

    private static void printThreadInfo(String title, Thread t) {
        System.out.println("\n=== " + title + " ===");
        System.out.println("Name: " + t.getName());
        System.out.println("Id: " + t.getId());
        System.out.println("Priority: " + t.getPriority());
        System.out.println("State: " + t.getState());
        System.out.println("Is daemon: " + t.isDaemon());
        System.out.println("Is alive: " + t.isAlive());
        System.out.println("Is interrupted: " + t.isInterrupted());

        ThreadGroup group = t.getThreadGroup();
        System.out.println("ThreadGroup: " + (group != null ? group.getName() : "(null)"));

        ClassLoader cl = t.getContextClassLoader();
        System.out.println("ContextClassLoader: " + (cl != null ? cl.toString() : "(null)"));

        StackTraceElement[] stack = t.getStackTrace();
        System.out.println("StackTrace length: " + stack.length);
        for (int i = 0; i < Math.min(stack.length, 5); i++) {
            System.out.println("  at " + stack[i]);
        }
    }
}