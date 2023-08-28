package utils;

import enums.ELayerZ;
import utils.Interfaces.IImageViewAble;
import utils.Interfaces.IMouseEventAble;

public class SelectImageView implements IImageViewAble, IMouseEventAble {

	public SelectImageView() {

		new ImageView("misc/select.png", ELayerZ.ICONS_MISC, this);
		this.getImageView().setVisible(false);

	}

	@Override
	public void handleMousePressedPrimary() {
		SelectImageViewManager.INSTANCE.handleMouseButtonPressedPrimary(this);
	}

	@Override
	public void handleMousePressedSecondary() {
		SelectImageViewManager.INSTANCE.handleMouseButtonPressedSecondary(this);
	}

	@Override
	public void handleMouseEntered() {
		SelectImageViewManager.INSTANCE.handleMouseEntered(this);
	}

	@Override
	public void handleMouseExited() {
		SelectImageViewManager.INSTANCE.handleMouseExited(this);
	}

}
