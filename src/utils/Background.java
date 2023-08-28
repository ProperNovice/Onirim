package utils;

import enums.ELayerZ;
import utils.Interfaces.IImageViewAble;

public class Background implements IImageViewAble {

	public Background(ELayerZ eLayerZ) {
		new ImageView("misc/background.png", eLayerZ, this);
	}

}
