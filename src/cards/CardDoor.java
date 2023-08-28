package cards;

import enums.EColor;

public class CardDoor extends Card {

	private EColor eColor = null;

	public CardDoor(EColor eColor) {

		this.eColor = eColor;
		super.createImageView();

	}

	@Override
	protected String getFileName() {
		return "door/" + this.eColor.toString().toLowerCase();
	}

	public EColor getEColor() {
		return this.eColor;
	}

}
