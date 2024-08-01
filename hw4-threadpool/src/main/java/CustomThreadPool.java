import java.util.ArrayList;
import java.util.List;

public class CustomThreadPool {
    private static boolean isRunning = true;
    private static BlockingQueue queue = new BlockingQueue();
    List<Thread> threads = new ArrayList<>();

    public CustomThreadPool(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            threads.add(new Thread(new TaskWorker()));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void execute(Runnable command) throws InterruptedException {
        if (isRunning) {
            queue.put(command);
        }
    }

    public void shutdown() {
        isRunning = false;
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private static final class TaskWorker implements Runnable {
        @Override
        public void run() {
            while (isRunning ||!queue.isEmpty()) {
                Runnable nextTask = null;
                try {
                    nextTask = (Runnable) queue.take();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                if (nextTask != null) {
                    nextTask.run();
                }
            }
        }
    }
}
