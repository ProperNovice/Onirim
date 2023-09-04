package gameStates;

import cards.Card;
import cards.CardDoor;
import cards.CardDreamNightmare;
import cards.CardLabyrinth;
import cards.CardLabyrinthChamber;
import gameStatesDefault.EndGameLost;
import gameStatesDefault.GameState;
import models.ModelCard;
import utils.Animation;
import utils.Flow;

public class DrawCard extends GameState {

	@Override
	public void execute() {

		if (getListsManager().hand.getArrayList().isMaxCapacity()) {

			Animation.INSTANCE.moveAsynchronousToSynchronous();
			ModelCard.INSTANCE.transferCardsFromLimboToDeckAnimateSynchronous();
			ModelCard.INSTANCE.shuffleDeck();
			Flow.INSTANCE.executeGameState(PlayCard.class);

		} else if (getListsManager().deck.getArrayList().isEmpty())
			Flow.INSTANCE.executeGameState(EndGameLost.class);

		else {

			Card card = ModelCard.INSTANCE.transferOneCardFromDeckToDrawAnimateSynchronousLock();
			Class<? extends Card> cardClass = card.getClass();

			if (CardLabyrinth.class.isAssignableFrom(cardClass))
				handleCardLabyrinthDrawn();
			else if (CardDoor.class.isAssignableFrom(cardClass))
				handleCardDoorDrawn((CardDoor) card);
			else if (CardDreamNightmare.class.isAssignableFrom(cardClass))
				handleCardNightmareDrawn();

		}

	}

	private void handleCardLabyrinthDrawn() {

		ModelCard.INSTANCE.transferCardFromDrawToHandRelocate();
		Flow.INSTANCE.executeGameState(DrawCard.class);

	}

	private void handleCardDoorDrawn(CardDoor cardDoor) {

		for (CardLabyrinthChamber cardLabyrinthChamber : ModelCard.INSTANCE.getKeysInHand()) {

			if (!cardLabyrinthChamber.getEColor().equals(cardDoor.getEColor()))
				continue;

			Flow.INSTANCE.executeGameState(ObtainDoorUsingKey.class);
			return;

		}

		ModelCard.INSTANCE.transferCardFromDrawToLimboRelocate();
		Flow.INSTANCE.executeGameState(DrawCard.class);

	}

	private void handleCardNightmareDrawn() {
		Flow.INSTANCE.executeGameState(ResolveCardNightmare.class);
	}

}
