package fun.peri.thread;

public class ConsumeThread implements Runnable {
	private Storage storage;

	public ConsumeThread() {
	}

	public ConsumeThread(Storage storage) {
		this.storage = storage;
	}

	public void run() {
		for (int i = 0; i <= 5; i++) {
			storage.pop();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
