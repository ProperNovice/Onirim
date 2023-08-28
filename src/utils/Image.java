package utils;

public class Image {

	private javafx.scene.image.Image image = null;
	private String filePath = "";

	public Image(String filePath) {

		this.filePath = filePath;

		try {

			this.image = new javafx.scene.image.Image("/images/" + this.filePath);

		} catch (Exception e) {

			Logger.INSTANCE.log("could not create image");
			Logger.INSTANCE.logNewLine(this.filePath);
			ShutDown.INSTANCE.execute();

		}

	}

	public String getFilePath() {
		return this.filePath;
	}

	public javafx.scene.image.Image getImageFX() {
		return this.image;
	}

}
