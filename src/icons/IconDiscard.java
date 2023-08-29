package icons;

import utils.Flow;

public class IconDiscard extends Icon {

	@Override
	protected String getFileName() {
		return "discard";
	}

	@Override
	public void handleMousePressedPrimary() {
		Flow.INSTANCE.getGameStateCurrent().handleCardIconDiscardPressed(super.card);
	}

}
