package utils;

public class Semaphore {

	private java.util.concurrent.Semaphore semaphore = null;

	public Semaphore(int permits) {
		this.semaphore = new java.util.concurrent.Semaphore(permits);
	}

	public void releasePermit() {
		this.semaphore.release();
	}

	public void acquirePermit() {

		try {
			this.semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void acquirePermits(int permits) {

		for (int counter = 1; counter <= permits; counter++)
			acquirePermit();

	}

	public int availablePermits() {
		return this.semaphore.availablePermits();
	}

}
