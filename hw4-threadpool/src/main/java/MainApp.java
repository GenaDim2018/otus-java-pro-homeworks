public class MainApp {
    public static void main(String[] args) {
        CustomThreadPool pool = new CustomThreadPool(4);
        for (int i = 0; i < 10; i++) {
            try {
                int finalI = i;
                pool.execute(() -> {
                    System.out.println(finalI + " " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {

                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
        try {
            pool.execute(() -> {
                try {
                    pool.execute(() -> {
                        System.out.println("pool is shutdown" + Thread.currentThread().getName());
                    });
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
