package utils;

import enums.EText;
import managers.Credentials;

public class TextEnum {

	private EText eText = null;
	private TextIndicator text = null;

	public TextEnum(EText eText) {

		this.eText = eText;
		createText();

	}

	private void createText() {

		switch (this.eText.getTextTypeEnum()) {

		case INDICATOR:
			this.text = new TextIndicator(this.eText.getString());
			break;

		case OPTION:
			this.text = new TextOption(this.eText);
			break;

		}

		if (this.eText.getString().contains("\n"))
			this.text.setHeight(2 * Credentials.INSTANCE.textHeight);
		else
			this.text.setHeight(Credentials.INSTANCE.textHeight);

		this.text.setVisible(false);

	}

	public void relocate(double x, double y) {
		this.text.relocateTopLeft(x, y);
	}

	public void relocate(Vector2 numbersPair) {
		relocate(numbersPair.x, numbersPair.y);
	}

	public void toFront() {
		this.text.toFront();
	}

	public void setVisible(boolean value) {
		this.text.setVisible(value);
	}

	public EText getEText() {
		return this.eText;
	}

	public TextIndicator getText() {
		return this.text;
	}

}
