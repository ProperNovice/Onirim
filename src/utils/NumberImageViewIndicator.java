package utils;

import listCredentials.ListCredentials;
import managers.Credentials;
import utils.Interfaces.IImageViewAble;

public class NumberImageViewIndicator {

	private ListImageViewAbles<IImageViewAble> list = null;
	private double dimension = 100;
	private int digits = 1;

	public NumberImageViewIndicator() {
		this(Credentials.INSTANCE.selectEventHandlerAbleDimension);
	}

	public NumberImageViewIndicator(double dimension) {
		this(dimension, 1);
	}

	public NumberImageViewIndicator(double dimension, int digits) {

		this.dimension = dimension;
		this.digits = digits;
		this.list = new ListImageViewAbles<>(ListCredentials.class);

	}

	public void setNumber(int number, double dimension) {

		if (dimension == 0)
			ShutDown.INSTANCE.execute();

		// clear currents

		clear();

		// check for minus

		if (number < 0) {

			this.list.getArrayList().addLast(NumbersImageView.INSTANCE.getMinusImageView());
			number = Math.abs(number);

		}

		// create list of numbers

		ArrayList<Integer> listInteger = new ArrayList<>();

		while (number > 0) {

			listInteger.addFirst(number % 10);
			number /= 10;

		}

		while (listInteger.size() < this.digits)
			listInteger.addFirst(0);

		// replete list

		for (Integer integer : listInteger)
			this.list.getArrayList().addLast(NumbersImageView.INSTANCE.getNumberImageView(integer));

		// resize image views

		for (IImageViewAble imageViewAbleTemp : this.list)
			imageViewAbleTemp.getImageView().setWidth(dimension);

		// set gap between components to 0

		this.list.getListCredentials().gapBetweenComponents = new Vector2(dimension, 0);

		// relocate

		this.list.relocateImageViews();

	}

	public void setNumber(int number) {
		setNumber(number, this.dimension);
	}

	public void clear() {

		for (IImageViewAble imageViewAble : this.list)
			imageViewAble.getImageView().setVisible(false);

		this.list.getArrayList().clear();

	}

	public ListCredentials getListCredentials() {
		return this.list.getListCredentials();
	}

}
