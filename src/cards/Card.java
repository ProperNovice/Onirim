package cards;

import icons.IconDiscard;
import icons.IconPlay;
import managers.Credentials;
import utils.ImageView;
import utils.Interfaces.IImageViewAble;
import utils.ObjectPool;
import utils.Vector2;

public abstract class Card implements IImageViewAble {

	private IconPlay iconPlay = null;
	private IconDiscard iconDiscard = null;

	public Card() {

	}

	protected final void createImageView() {

		String fileName = "cards/";
		fileName += getFileName();
		fileName += ".png";

		new ImageView(fileName, this);
		getImageView().setBack("cards/back.png");

	}

	public final void setIconPlay() {

		Vector2 vector2 = getImageView().getCoordinatesCenter();
		vector2.substractY(Credentials.INSTANCE.dIcon.y / 2);

		this.iconPlay = ObjectPool.INSTANCE.acquire(IconPlay.class);
		this.iconPlay.getImageView().relocateCenter(vector2);
		this.iconPlay.setCard(this);

	}

	public final void setIconDiscard() {

		Vector2 vector2 = getImageView().getCoordinatesCenter();
		vector2.addY(Credentials.INSTANCE.dIcon.y / 2);

		this.iconDiscard = ObjectPool.INSTANCE.acquire(IconDiscard.class);
		this.iconDiscard.getImageView().relocateCenter(vector2);
		this.iconDiscard.setCard(this);

	}

	public final void releaseIconPlay() {

		this.iconPlay.getImageView().setVisible(false);
		this.iconPlay = null;

	}

	public final void releaseIconDiscard() {

		this.iconDiscard.getImageView().setVisible(false);
		this.iconDiscard = null;

	}

	protected abstract String getFileName();

}
