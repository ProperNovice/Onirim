package gameStates;

import gameStatesDefault.GameState;
import managers.CardModel;

public class DrawNewHand extends GameState {

	@Override
	public void execute() {

		for (int counter = 1; counter <= 5; counter++) {
			CardModel.INSTANCE.transferCardFromDeckToDrawAnimateSynchronousLock();
			System.out.println("a");
		}

	}

}
