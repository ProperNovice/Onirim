package gameStates;

import cards.Card;
import cards.CardLabyrinthChamber;
import gameStatesDefault.GameState;
import managers.CardModel;
import utils.Animation;

public class DrawNewHand extends GameState {

	@Override
	public void execute() {

		while (!getListsManager().hand.getArrayList().isMaxCapacity()) {

			Card card = CardModel.INSTANCE.transferOneCardFromDeckToDrawAnimateSynchronousLock();

			if (card instanceof CardLabyrinthChamber)
				CardModel.INSTANCE.transferCardFromDrawToHandAnimateAsynchronous();
			else
				CardModel.INSTANCE.transferCardFromDrawToLimboAnimateAsynchronous();

		}

		Animation.INSTANCE.moveAsynchronousToSynchronousLock();

		if (!getListsManager().limbo.getArrayList().isEmpty()) {

			CardModel.INSTANCE.transferCardsFromLimboToDeckAnimateSynchronousLock();
			CardModel.INSTANCE.shuffleDeck();

		}

	}

}
