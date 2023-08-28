package utils;

import utils.Interfaces.ISaveLoadStateAble;

public class Boolean implements ISaveLoadStateAble {

	private boolean booleanCurrent, booleanOriginal, booleanState;

	public Boolean(boolean value) {
		this.booleanCurrent = value;
		saveOriginal();
	}

	public void set(boolean value) {
		this.booleanCurrent = value;
	}

	public boolean get() {
		return this.booleanCurrent;
	}

	public void print() {
		Logger.INSTANCE.logNewLine("boolean -> " + this.booleanCurrent);
	}

	@Override
	public String toString() {
		return java.lang.Boolean.toString(this.booleanCurrent);
	}

	@Override
	public void saveOriginal() {
		this.booleanOriginal = this.booleanCurrent;
	}

	@Override
	public void loadOriginal() {
		this.booleanCurrent = this.booleanOriginal;
	}

	@Override
	public void saveState() {
		this.booleanState = this.booleanCurrent;
	}

	@Override
	public void loadState() {
		this.booleanCurrent = this.booleanState;
	}

}
