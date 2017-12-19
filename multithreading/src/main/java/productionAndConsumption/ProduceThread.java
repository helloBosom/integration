package productionAndConsumption;

public class ProduceThread implements Runnable {
    private Storage storage;

    public void run() {
        for (int i = 1; i < 5; i++) {
            Apple apple = new Apple();
            apple.setAppleId(i);
            storage.push(apple);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public ProduceThread(Storage storage) {
        this.storage = storage;
    }

}
