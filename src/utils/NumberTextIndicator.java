package utils;

public class NumberTextIndicator {

	private TextIndicator textIndicator = new TextIndicator();
	private String text = "";
	private int totalValue, startingValue, currentValue;

	public NumberTextIndicator(String text, int totalValue, int startingValue, double x, double y) {

		this.text = text;
		this.textIndicator.relocateTopLeft(x, y);
		this.totalValue = totalValue;
		this.startingValue = startingValue;
		this.currentValue = this.startingValue;

		updateIndicator();

	}

	public NumberTextIndicator(String text, int totalValue, int startingValue, Vector2 coordinates) {
		this(text, totalValue, startingValue, coordinates.x, coordinates.y);
	}

	public NumberTextIndicator(String text, int startingValue, double x, double y) {
		this(text, -1, startingValue, x, y);
	}

	public NumberTextIndicator(String text, int startingValue, Vector2 coordinates) {
		this(text, startingValue, coordinates.x, coordinates.y);
	}

	public void add(int amount) {

		this.currentValue += amount;
		updateIndicator();

	}

	public void addOne() {
		add(1);
	}

	public void substract(int amount) {

		this.currentValue -= amount;
		updateIndicator();

	}

	public void substractOne() {
		substract(1);
	}

	private void updateIndicator() {

		String text = this.text;
		text += ": ";
		text += this.currentValue;

		if (this.totalValue != -1) {

			text += "/";
			text += this.totalValue;

		}

		this.textIndicator.setText(text);

	}

	public void setCurrentValue(int value) {

		this.currentValue = value;
		updateIndicator();

	}

	public int getCurrentValue() {
		return this.currentValue;
	}

}
