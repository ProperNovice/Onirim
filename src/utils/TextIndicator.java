package utils;

import gui.InstancesGui;
import javafx.scene.text.Font;
import managers.Credentials;
import utils.Interfaces.INode;

public class TextIndicator implements INode {

	protected javafx.scene.text.Text text = null;
	protected String textVisibleTrue = "", textVisibleFalse = "", textOriginal = "";
	private double heightConstraints = 41.005859375;

	public TextIndicator(String stringText) {

		this.text = new javafx.scene.text.Text(stringText);

		this.textOriginal += stringText;
		setHeight(Credentials.INSTANCE.textHeight);

		InstancesGui.INSTANCE.getParent().addNode(this.text);
		LayerZ.INSTANCE.addTextIndicator(this);

		this.text.relocate(0, 0);

	}

	public TextIndicator(int value) {
		this(Integer.toString(value));
	}

	public TextIndicator() {
		this("");
	}

	@Override
	public void setVisible(final boolean value) {

		if (value)
			this.text.setText(this.textVisibleTrue);
		else
			this.text.setText(this.textVisibleFalse);

	}

	@Override
	public boolean isVisible() {
		return this.text.isVisible();
	}

	public void toBack() {
		this.text.toBack();
	}

	@Override
	public void toFront() {
		this.text.toFront();
	}

	@Override
	public final double getCoordinatesTopLeftX() {
		return this.text.getLayoutX();
	}

	@Override
	public final double getCoordinatesTopLeftY() {
		return this.text.getLayoutY() - this.heightConstraints;
	}

	@Override
	public Vector2 getCoordinatesTopLeft() {
		return new Vector2(getCoordinatesTopLeftX(), getCoordinatesTopLeftY());
	}

	@Override
	public void relocateTopLeft(final double x, final double y) {
		this.text.relocate(x, y);
	}

	@Override
	public void relocateTopLeft(final Vector2 numbersPair) {
		relocateTopLeft(numbersPair.x, numbersPair.y);
	}

	@Override
	public void relocateCenter(double x, double y) {

		x -= getWidth() / 2;
		y -= getHeight() / 2;
		relocateTopLeft(x, y);

	}

	@Override
	public void relocateCenter(Vector2 vector2) {
		relocateCenter(vector2.x, vector2.y);
	}

	@Override
	public double getCoordinatesCenterX() {
		return this.getCoordinatesTopLeftX() + this.getWidth() / 2;
	}

	@Override
	public double getCoordinatesCenterY() {
		return this.getCoordinatesTopLeftY() + this.getHeight() / 2;
	}

	@Override
	public Vector2 getCoordinatesCenter() {
		return new Vector2(getCoordinatesCenterX(), getCoordinatesCenterY());
	}

	@Override
	public double getWidth() {
		return this.text.minWidth(0);
	}

	@Override
	public double getHeight() {
		return this.text.minHeight(0);
	}

	public void setWidth(final double pixels) {

		int font = 1;
		setFont(font);

		while (getWidth() <= pixels)
			setFont(++font);

		setFont(font);

	}

	public void setHeight(final double pixels) {

		int font = 1;
		setFont(font);

		while (getHeight() <= pixels)
			setFont(++font);

		setFont(font);

	}

	public final void setText(String text) {
		this.textVisibleTrue = text;
		setVisible(true);
	}

	public final void setText(int value) {
		this.textVisibleTrue = Integer.toString(value);
		setVisible(true);
	}

	private void setFont(final int value) {
		this.text.setFont(new Font(value));
	}

}
