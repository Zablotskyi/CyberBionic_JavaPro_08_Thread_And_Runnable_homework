import java.util.concurrent.atomic.AtomicInteger;

public class FirstClass {
    private final Object lock;
    private final AtomicInteger turn;

    public FirstClass(Object lock, AtomicInteger turn) {
        this.lock = lock;
        this.turn = turn;
    }

    public void print(int value) throws InterruptedException {
        synchronized (lock) {
            while (turn.get() != 1) {
                lock.wait();
            }
            System.out.println("First class: " + value);
            turn.set(2);
            lock.notifyAll();
        }
    }
}
