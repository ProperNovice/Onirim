package gui;

import gameStates.JUnit;
import gameStatesDefault.RestartGame;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import managers.Credentials;
import managers.InstantiateComponents;
import managers.ListsManager;
import utils.Animation;
import utils.Flow;
import utils.Logger;
import utils.ObjectPool;
import utils.ShutDown;
import utils.TextManager;
import utils.Vector2;

public class Onirim extends Application {

	private Vector2 dimensionsInsets = new Vector2(16, 39);
	private double pixesOnTheLeft = 180;

	private void start() {

		ListsManager.INSTANCE.instantiate();
		TextManager.values();

		long start = System.currentTimeMillis();
		Logger.INSTANCE.log("instantiating imageviews");

		InstantiateComponents.values();

		long end = System.currentTimeMillis();
		Logger.INSTANCE.logNewLine((end - start) + "ms");

		Flow.INSTANCE.executeGameState(JUnit.class);

	}

	private void logJavaVersion() {

		Logger.INSTANCE.log("Java -> " + System.getProperty("java.version"));
		Logger.INSTANCE.log("JavaFX -> " + System.getProperty("javafx.version"));
		Logger.INSTANCE.newLine();

	}

	private void setKeyPressed(Scene scene) {

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				KeyCode keyCode = event.getCode();

				Logger.INSTANCE.logNewLine(keyCode + " key code pressed");

				if (keyCode.equals(KeyCode.ESCAPE))
					ShutDown.INSTANCE.execute();

				else if (Animation.INSTANCE.isAnimatingSynchronous())
					return;

				else if (keyCode.equals(KeyCode.BACK_QUOTE))
					Flow.INSTANCE.executeGameState(RestartGame.class);

				else if (keyCode.equals(KeyCode.O))
					ObjectPool.INSTANCE.print();

				else
					Flow.INSTANCE.getGameStateCurrent().executeKeyPressed(keyCode);

			}

		});

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		logJavaVersion();

		Panel panel = new Panel();

		double width = Credentials.INSTANCE.dFrame.x + this.dimensionsInsets.x;
		double height = Credentials.INSTANCE.dFrame.y + this.dimensionsInsets.y;

		Scene scene = new Scene(panel);
		InstancesGui.INSTANCE.setScene(scene);
		setKeyPressed(scene);

		primaryStage.setScene(scene);
		primaryStage.setWidth(width);
		primaryStage.setHeight(height);
		primaryStage.setResizable(false);

		primaryStage.setTitle(Credentials.INSTANCE.primaryStageTitle);

		primaryStage.setX(
				(Screen.getPrimary().getBounds().getWidth() - width) / 2 - this.pixesOnTheLeft);

		double y = 0;
		y += Screen.getPrimary().getBounds().getHeight();
		y -= 72;
		y -= Credentials.INSTANCE.dFrame.y;
		y /= 2;

		primaryStage.setY(y);

//		if (Credentials.INSTANCE.dFrame.y == 1366)
//			primaryStage.setY(0);
//		else
//			primaryStage.setY((Screen.getPrimary().getBounds().getHeight() - height) / 2);

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				ShutDown.INSTANCE.execute();
			}

		});

		primaryStage.show();

		start();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
