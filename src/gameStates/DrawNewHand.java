package gameStates;

import cards.Card;
import cards.CardLabyrinthChamber;
import gameStatesDefault.GameState;
import models.ModelCard;
import utils.Animation;
import utils.Flow;

public class DrawNewHand extends GameState {

	@Override
	public void execute() {

		while (!getListsManager().hand.getArrayList().isMaxCapacity()) {

			Card card = ModelCard.INSTANCE.transferOneCardFromDeckToDrawAnimateSynchronousLock();

			if (card instanceof CardLabyrinthChamber)
				ModelCard.INSTANCE.transferCardFromDrawToHandAnimateAsynchronous();
			else
				ModelCard.INSTANCE.transferCardFromDrawToLimboAnimateAsynchronous();

		}

		Animation.INSTANCE.moveAsynchronousToSynchronousLock();

		if (!getListsManager().limbo.getArrayList().isEmpty()) {

			ModelCard.INSTANCE.transferCardsFromLimboToDeckAnimateSynchronousLock();
			ModelCard.INSTANCE.shuffleDeck();

		}

		Flow.INSTANCE.executeGameState(PlayCard.class);

	}

}
