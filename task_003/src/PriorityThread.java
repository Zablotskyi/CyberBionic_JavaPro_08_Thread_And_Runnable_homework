public class PriorityThread extends Thread {
    public PriorityThread(String name, int minPriority) {
        super(name);
        setPriority(getPriority());
    }

    @Override
    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println(getName() + " (priority = " + getPriority() + "); " + i);
            Thread.yield();
        }
    }
}
