package utils;

import enums.EText;
import managers.Credentials;
import utils.Enums.RearrangeTypeEnum;
import utils.Enums.TextTypeEnum;

public enum TextManager {

	INSTANCE;

	private ArrayList<TextEnum> textEnum = new ArrayList<>();
	private ArrayList<TextEnum> textEnumShowing = new ArrayList<>();

	private TextManager() {

		for (EText eText : EText.values())
			this.textEnum.addLast(new TextEnum(eText));

	}

	public void showText(EText eText, String text) {

		for (TextEnum textEnum : this.textEnum) {

			if (!textEnum.getEText().equals(eText))
				continue;

			textEnum.getText().setText(text);
			this.textEnumShowing.addLast(textEnum);
			break;

		}

		showText();

	}

	private void showText() {

		double x = Credentials.INSTANCE.cTextPanel.x;
		double y = Credentials.INSTANCE.cTextPanel.y;

		if (Credentials.INSTANCE.rearrangeTypeEnumText.equals(RearrangeTypeEnum.PIVOT)) {

			double totalHeight = this.textEnumShowing.size() * Credentials.INSTANCE.textHeight;
			y -= totalHeight / 2;

		}

		for (TextEnum textEnum : this.textEnumShowing) {

			textEnum.toFront();
			textEnum.setVisible(true);
			textEnum.relocate(x, y);

			y += Credentials.INSTANCE.textHeight;

		}

	}

	public void concealText() {

		for (TextEnum textEnum : this.textEnumShowing)
			textEnum.setVisible(false);

		this.textEnumShowing.clear();

	}

	public EText getTextEnumOptionPressed(int textOptionID) {

		ArrayList<TextEnum> textEnumOptionShowing = this.textEnumShowing.clone();

		for (TextEnum textGame : textEnumOptionShowing.clone())
			if (textGame.getEText().getTextTypeEnum().equals(TextTypeEnum.INDICATOR))
				textEnumOptionShowing.remove(textGame);

		if (textOptionID >= textEnumOptionShowing.size())
			return null;
		else
			return textEnumOptionShowing.get(textOptionID).getEText();

	}

}
