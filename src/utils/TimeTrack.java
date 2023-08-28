package utils;

public enum TimeTrack {

	INSTANCE;

	private long start, end;

	private TimeTrack() {

	}

	public void start() {
		this.start = getTimeMillis();
	}

	public void end() {

		this.end = getTimeMillis();

		String string = "time passed - ";
		string += (this.end - this.start);
		string += " millis";
		Logger.INSTANCE.logNewLine(string);

	}

	private long getTimeMillis() {
		return System.currentTimeMillis();
	}

}
