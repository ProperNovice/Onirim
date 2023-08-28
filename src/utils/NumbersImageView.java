package utils;

import enums.ELayerZ;
import managers.Credentials;
import utils.Interfaces.IImageViewAble;

public enum NumbersImageView {

	INSTANCE;

	private HashMap<Integer, ArrayList<NumberImageView>> list = new HashMap<>();
	private ArrayList<MinusImageView> listMinusImageView = new ArrayList<>();

	private NumbersImageView() {

		for (int counter = 0; counter <= 9; counter++)
			this.list.put(counter, new ArrayList<>());

	}

	public IImageViewAble getNumberImageView(int number) {

		NumberImageView numberImageView = null;

		for (NumberImageView numberImageViewTemp : this.list.getValue(number)) {

			if (numberImageViewTemp.getImageView().isVisible())
				continue;

			numberImageView = numberImageViewTemp;
			numberImageView.getImageView().setVisible(true);
			break;

		}

		if (numberImageView == null) {

			numberImageView = new NumberImageView(number);
			this.list.getValue(number).addLast(numberImageView);

		}

		return numberImageView;

	}

	public IImageViewAble getMinusImageView() {

		MinusImageView minusImageView = null;

		for (MinusImageView minusImageViewTemp : this.listMinusImageView) {

			if (minusImageViewTemp.getImageView().isVisible())
				continue;

			minusImageView = minusImageViewTemp;
			minusImageView.getImageView().setVisible(true);
			break;

		}

		if (minusImageView == null) {

			minusImageView = new MinusImageView();
			this.listMinusImageView.addLast(minusImageView);

		}

		return minusImageView;

	}

	private class NumberImageView implements IImageViewAble {

		public NumberImageView(int number) {

			String fileName = "misc/numbers/";
			fileName += Credentials.INSTANCE.numbersImageViewColor;
			fileName += "/";
			fileName += number;
			fileName += ".png";

			new ImageView(fileName, ELayerZ.ICONS_MISC, this);

		}

	}

	private class MinusImageView implements IImageViewAble {

		public MinusImageView() {

			String fileName = "misc/numbers/";
			fileName += Credentials.INSTANCE.numbersImageViewColor;
			fileName += "/-.png";

			new ImageView(fileName, ELayerZ.ICONS_MISC, this);

		}

	}

}
