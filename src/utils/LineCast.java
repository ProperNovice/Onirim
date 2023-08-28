package utils;

import managers.Credentials;
import utils.Enums.DirectionEnum;
import utils.Enums.MapImageViews;
import utils.Interfaces.IImageViewAble;

public enum LineCast {

	INSTANCE;

	private double startingX, startingY, endingX, endingY, stepX, stepY;
	private ArrayList<IImageViewAble> listLineCast = new ArrayList<IImageViewAble>();
	private ArrayList<IImageViewAble> listStarting = new ArrayList<IImageViewAble>();
	private boolean colliderFound = false;
	private ArrayList<Class<?>> lineCastIncludeList = new ArrayList<Class<?>>();

	public <T> ArrayList<T> lineCastList(IImageViewAble imageViewAble, DirectionEnum directionEnum,
			int units) {

		ImageView imageView = imageViewAble.getImageView();

		double startingX = imageView.getCoordinatesCenterX();
		double startingY = imageView.getCoordinatesCenterY();
		double width = imageView.getWidth();
		double height = imageView.getHeight();
		Vector2 dimensions = new Vector2(width, height);

		return lineCastList(startingX, startingY, dimensions, directionEnum, units);

	}

	@SuppressWarnings("unchecked")
	public <T> Object lineCastFirst(IImageViewAble imageViewAble, DirectionEnum directionEnum,
			int units) {

		lineCastList(imageViewAble, directionEnum, units);

		if (this.listLineCast.isEmpty())
			return null;
		else
			return (T) this.listLineCast.getFirst();

	}

	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> lineCastList(double startingX, double startingY, Vector2 dimensions,
			DirectionEnum directionEnum, int units) {

		this.startingX = startingX;
		this.startingY = startingY;

		setUpCredentials(startingX, startingY, dimensions, directionEnum, units);
		executeLineCast();

		return (ArrayList<T>) this.listLineCast;

	}

	@SuppressWarnings("unchecked")
	public <T> Object lineCastFirst(double startingX, double startingY, Vector2 dimensions,
			DirectionEnum directionEnum, int units) {

		lineCastList(startingX, startingY, dimensions, directionEnum, units);

		if (this.listLineCast.isEmpty())
			return null;
		else
			return (T) this.listLineCast.getFirst();

	}

	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> lineCast(double startingX, double startingY, double endingX,
			double endingY) {

		this.startingX = startingX;
		this.startingY = startingY;
		this.endingX = endingX;
		this.endingY = endingY;

		executeLineCast();
		return (ArrayList<T>) this.listLineCast;

	}

	@SuppressWarnings("unchecked")
	public <T> T objectAtCoordinates(double x, double y) {

		ArrayList<IImageViewAble> list = getImageViewAbleContaining(x, y);

		if (list.isEmpty())
			return null;
		else
			return (T) list.getFirst();

	}

	public <T> T objectAtCoordinates(Vector2 numbersPair) {
		return objectAtCoordinates(numbersPair.x, numbersPair.y);
	}

	public void addLineCastIncludeClass(Class<?> classType) {
		this.lineCastIncludeList.addLast(classType);
	}

	public void clearLineCastIncludeList() {
		this.lineCastIncludeList.clear();
	}

	private void setUpCredentials(double startingX, double startingY, Vector2 dimensions,
			DirectionEnum directionEnum, int units) {

		this.startingX = startingX;
		this.startingY = startingY;
		this.endingX = this.startingX;
		this.endingY = this.startingY;
		this.colliderFound = false;

		double width = units
				* (dimensions.x + Credentials.INSTANCE.dGapBetweenComponentsLineCast.x);
		double height = units
				* (dimensions.y + Credentials.INSTANCE.dGapBetweenComponentsLineCast.y);

		switch (directionEnum) {

		case DOWN:
			this.endingY += height;
			break;

		case LEFT:
			this.endingX -= width;
			break;

		case RIGHT:
			this.endingX += width;
			break;

		case UP:
			this.endingY -= height;
			break;

		}

	}

	private void executeLineCast() {

		this.listLineCast.clear();
		this.listStarting.clear();

		setStepAxis();
		setListStartingImageViewAble();
		setListLineCast();

	}

	private void setStepAxis() {
		setStepX();
		setStepY();
	}

	private void setStepX() {

		if (this.endingX > this.startingX)
			this.stepX = 1;
		else if (this.endingX < this.startingX)
			this.stepX = -1;
		else
			this.stepX = 0;

	}

	private void setStepY() {

		double dX = Math.abs(this.startingX - this.endingX);
		double dY = this.endingY - this.startingY;

		if (dX == 0)
			this.stepY = 1;
		else
			this.stepY = dY / dX;

	}

	private void setListStartingImageViewAble() {
		this.listStarting.addAllLast(getImageViewAbleContaining(this.startingX, this.startingY));
	}

	private void setListLineCast() {

		double currentX = this.startingX;
		double currentY = this.startingY;

		double stepsTotal = 0;

		if (this.stepX != 0)
			stepsTotal = Math.ceil(Math.abs(this.endingX - this.startingX));

		else if (this.stepY != 0)
			stepsTotal = Math.ceil(Math.abs((this.endingY - this.startingY) / this.stepY));

		if (stepsTotal == 0)
			ShutDown.INSTANCE.execute();

		for (int counter = 1; counter <= stepsTotal; counter++) {

			currentX += this.stepX;
			currentY += this.stepY;

			ArrayList<IImageViewAble> list = getImageViewAbleContaining(currentX, currentY);

			if (this.colliderFound)
				return;

			for (IImageViewAble imageViewAble : list) {

				if (this.listStarting.contains(imageViewAble))
					continue;

				if (this.listLineCast.contains(imageViewAble))
					continue;

				this.listLineCast.addLast(imageViewAble);

			}

		}

	}

	private ArrayList<IImageViewAble> getImageViewAbleContaining(double x, double y) {

		ArrayList<IImageViewAble> list = new ArrayList<IImageViewAble>();

		if (Collider.INSTANCE.contains(x, y)) {

			this.colliderFound = true;
			return list;

		}

		for (IImageViewAble imageViewAble : MapImageViews.INSTANCE.getImageViewsMap()) {

			boolean isAssignable = false;

			for (Class<?> classObject : this.lineCastIncludeList)
				if (classObject.isAssignableFrom(imageViewAble.getClass()))
					isAssignable = true;

			if (!isAssignable)
				continue;

			ImageView imageView = MapImageViews.INSTANCE.getImageViewsMap().getValue(imageViewAble);

			if (!imageView.isVisible())
				continue;

			if (!imageView.contains(x, y))
				continue;

			list.addLast(imageViewAble);

		}

		return list;

	}

}
