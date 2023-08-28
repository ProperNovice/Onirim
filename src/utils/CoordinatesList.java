package utils;

import managers.Credentials;
import utils.Enums.DirectionEnum;
import utils.Interfaces.IImageViewAble;

public class CoordinatesList<T> {

	private ListImageViewAbles<T> listImageViewAbles = null;

	private Vector2 dimensionsImageViewAble, firstObject;

	public CoordinatesList(ListImageViewAbles<T> list) {

		this.listImageViewAbles = list;
		this.firstObject = new Vector2(0, 0);

	}

	public Vector2 getCoordinate(int index) {

		IImageViewAble imageViewAble = (IImageViewAble) this.listImageViewAbles.getArrayList()
				.getFirst();

		this.dimensionsImageViewAble = imageViewAble.getImageView().getDimenions();

		switch (this.listImageViewAbles.getListCredentials().rearrangeTypeEnum) {

		case LINEAR:
			this.firstObject.x = this.listImageViewAbles.getListCredentials().coordinatesList.x;
			this.firstObject.y = this.listImageViewAbles.getListCredentials().coordinatesList.y;
			break;

		case PIVOT:
			calculateFirstObjectCoordinatesPivot();
			break;

		case STATIC:
			return CoordinatesRelocateType.INSTANCE.translateRelocateTypeCoordinatesSubstract(
					new Vector2(this.listImageViewAbles.getListCredentials().coordinatesList.x,
							this.listImageViewAbles.getListCredentials().coordinatesList.y),
					this.dimensionsImageViewAble,
					this.listImageViewAbles.getListCredentials().relocateTypeEnum);

		}

		double coordinateX = 0, coordinateY = 0;

		int row, column;

		if (this.listImageViewAbles.getListCredentials().objectsPerRow == -1) {

			row = 0;
			column = index;

		} else {

			row = index / this.listImageViewAbles.getListCredentials().objectsPerRow;
			column = index - row * this.listImageViewAbles.getListCredentials().objectsPerRow;

		}

		double x, y;

		if (this.listImageViewAbles
				.getListCredentials().gapBetweenComponents.x == Credentials.INSTANCE.dGapBetweenComponents.x)
			x = column * (this.dimensionsImageViewAble.x
					+ this.listImageViewAbles.getListCredentials().gapBetweenComponents.x);
		else
			x = column * this.listImageViewAbles.getListCredentials().gapBetweenComponents.x;

		if (this.listImageViewAbles
				.getListCredentials().gapBetweenComponents.y == Credentials.INSTANCE.dGapBetweenComponents.y)
			y = row * (this.dimensionsImageViewAble.y
					+ this.listImageViewAbles.getListCredentials().gapBetweenComponents.y);
		else
			y = row * this.listImageViewAbles.getListCredentials().gapBetweenComponents.y;

		switch (this.listImageViewAbles.getListCredentials().directionEnumHorizontal) {

		case RIGHT:
			coordinateX = this.firstObject.x + x;
			break;

		case LEFT:
			coordinateX = this.firstObject.x - x;
			break;

		default:
			logErrorShutDown(this.listImageViewAbles.getListCredentials().directionEnumHorizontal);
			break;

		}

		switch (this.listImageViewAbles.getListCredentials().directionEnumVertical) {

		case DOWN:
			coordinateY = this.firstObject.y + y;
			break;

		case UP:
			coordinateY = this.firstObject.y - y;
			break;

		default:
			logErrorShutDown(this.listImageViewAbles.getListCredentials().directionEnumVertical);
			break;

		}

		return CoordinatesRelocateType.INSTANCE.translateRelocateTypeCoordinatesSubstract(
				new Vector2(coordinateX, coordinateY), this.dimensionsImageViewAble,
				this.listImageViewAbles.getListCredentials().relocateTypeEnum);

	}

	private void calculateFirstObjectCoordinatesPivot() {

		int rows, columns;
		int listSize = this.listImageViewAbles.getArrayList().size();

		if (this.listImageViewAbles.getListCredentials().objectsPerRow == -1) {

			rows = 1;
			columns = listSize;

		} else {

			rows = (int) (Math.ceil((double) listSize
					/ this.listImageViewAbles.getListCredentials().objectsPerRow));
			columns = (int) Math.min(listSize,
					this.listImageViewAbles.getListCredentials().objectsPerRow);

		}

		double totalX, totalY;

		if (this.listImageViewAbles
				.getListCredentials().gapBetweenComponents.x == Credentials.INSTANCE.dGapBetweenComponents.x)
			totalX = this.dimensionsImageViewAble.x
					+ (columns - 1) * (this.dimensionsImageViewAble.x
							+ this.listImageViewAbles.getListCredentials().gapBetweenComponents.x);
		else
			totalX = this.dimensionsImageViewAble.x + (columns - 1)
					* this.listImageViewAbles.getListCredentials().gapBetweenComponents.x;

		if (this.listImageViewAbles
				.getListCredentials().gapBetweenComponents.y == Credentials.INSTANCE.dGapBetweenComponents.y)
			totalY = this.dimensionsImageViewAble.y + (rows - 1) * (this.dimensionsImageViewAble.y
					+ this.listImageViewAbles.getListCredentials().gapBetweenComponents.y);
		else
			totalY = this.dimensionsImageViewAble.y + (rows - 1)
					* this.listImageViewAbles.getListCredentials().gapBetweenComponents.y;

		switch (this.listImageViewAbles.getListCredentials().directionEnumHorizontal) {

		case RIGHT:
			this.firstObject.x = this.listImageViewAbles.getListCredentials().coordinatesList.x
					- totalX / 2;
			break;

		case LEFT:
			this.firstObject.x = this.listImageViewAbles.getListCredentials().coordinatesList.x
					+ totalX / 2 - this.dimensionsImageViewAble.x;
			break;

		default:
			logErrorShutDown(this.listImageViewAbles.getListCredentials().directionEnumHorizontal);
			break;

		}

		switch (this.listImageViewAbles.getListCredentials().directionEnumVertical) {

		case DOWN:
			this.firstObject.y = this.listImageViewAbles.getListCredentials().coordinatesList.y
					- totalY / 2;
			break;

		case UP:
			this.firstObject.y = this.listImageViewAbles.getListCredentials().coordinatesList.y
					+ totalY / 2;
			break;

		default:
			logErrorShutDown(this.listImageViewAbles.getListCredentials().directionEnumVertical);
			break;

		}

		this.firstObject = CoordinatesRelocateType.INSTANCE.translateRelocateTypeCoordinatesAdd(
				this.firstObject, this.dimensionsImageViewAble,
				this.listImageViewAbles.getListCredentials().relocateTypeEnum);

	}

	private void logErrorShutDown(DirectionEnum directionEnum) {

		Logger.INSTANCE.log("ArrayListImageView direction error:");
		Logger.INSTANCE.log(directionEnum);
		ShutDown.INSTANCE.execute();

	}

}
