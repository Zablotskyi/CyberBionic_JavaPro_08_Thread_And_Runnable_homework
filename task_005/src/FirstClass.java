public class FirstClass {
    private final Object firstLock = new Object();

    public Object getFirstLock() {
        return firstLock;
    }

    public void firstAction(SecondClass secondClass) {
        synchronized (firstLock) {
            System.out.println(Thread.currentThread().getName() + ": FirstClass тримає firstLock");
            try { Thread.sleep(100); } catch (InterruptedException ignored) {}

            System.out.println(Thread.currentThread().getName() + ": FirstClass намагається взяти secondLock...");
            synchronized (secondClass.getSecondLock()) {
                System.out.println("Цей рядок при дедлоці не має з’явитися");
            }
        }
    }
}
