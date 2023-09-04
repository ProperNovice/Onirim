package enums;

import utils.Enums.TextTypeEnum;
import utils.TextManager;

public enum EText {

	CANCEL("Cancel", TextTypeEnum.OPTION),
	CONTINUE("Continue", TextTypeEnum.OPTION),
	RESTART("Restart", TextTypeEnum.OPTION),
	START_GAME("Start game", TextTypeEnum.OPTION),
	VOID("", TextTypeEnum.INDICATOR),
	YOU_LOST("You lost", TextTypeEnum.INDICATOR),
	YOU_WON("You won", TextTypeEnum.INDICATOR),
	DISCARD_A_KEY_FROM_YOUR_HAND("Discard a key from your hand", TextTypeEnum.OPTION),
	PLACE_A_DOOR_IN_LIMBO("Place a door in limbo", TextTypeEnum.OPTION),
	REVEAL_THE_FIRST_FIVE_CARDS("Reveal the first five cards", TextTypeEnum.OPTION),
	DISCARD_YOUR_HAND("Discard your hand", TextTypeEnum.OPTION),
	OBTAIN_DOOR("Obtain door", TextTypeEnum.OPTION),
	PUT_IT_IN_LIMBO("Put it in limbo", TextTypeEnum.OPTION),

	;

	private TextTypeEnum textTypeEnum = null;
	private String string = null;

	private EText(String string, TextTypeEnum textTypeEnum) {

		this.string = string;
		this.textTypeEnum = textTypeEnum;

	}

	public void show() {
		TextManager.INSTANCE.showText(this, getString());
	}

	public void showAdditionally(String string) {
		TextManager.INSTANCE.showText(this, getString() + string);
	}

	public void showAdditionally(int integer) {
		showAdditionally(Integer.toString(integer));
	}

	public void showInstead(String string) {
		TextManager.INSTANCE.showText(this, string);
	}

	public String getString() {
		return this.string;
	}

	public TextTypeEnum getTextTypeEnum() {
		return this.textTypeEnum;
	}

}
