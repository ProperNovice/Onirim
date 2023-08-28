package utils;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;

public enum Sleep {

	INSTANCE;

	private Object lockObject = new Object();
	private Sleeping sleeping = new Sleeping();
	private long millisToSleep, sleepStart;

	private Sleep() {

	}

	public void sleep(long millis) {

		this.millisToSleep = millis;

		Logger.INSTANCE.log("sleeping for " + millis + " millis");

		this.sleepStart = System.currentTimeMillis();
		this.sleeping.start();
		CursorFX.INSTANCE.setWait();

		Platform.enterNestedEventLoop(this.lockObject);

	}

	private void awake() {

		this.sleeping.stop();
		CursorFX.INSTANCE.setDefault();

		Logger.INSTANCE.logNewLine("awake");

		Platform.exitNestedEventLoop(this.lockObject, null);

	}

	private void handleTimePassed() {

		long timePassed = System.currentTimeMillis() - this.sleepStart;

		if (timePassed < this.millisToSleep)
			return;

		awake();

	}

	private class Sleeping extends AnimationTimer {

		@Override
		public void handle(long timer) {
			handleTimePassed();
		}

	}

}
