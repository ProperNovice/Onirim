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
				ModelCard.INSTANCE.transferCardFromDrawToHandRelocate();
			else
				ModelCard.INSTANCE.transferCardFromDrawToLimboRelocate();

		}

		Animation.INSTANCE.moveAsynchronousToSynchronousLock();

		if (!getListsManager().limbo.getArrayList().isEmpty()) {

			ModelCard.INSTANCE.transferCardsFromLimboToDeckAnimateSynchronous();
			ModelCard.INSTANCE.shuffleDeck();

		}

		Flow.INSTANCE.executeGameState(PlayCard.class);

	}

}
