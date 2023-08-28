package utils;

public enum ShutDown {

	INSTANCE;

	public void execute() {

		Class<?> classObject = null;

		try {

			classObject = Class.forName(new Exception().getStackTrace()[1].getClassName());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Logger.INSTANCE.newLine();
		Logger.INSTANCE.log(classObject);
		Logger.INSTANCE.log(new Throwable().getStackTrace()[1].getMethodName() + "()");
		Logger.INSTANCE.logNewLine("System.exit(0)");
		System.exit(0);

	}

}
