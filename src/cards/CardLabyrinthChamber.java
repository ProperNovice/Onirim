package cards;

import enums.EColor;
import enums.ESubType;

public class CardLabyrinthChamber extends CardLabyrinth {

	private EColor eColor = null;
	private ESubType eSubType = null;

	public CardLabyrinthChamber(EColor eColor, ESubType eSubType) {

		this.eColor = eColor;
		this.eSubType = eSubType;
		super.createImageView();

	}

	public EColor getEColor() {
		return this.eColor;
	}

	public ESubType getESubType() {
		return this.eSubType;
	}

	@Override
	protected String getFileName() {

		String fileName = super.getFileName();
		fileName += "chamber/";

		fileName += this.eColor.toString().toLowerCase() + this.eSubType.toString().toLowerCase();

		return fileName;

	}

}
