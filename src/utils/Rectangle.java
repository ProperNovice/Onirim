package utils;

import gui.InstancesGui;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Rectangle {

	private javafx.scene.shape.Polyline rectangle = new javafx.scene.shape.Polyline();
	private double topLeftX = 0, topLeftY = 0;
	private double width, height;

	public Rectangle(double width, double height) {

		this.width = width;
		this.height = height;

		createRectangle();

	}

	public Rectangle(double squareDimension) {
		this(squareDimension, squareDimension);
	}

	public Rectangle(double topLeftX, double topLeftY, double width, double height) {

		this(width, height);
		relocateTopLeft(topLeftX, topLeftY);

	}

	private void createRectangle() {

		this.rectangle.getPoints().addAll(0.0, 0.0, this.width, 0.0, this.width, this.height, 0.0,
				this.height, 0.0, 0.0);

		InstancesGui.INSTANCE.getParent().addNode(this.rectangle);
		this.rectangle.setFill(null);
		this.rectangle.setStroke(Color.BLACK);

	}

	public final ObservableList<Double> getPoints() {
		return this.rectangle.getPoints();
	}

	public boolean contains(double localX, double localY) {

		if (localX < this.topLeftX)
			return false;

		if (localX > this.topLeftX + this.width)
			return false;

		if (localY < this.topLeftY)
			return false;

		if (localY > this.topLeftY + this.height)
			return false;

		return true;

	}

	public void relocateTopLeft(double x, double y) {

		this.topLeftX = x;
		this.topLeftY = y;
		this.rectangle.relocate(x, y);

	}

	public void relocateTopLeft(final Vector2 numbersPair) {
		relocateTopLeft(numbersPair.x, numbersPair.y);
	}

	public void setWidth(double width) {

		this.width = width;
		resizeRectangle();

	}

	public void setHeight(double height) {

		this.height = height;
		resizeRectangle();

	}

	private void resizeRectangle() {

		this.rectangle.getPoints().clear();
		this.rectangle.getPoints().addAll(0.0, 0.0, getWidth(), 0.0, getWidth(), getHeight(), 0.0,
				getHeight(), 0.0, 0.0);

	}

	public double getWidth() {
		return this.width;
	}

	public double getHeight() {
		return this.height;
	}

	public final void setFill(Paint value) {
		this.rectangle.setFill(value);
	}

	public final void setStroke(Paint value) {
		this.rectangle.setStroke(value);
	}

	public final void setOnMouseEntered(javafx.event.EventHandler<? super MouseEvent> value) {
		this.rectangle.setOnMouseEntered(value);
	}

	public final void setOnMouseExited(javafx.event.EventHandler<? super MouseEvent> value) {
		this.rectangle.setOnMouseExited(value);
	}

	public final void setOnMousePressed(javafx.event.EventHandler<? super MouseEvent> value) {
		this.rectangle.setOnMousePressed(value);
	}

	public void toBack() {
		this.rectangle.toBack();
	}

	public void toFront() {
		this.rectangle.toFront();
	}

	public final void setVisible(boolean visibility) {
		this.rectangle.setVisible(visibility);
	}

	public double getLayoutX() {
		return this.rectangle.getLayoutX();
	}

	public double getLayoutY() {
		return this.rectangle.getLayoutY();
	}

	public Vector2 getLayout() {
		return new Vector2(getLayoutX(), getLayoutY());
	}

}
