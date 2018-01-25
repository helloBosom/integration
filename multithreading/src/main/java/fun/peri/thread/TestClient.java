package fun.peri.thread;

public class TestClient {
    public static void main(String[] args) {
        Storage storage = new Storage();
        ProduceThread produce = new ProduceThread(storage);
        ConsumeThread consume = new ConsumeThread(storage);
        new Thread(produce).start();
        new Thread(consume).start();
    }
}
