package utils;

import javafx.application.Platform;

public enum Lock {

	INSTANCE;

	private Object lockObject = new Object();
	private Runnable runnable = null;
	private boolean lock = false;

	private Lock() {

	}

	public void lock() {

		if (!Animation.INSTANCE.isAnimatingSynchronous()) {

			Logger.INSTANCE.logNewLine("locked without synch");
			return;

		}

		this.lock = true;
		Logger.INSTANCE.log("lock");
		Platform.enterNestedEventLoop(this.lockObject);

	}

	public void unlock() {

		if (!this.lock) {

			Logger.INSTANCE.logNewLine("unlocked without synch");
			return;

		}

		Logger.INSTANCE.logNewLine("unlock");

		this.lock = false;
		Platform.exitNestedEventLoop(this.lockObject, null);

		if (this.runnable == null)
			return;

		Logger.INSTANCE.logNewLine("executing runnable");

	}

}
