package models;

import enums.EColor;
import enums.ESubType;
import managers.Credentials;
import utils.HashMap;
import utils.TextIndicator;

public enum ModelStatistics {

	INSTANCE;

	private HashMap<EColor, TextIndicator> eColors = new HashMap<>();
	private HashMap<ESubType, TextIndicator> eSubTypes = new HashMap<>();

	private ModelStatistics() {

		double x, y, length = 0;

		// colors

		for (EColor eColor : EColor.values())
			this.eColors.put(eColor, new TextIndicator(eColor.toString().toLowerCase()));

		for (EColor eColor : this.eColors)
			length = Math.max(this.eColors.getValue(eColor).getWidth(), length);

		x = Credentials.INSTANCE.gapBetweenBorders;
		x += length / 2;
		y = Credentials.INSTANCE.gapBetweenBorders;
		y += Credentials.INSTANCE.dCard.y;
		y += Credentials.INSTANCE.dGapBetweenComponents.y;
		y += Credentials.INSTANCE.textHeight / 2;

		for (EColor eColor : EColor.values()) {

			System.out.println(x + " " + y);
			
			this.eColors.getValue(eColor).relocateCenter(x, y);
			y += Credentials.INSTANCE.textHeight;

		}
		
		// sub types

//		for (ESubType eSubType : ESubType.values())
//			this.eSubTypes.put(eSubType, new TextIndicator(eSubType.toString().toLowerCase()));

	}

	public void update() {

	}

}
