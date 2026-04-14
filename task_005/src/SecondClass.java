public class SecondClass {
    private final Object second = new Object();
    FirstClass firstClass = new FirstClass();

    private final Object secondLock = new Object();

    public Object getSecondLock() {
        return secondLock;
    }

    public void secondAction(FirstClass firstClass) {
        synchronized (secondLock) {
            System.out.println(Thread.currentThread().getName() + ": SecondClass тримає secondLock");
            try { Thread.sleep(100); } catch (InterruptedException ignored) {}

            System.out.println(Thread.currentThread().getName() + ": SecondClass намагається взяти firstLock...");
            synchronized (firstClass.getFirstLock()) {
                System.out.println("Цей рядок при дедлоці не має з’явитися");
            }
        }
    }
}
