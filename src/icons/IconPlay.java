package icons;

import utils.Flow;

public class IconPlay extends Icon {

	@Override
	protected String getFileName() {
		return "play";
	}

	@Override
	public void handleMousePressedPrimary() {
		Flow.INSTANCE.getGameStateCurrent().handleCardIconPlayPressed(super.card);
	}

}
