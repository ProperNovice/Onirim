package utils;

import utils.Interfaces.IImageViewAble;

public class Enums {

	public enum AnimationSynchEnum {
		SYNCHRONOUS, ASYNCHRONOUS
	}

	public enum RelocateTypeEnum {
		TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, CENTER
	}

	public enum DirectionEnum {
		UP, DOWN, LEFT, RIGHT
	}

	public enum LayerZListEnum {
		TO_FRONT_FIRST_IMAGEVIEW, TO_BACK_FIRST_IMAGEVIEW
	}

	public enum ImageViewActionEnum {
		ANIMATE, RELOCATE
	}

	public enum RearrangeTypeEnum {
		LINEAR, PIVOT, STATIC
	}

	public enum TextTypeEnum {
		INDICATOR, OPTION
	}

	public enum MapImageViews {

		INSTANCE;

		private HashMap<IImageViewAble, ImageView> mapImageViews = new HashMap<>();

		public HashMap<IImageViewAble, ImageView> getImageViewsMap() {
			return this.mapImageViews;
		}

	}

}
