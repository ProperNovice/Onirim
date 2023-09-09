package utils;

import gui.InstancesGui;
import javafx.scene.Cursor;
import javafx.scene.Scene;

public enum CursorFX {

	INSTANCE;

	private Scene scene = InstancesGui.INSTANCE.getScene();
	private Cursor cursor = Cursor.DEFAULT;

	private CursorFX() {

	}

	public void setWait() {

		if (this.cursor.equals(Cursor.WAIT))
			return;

		setCursor(Cursor.WAIT);

	}

	public void setDefault() {

		if (this.cursor.equals(Cursor.DEFAULT))
			return;

		setCursor(Cursor.DEFAULT);

	}

	private void setCursor(Cursor cursor) {

		this.cursor = cursor;
		this.scene.setCursor(this.cursor);

	}

}
