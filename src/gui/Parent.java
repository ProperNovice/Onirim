package gui;

import java.util.Arrays;

import javafx.scene.Node;

public class Parent extends javafx.scene.Parent {

	public void addNode(Node... node) {

		this.getChildren().addAll(Arrays.asList(node));

	}

}
