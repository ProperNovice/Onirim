package utils;

import managers.Credentials;
import utils.Enums.AnimationSynchEnum;
import utils.Interfaces.IImageViewAble;
import utils.Interfaces.IUpdateAble;

public enum Animation implements IUpdateAble {

	INSTANCE;

	private HashMap<IImageViewAble, AnimationExecutionObject> animationsSynchronous = new HashMap<>();
	private HashMap<IImageViewAble, AnimationExecutionObject> animationsAsynchronous = new HashMap<>();

	private Animation() {

	}

	@Override
	public void update() {

		if (!this.animationsSynchronous.isEmpty())
			executeAnimationSynchronous();
		if (!this.animationsAsynchronous.isEmpty())
			executeAnimationAsynchronous();

		if (isAnimating())
			return;
		else
			AnimationTimerFX.INSTANCE.remove(this);

	}

	private void executeAnimationSynchronous() {

		executeAnimationList(this.animationsSynchronous);

		if (!this.animationsSynchronous.isEmpty())
			return;

		CursorFX.INSTANCE.setDefault();
		Lock.INSTANCE.unlock();

	}

	private void executeAnimationAsynchronous() {
		executeAnimationList(this.animationsAsynchronous);
	}

	private void executeAnimationList(
			HashMap<IImageViewAble, AnimationExecutionObject> animationsList) {

		for (IImageViewAble imageView : animationsList.clone()) {

			AnimationExecutionObject animationExecutionObject = animationsList.getValue(imageView);
			animationExecutionObject.moveTowardsAnimationCoordinates();

			if (!animationExecutionObject.animationIsFinished())
				continue;

			animationsList.removeKey(imageView);

		}

	}

	public void animateTopLeft(IImageViewAble imageViewAble, Vector2 vector2,
			AnimationSynchEnum animationSynchEnum) {

		if (this.animationsSynchronous.containsKey(imageViewAble))
			this.animationsSynchronous.removeKey(imageViewAble);
		else if (this.animationsAsynchronous.containsKey(imageViewAble))
			this.animationsAsynchronous.removeKey(imageViewAble);

		imageViewAble.getImageView().toFront();

		HashMap<IImageViewAble, AnimationExecutionObject> listToAdd = null;

		AnimationExecutionObject animationExecutionObject = new AnimationExecutionObject(
				imageViewAble.getImageView(), vector2);

		if (animationExecutionObject.animationIsFinished())
			return;

		switch (animationSynchEnum) {

		case SYNCHRONOUS:

			if (!isAnimatingSynchronous())
				CursorFX.INSTANCE.setWait();

			listToAdd = this.animationsSynchronous;
			break;

		case ASYNCHRONOUS:
			listToAdd = this.animationsAsynchronous;
			break;

		}

		listToAdd.put(imageViewAble, animationExecutionObject);

		AnimationTimerFX.INSTANCE.updateEachFrame(this);

		if (!ImageViewDependency.INSTANCE.containsDependency(imageViewAble))
			return;

		// dependency

		for (IImageViewAble dependency : ImageViewDependency.INSTANCE
				.getDependency(imageViewAble)) {

			Vector2 primaryTopLeft = imageViewAble.getImageView().getCoordinatesTopLeft();
			Vector2 secondaryTopLeft = dependency.getImageView().getCoordinatesTopLeft();

			double secondaryX = vector2.x + (secondaryTopLeft.x - primaryTopLeft.x);
			double secondaryY = vector2.y + (secondaryTopLeft.y - primaryTopLeft.y);

			Vector2 vector2Secondary = new Vector2(secondaryX, secondaryY);
			animateTopLeft(dependency, vector2Secondary, animationSynchEnum);

		}

	}

	public void animateCenter(IImageViewAble imageViewAble, Vector2 vector2,
			AnimationSynchEnum animationSynchEnum) {

		double x = vector2.x - imageViewAble.getImageView().getWidth() / 2;
		double y = vector2.y - imageViewAble.getImageView().getHeight() / 2;

		animateTopLeft(imageViewAble, new Vector2(x, y), animationSynchEnum);

	}

	public boolean isAnimatingSynchronous() {
		return !this.animationsSynchronous.isEmpty();
	}

	public boolean isAnimatingAsynchronous() {
		return !this.animationsAsynchronous.isEmpty();
	}

	public boolean isAnimating() {

		if (isAnimatingSynchronous())
			return true;

		if (isAnimatingAsynchronous())
			return true;

		return false;

	}

	public void moveAsynchronousToSynchronous() {

		for (IImageViewAble imageViewAble : this.animationsAsynchronous)
			this.animationsSynchronous.put(imageViewAble,
					this.animationsAsynchronous.getValue(imageViewAble));

		this.animationsAsynchronous.clear();

	}

	public void moveAsynchronousToSynchronousLock() {

		moveAsynchronousToSynchronous();
		Lock.INSTANCE.lock();

	}

	private class AnimationExecutionObject {

		private ImageView imageView = null;
		private Vector2 targetCoordinates = null, animationStep = null;

		public AnimationExecutionObject(ImageView imageView, Vector2 targetCoordinates) {

			this.imageView = imageView;
			this.targetCoordinates = targetCoordinates;
			setAnimationCredentials();

		}

		private void setAnimationCredentials() {

			// calculating animation steps

			this.animationStep = new Vector2(Credentials.INSTANCE.animationStep,
					Credentials.INSTANCE.animationStep);

			double distanceX = Math
					.abs(this.targetCoordinates.x - this.imageView.getCoordinatesTopLeftX());
			double distanceY = Math
					.abs(this.targetCoordinates.y - this.imageView.getCoordinatesTopLeftY());

			if (distanceX < distanceY)
				this.animationStep.x = distanceX * Credentials.INSTANCE.animationStep / distanceY;

			else if (distanceY < distanceX)
				this.animationStep.y = distanceY * Credentials.INSTANCE.animationStep / distanceX;

			// set sign

			if (this.targetCoordinates.x < this.imageView.getCoordinatesTopLeftX())
				this.animationStep.x = -this.animationStep.x;
			if (this.targetCoordinates.y < this.imageView.getCoordinatesTopLeftY())
				this.animationStep.y = -this.animationStep.y;

		}

		public boolean animationIsFinished() {

			if (Math.abs(this.targetCoordinates.x - this.imageView.getCoordinatesTopLeftX()) > 0.1)
				return false;
			else if (Math
					.abs(this.targetCoordinates.y - this.imageView.getCoordinatesTopLeftY()) > 0.1)
				return false;
			else
				return true;

		}

		public void moveTowardsAnimationCoordinates() {
			this.imageView.relocateTopLeft(getCoordinateX(), getCoordinateY());
		}

		private double getCoordinateX() {

			if (Math.abs(this.targetCoordinates.x - this.imageView.getCoordinatesTopLeftX()) < Math
					.abs(this.animationStep.x))
				return this.targetCoordinates.x;
			else
				return this.imageView.getCoordinatesTopLeftX() + this.animationStep.x;

		}

		private double getCoordinateY() {

			if (Math.abs(this.targetCoordinates.y - this.imageView.getCoordinatesTopLeftY()) < Math
					.abs(this.animationStep.y))
				return this.targetCoordinates.y;
			else
				return this.imageView.getCoordinatesTopLeftY() + this.animationStep.y;

		}

	}

}
