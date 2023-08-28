package utils;

import javafx.animation.AnimationTimer;
import utils.Interfaces.IUpdateAble;

public enum AnimationTimerFX {

	INSTANCE;

	private ArrayList<IUpdateAble> updateNextFrame = new ArrayList<>();
	private ArrayList<IUpdateAble> updateEachFrame = new ArrayList<>();
	private ArrayList<UpdateInterval> updateInterval = new ArrayList<>();
	private Timer timer = new Timer();

	private AnimationTimerFX() {

	}

	public void updateNextFrame(IUpdateAble updateAble) {

		if (this.updateNextFrame.contains(updateAble))
			return;

		this.updateNextFrame.addLast(updateAble);
		this.timer.start();

	}

	public void updateEachFrame(IUpdateAble updateAble) {

		if (this.updateEachFrame.contains(updateAble))
			return;

		this.updateEachFrame.addLast(updateAble);
		this.timer.start();

	}

	public void updateInterval(long interval, IUpdateAble updateAble) {

		this.updateInterval.addLast(new UpdateInterval(updateAble, interval));
		this.timer.start();

	}

	public void remove(IUpdateAble updateAble) {

		if (this.updateEachFrame.contains(updateAble))
			this.updateEachFrame.remove(updateAble);

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

		// check if timer stops

		int updateSize = 0;

		updateSize += this.updateNextFrame.size();
		updateSize += this.updateEachFrame.size();
		updateSize += this.updateInterval.size();

		if (updateSize > 0)
			return;

		this.timer.stop();

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
