package gameStatesDefault;

import gameStates.StartGame;
import utils.Flow;
import utils.SelectImageViewManager;
import utils.TextManager;

public class RestartGame extends GameState {

	@Override
	public void execute() {

		TextManager.INSTANCE.concealText();
		SelectImageViewManager.INSTANCE.releaseSelectImageViews();
		Flow.INSTANCE.getFlow().clear();

		Flow.INSTANCE.executeGameState(StartGame.class);

	}

}
