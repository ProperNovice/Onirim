package utils;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Audio {

	private URL resource = null;
	private Media media = null;
	private MediaPlayer mediaPlayer = null;

	public Audio(String path) {

		String finalPath = "/mp3/" + path + ".mp3";

		this.resource = getClass().getResource(finalPath);
		Logger.INSTANCE.log(this.resource.toString());
		this.media = new Media(this.resource.toString());
		this.mediaPlayer = new MediaPlayer(this.media);

	}

	public void play() {
		this.mediaPlayer.play();
	}

	public void stop() {
		this.mediaPlayer.stop();
	}

}
