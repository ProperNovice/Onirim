package utils;

import javafx.animation.AnimationTimer;
import utils.Interfaces.IUpdateAble;

public enum AnimationTimerFX {

	INSTANCE;

	private ArrayList<IUpdateAble> updateNextFrame = new ArrayList<>();
	private ArrayList<IUpdateAble> updateEachFrame = new ArrayList<>();
	private ArrayList<UpdateInterval> updateInterval = new ArrayList<>();

	private AnimationTimerFX() {
		new Timer().start();
	}

	public void updateNextFrame(IUpdateAble updateAble) {

		if (this.updateNextFrame.contains(updateAble))
			return;

		this.updateNextFrame.addLast(updateAble);

	}

	public void updateEachFrame(IUpdateAble updateAble) {

		if (this.updateEachFrame.contains(updateAble))
			return;

		this.updateEachFrame.addLast(updateAble);

	}

	public void updateInterval(IUpdateAble updateAble, long interval) {
		this.updateInterval.addLast(new UpdateInterval(updateAble, interval));
	}

	public void remove(IUpdateAble updateAble) {

		for (UpdateInterval updateInterval : this.updateInterval.clone())
			if (updateInterval.getUpdateAble().equals(updateAble))
				this.updateInterval.remove(updateInterval);

	}

	private void update() {

		// update next frame

		for (IUpdateAble updateAble : this.updateNextFrame)
			updateAble.update();

		this.updateNextFrame.clear();

		// update each frame

		for (IUpdateAble updateAble : this.updateEachFrame)
			updateAble.update();

		// update interval

		for (UpdateInterval updateInterval : this.updateInterval)
			updateInterval.update();

	}

	private class Timer extends AnimationTimer {

		@Override
		public void handle(long now) {
			update();
		}

	}

	private class UpdateInterval implements IUpdateAble {

		private IUpdateAble updateAble = null;
		private long interval, timeMillis = System.currentTimeMillis();

		public UpdateInterval(IUpdateAble updateAble, long interval) {
			this.updateAble = updateAble;
			this.interval = interval;
		}

		@Override
		public void update() {

			long currentTimeMillis = System.currentTimeMillis();

			if (currentTimeMillis - this.timeMillis < this.interval)
				return;

			this.timeMillis += this.interval;
			this.updateAble.update();

		}

		public IUpdateAble getUpdateAble() {
			return this.updateAble;
		}

	}

}