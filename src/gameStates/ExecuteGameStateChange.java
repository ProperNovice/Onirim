package gameStates;

import gameStatesDefault.GameState;
import models.ModelStatistics;

public class ExecuteGameStateChange extends GameState {

	@Override
	public void execute() {

		ModelStatistics.INSTANCE.update();

	}

}
