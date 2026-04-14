import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixByExecutor {
    public static void run() throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> System.out.println("Task A виконано послідовно"));
        executor.submit(() -> System.out.println("Task B виконано послідовно"));
        executor.submit(() -> System.out.println("Task C виконано послідовно"));

        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.SECONDS);

        System.out.println("✅ Завершено без дедлоку (один виконавець, послідовні задачі).");
    }
}