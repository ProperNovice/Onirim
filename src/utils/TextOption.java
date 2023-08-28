package utils;

import enums.EText;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class TextOption extends TextIndicator {

	private Rectangle rectangle = null;
	private EText eText = null;

	public TextOption(EText eText) {

		super(eText.getString());
		this.eText = eText;

		createBorder();

		this.rectangle.toFront();
		super.text.toFront();

		setEventHandlers();

	}

	private void createBorder() {

		this.rectangle = new Rectangle(super.getWidth(), super.getHeight());
		this.rectangle.setStroke(null);
		this.rectangle.setFill(Color.WHEAT);

	}

	@Override
	public void toFront() {

		this.rectangle.toFront();
		super.text.toFront();

	}

	@Override
	public void setWidth(final double pixels) {
		super.setWidth(pixels);
		resizePolyLine();
	}

	@Override
	public void setHeight(final double pixels) {

		super.setHeight(pixels);

		if (this.rectangle != null)
			resizePolyLine();

	}

	private void resizePolyLine() {

		this.rectangle.getPoints().clear();
		this.rectangle.getPoints().addAll(0.0, 0.0, getWidth(), 0.0, getWidth(), getHeight(), 0.0,
				getHeight(), 0.0, 0.0);

	}

	@Override
	public void relocateTopLeft(final double x, double y) {

		this.text.relocate(x, y);
		this.rectangle.relocateTopLeft(x, y);

	}

	@Override
	public final void setVisible(final boolean value) {

		super.setVisible(value);
		this.rectangle.setVisible(value);

	}

	protected class OnMousePressed implements javafx.event.EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			Flow.INSTANCE.getGameStateCurrent().handleTextOptionPressed(eText);
		}

	}

	protected class OnMouseEntered implements javafx.event.EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {

			rectangle.setFill(Color.BLACK);
			text.setFill(Color.WHITE);

		}

	}

	protected class OnMouseExited implements javafx.event.EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {

			rectangle.setFill(Color.WHEAT);
			text.setFill(Color.BLACK);

		}

	}

	private void setEventHandlers() {

		OnMouseEntered onMouseEntered = new OnMouseEntered();
		OnMouseExited onMouseExited = new OnMouseExited();

		super.text.setOnMouseEntered(onMouseEntered);
		this.rectangle.setOnMouseEntered(onMouseEntered);
		super.text.setOnMouseExited(onMouseExited);
		this.rectangle.setOnMouseExited(onMouseExited);

		super.text.setOnMousePressed(new OnMousePressed());
		this.rectangle.setOnMousePressed(new OnMousePressed());

	}

}
