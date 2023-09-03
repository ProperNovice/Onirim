package gameStates;

import cards.Card;
import cards.CardDoor;
import cards.CardDreamNightmare;
import cards.CardLabyrinth;
import cards.CardLabyrinthChamber;
import gameStatesDefault.GameState;
import models.ModelCard;
import utils.Flow;

public class DrawCard extends GameState {

	@Override
	public void execute() {

		Card card = ModelCard.INSTANCE.transferOneCardFromDeckToDrawAnimateSynchronousLock();
		Class<? extends Card> cardClass = card.getClass();

		if (CardLabyrinth.class.isAssignableFrom(cardClass))
			handleCardLabyrinthDrawn();
		else if (CardDoor.class.isAssignableFrom(cardClass))
			handleCardDoorDrawn((CardDoor) card);
		else if (CardDreamNightmare.class.isAssignableFrom(cardClass))
			handleCardNightmareDrawn();

	}

	private void handleCardLabyrinthDrawn() {

		ModelCard.INSTANCE.transferCardFromDrawToHandAnimateAsynchronous();
		Flow.INSTANCE.executeGameState(DrawCard.class);

	}

	private void handleCardDoorDrawn(CardDoor cardDoor) {

		for (CardLabyrinthChamber cardLabyrinthChamber : ModelCard.INSTANCE.getKeysInHand()) {

			if (!cardLabyrinthChamber.getEColor().equals(cardDoor.getEColor()))
				continue;

			Flow.INSTANCE.executeGameState(ObtainDoorUsingKey.class);
			return;

		}

		ModelCard.INSTANCE.transferCardFromDrawToLimboAnimateAsynchronous();
		Flow.INSTANCE.executeGameState(DrawCard.class);

	}

	private void handleCardNightmareDrawn() {
		Flow.INSTANCE.executeGameState(ResolveCardNightmare.class);
	}

}
