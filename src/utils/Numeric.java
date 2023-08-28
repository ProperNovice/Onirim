package utils;

import utils.Interfaces.ISaveLoadStateAble;

public class Numeric implements ISaveLoadStateAble {

	private int numberCurrent, numberOriginal, numberState;

	public Numeric(int number) {

		this.numberCurrent = number;
		saveOriginal();

	}

	public void set(int number) {
		this.numberCurrent = number;
	}

	public void add(int number) {
		this.numberCurrent += number;
	}

	public void substract(int number) {
		this.numberCurrent -= number;
	}

	public int random(int firstNumber, int secondNumber) {
		this.numberCurrent = Random.INSTANCE.getRandomNumber(firstNumber, secondNumber);
		return this.numberCurrent;
	}

	public int max(int number) {
		this.numberCurrent = Math.max(this.numberCurrent, number);
		return this.numberCurrent;
	}

	public int min(int number) {
		this.numberCurrent = Math.min(this.numberCurrent, number);
		return this.numberCurrent;
	}

	public int get() {
		return this.numberCurrent;
	}

	public void print() {
		Logger.INSTANCE.logNewLine("number -> " + this.numberCurrent);
	}

	@Override
	public String toString() {
		return Integer.toString(this.numberCurrent);
	}

	@Override
	public void saveOriginal() {
		this.numberOriginal = this.numberCurrent;
	}

	@Override
	public void loadOriginal() {
		this.numberCurrent = this.numberOriginal;
	}

	@Override
	public void saveState() {
		this.numberState = this.numberCurrent;
	}

	@Override
	public void loadState() {
		this.numberCurrent = this.numberState;
	}

}
