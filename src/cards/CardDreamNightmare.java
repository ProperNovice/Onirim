package cards;

public class CardDreamNightmare extends CardDream {

	public CardDreamNightmare() {
		super.createImageView();
	}

	@Override
	protected String getFileName() {
		return super.getFileName() + "nightmare";
	}

}
