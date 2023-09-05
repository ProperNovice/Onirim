package gameStates;

import cards.Card;
import cards.CardLabyrinthChamber;
import gameStatesDefault.EndGameLost;
import gameStatesDefault.EndGameWon;
import gameStatesDefault.GameState;
import models.ModelCard;
import models.ModelStatistics;
import utils.Animation;
import utils.Flow;

public class DrawNewHand extends GameState {

	@Override
	public void execute() {

		if (ModelCard.INSTANCE.gameIsWon()) {

			Flow.INSTANCE.executeGameState(EndGameWon.class);
			return;

		}

		while (!getListsManager().hand.getArrayList().isMaxCapacity()) {

			ModelStatistics.INSTANCE.update();

			if (getListsManager().deck.getArrayList().isEmpty()) {

				Flow.INSTANCE.executeGameState(EndGameLost.class);
				return;

			}

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
