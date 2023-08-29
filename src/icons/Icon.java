package icons;

import cards.Card;
import enums.ELayerZ;
import managers.Credentials;
import utils.ImageView;
import utils.Interfaces.IImageViewAble;

public abstract class Icon implements IImageViewAble {

	protected Card card = null;

	public Icon() {

		String fileName = getFileName() + ".png";

		new ImageView(fileName, ELayerZ.ICONS_MISC, this);
		getImageView().setDimensions(Credentials.INSTANCE.dIcon);

	}

	public final void setCard(Card card) {
		this.card = card;
	}

	protected abstract String getFileName();

}
