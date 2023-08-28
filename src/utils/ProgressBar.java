package utils;

import javafx.scene.paint.Color;

public class ProgressBar {

	private Rectangle rectangleProgressBar = null;
	private Rectangle rectangleFill = null;
	private double width, height, progress = 0, topLeftX, topLeftY;
	private ProgressBarOrientation progressBarOrientation = null;

	public ProgressBar(double width, double height, ProgressBarOrientation progressBarOrientation) {

		this.progressBarOrientation = progressBarOrientation;

		this.width = width;
		this.height = height;
		this.rectangleProgressBar = new Rectangle(this.width, this.height);

		switch (this.progressBarOrientation) {

		case HORIZONTAL:
			this.rectangleFill = new Rectangle(0, this.height);
			break;

		case VERTICAL:
			this.rectangleFill = new Rectangle(this.width, 0);
			break;

		}

		this.rectangleProgressBar.setStroke(Color.BLUE);
		this.rectangleFill.setFill(Color.BLUE);
		this.rectangleFill.setStroke(Color.BLUE);
		this.rectangleFill.setVisible(false);

	}

	public enum ProgressBarOrientation {
		HORIZONTAL, VERTICAL;
	}

	public void relocate(double x, double y) {

		this.topLeftX = x;
		this.topLeftY = y;

		this.rectangleProgressBar.relocateTopLeft(this.topLeftX, this.topLeftY);

		switch (this.progressBarOrientation) {

		case HORIZONTAL:
			this.rectangleFill.relocateTopLeft(this.topLeftX, this.topLeftY);
			break;

		case VERTICAL:

			double yRectangleFill = this.topLeftY + this.height - (this.height * this.progress);
			this.rectangleFill.relocateTopLeft(this.topLeftX, yRectangleFill);
			break;

		}

	}

	public void setFill(Color color) {

		this.rectangleProgressBar.setStroke(color);
		this.rectangleFill.setFill(color);
		this.rectangleFill.setStroke(color);

	}

	public void setProgress(double progress) {

		this.progress = progress;

		if (this.progress == 0)
			this.rectangleFill.setVisible(false);
		else
			this.rectangleFill.setVisible(true);

		switch (this.progressBarOrientation) {

		case HORIZONTAL:

			double widthFill = this.width * this.progress;
			this.rectangleFill.setWidth(widthFill);

			break;

		case VERTICAL:

			double y = this.topLeftY + this.height - (this.height * this.progress);
			this.rectangleFill.relocateTopLeft(this.topLeftX, y);

			double heightFill = this.height * this.progress;
			this.rectangleFill.setHeight(heightFill);

			break;

		}

	}

	public double getProgress() {
		return this.progress;
	}

}
