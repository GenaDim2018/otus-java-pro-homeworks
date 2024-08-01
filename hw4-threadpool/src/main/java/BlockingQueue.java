import java.util.LinkedList;
import java.util.List;

public class BlockingQueue {

    private List<Runnable> queue = new LinkedList<>();
    private int limit = 10;

    public BlockingQueue() {
    }

    public BlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }


    public synchronized void put(Runnable item) throws InterruptedException {
        while (this.queue.size() == this.limit) {
            wait();
        }
        if (this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add(item);
    }


    public synchronized Runnable take() throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();
        }
        if (this.queue.size() == this.limit) {
            notifyAll();
        }

        return this.queue.removeFirst();
    }
}