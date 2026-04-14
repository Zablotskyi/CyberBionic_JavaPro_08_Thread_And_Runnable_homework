import java.util.concurrent.atomic.AtomicInteger;

public class SecondClass {
    private final Object lock;
    private final AtomicInteger turn;

    public SecondClass(Object lock, AtomicInteger turn) {
        this.lock = lock;
        this.turn = turn;
    }

    public void print(int value) throws InterruptedException {
        synchronized (lock) {
            while (turn.get() != 2) {
                lock.wait();
            }
            System.out.println("Second class: " + value);
            turn.set(1);
            lock.notifyAll();
        }
    }
}
