package gui;

import javafx.scene.Scene;

public enum InstancesGui {

	INSTANCE;

	private Parent parent = null;
	private Scene scene = null;

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Parent getParent() {
		return this.parent;
	}

	public Scene getScene() {
		return this.scene;
	}

}
