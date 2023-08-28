package utils;

import managers.Credentials;
import utils.Enums.MapImageViews;

public class Interfaces {

	public interface IMouseEventAble {

		public default void handleMousePressedPrimary() {

		};

		public default void handleMousePressedSecondary() {

		};

		public default void handleMouseEntered() {

		};

		public default void handleMouseExited() {

		};

		public default void handleMouseDragged() {

		};

		public default void handleMouseReleased() {

		};

	}

	public interface ISaveLoadStateAble {

		public default void saveOriginal() {
			Logger.INSTANCE.logNewLine("save original not implemented");
		}

		public default void loadOriginal() {
			Logger.INSTANCE.logNewLine("load original not implemented");
		}

		public default void saveState() {
			Logger.INSTANCE.logNewLine("save state not implemented");
		}

		public default void loadState() {
			Logger.INSTANCE.logNewLine("load state not implemented");
		}

	}

	public interface IImageViewAble extends IMouseEventAble {

		public default ImageView getImageView() {
			return MapImageViews.INSTANCE.getImageViewsMap().getValue(this);
		}

		public default void reverseSelectImageView() {
			SelectImageViewManager.INSTANCE.reverseSelectImageViewAble(this);
		}

		public default boolean isSelected() {
			return SelectImageViewManager.INSTANCE.isSelectedImageViewAble(this);
		}

		public default void setSelected() {
			SelectImageViewManager.INSTANCE.addSelectImageViewAble(this);
		}

	}

	public interface ISelectCoordinatesAble extends IMouseEventAble {

		public Vector2 getSelectCoordinatesCenter();

		public default double getSelectDimension() {
			return Credentials.INSTANCE.selectEventHandlerAbleDimension;
		}

		public default void reverseSelectCoordinates() {
			SelectImageViewManager.INSTANCE.reverseSelectCoordinates((IMouseEventAble) this);
		}

		public default boolean isSelected() {
			return SelectImageViewManager.INSTANCE.isSelectedCoordinates((IMouseEventAble) this);
		}

		public default void setSelected() {
			SelectImageViewManager.INSTANCE.addSelectCoordinates((IMouseEventAble) this);
		}

		public default void releaseSelected() {
			SelectImageViewManager.INSTANCE.releaseSelectCoordinates((IMouseEventAble) this);
		}

	}

	public interface IUpdateAble {

		public void update();

	}

	public interface INode {

		public void relocateTopLeft(double x, double y);

		public void relocateTopLeft(Vector2 vector2);

		public void relocateCenter(double x, double y);

		public void relocateCenter(Vector2 vector2);

		public double getCoordinatesTopLeftX();

		public double getCoordinatesTopLeftY();

		public Vector2 getCoordinatesTopLeft();

		public double getCoordinatesCenterX();

		public double getCoordinatesCenterY();

		public Vector2 getCoordinatesCenter();

		public void toFront();

		public void toBack();

		public double getWidth();

		public double getHeight();

		public void setVisible(boolean value);

		public boolean isVisible();

	}

}
