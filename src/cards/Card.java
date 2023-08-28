package cards;

import utils.ImageView;
import utils.Interfaces.IImageViewAble;

public abstract class Card implements IImageViewAble {

	public Card() {

	}

	protected final void createImageView() {

		String fileName = "cards/";
		fileName += getFileName();
		fileName += ".png";

		new ImageView(fileName, this);
		getImageView().setBack("cards/back.png");

	}

	protected abstract String getFileName();

}
