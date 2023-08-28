package utils;

public enum Random {

	INSTANCE;

	private java.util.Random random = new java.util.Random();

	public int getRandomNumber(int firstNumber, int secondNumber) {
		return firstNumber + this.random.nextInt(secondNumber - firstNumber + 1);
	}

	public boolean chanceOutcome(double chance) {
		return this.getRandomNumber(1, 100) <= chance;
	}

}
