package gameStates;

import cards.Card;
import cards.CardDoor;
import cards.CardDreamNightmare;
import cards.CardLabyrinth;
import cards.CardLabyrinthChamber;
import enums.ESubType;
import gameStatesDefault.GameState;
import models.CardModel;
import utils.Flow;

public class DrawCard extends GameState {

	@Override
	public void execute() {

		Card card = CardModel.INSTANCE.transferOneCardFromDeckToDrawAnimateSynchronousLock();
		Class<? extends Card> cardClass = card.getClass();

		if (CardLabyrinth.class.isAssignableFrom(cardClass))
			handleCardLabyrinthDrawn();
		else if (CardDoor.class.isAssignableFrom(cardClass))
			handleCardDoorDrawn((CardDoor) card);
		else if (CardDreamNightmare.class.isAssignableFrom(cardClass))
			handleCardNightmareDrawn();

	}

	private void handleCardLabyrinthDrawn() {

		CardModel.INSTANCE.transferCardFromDrawToHandAnimateAsynchronous();
		Flow.INSTANCE.executeGameState(DrawCard.class);

	}

	private void handleCardDoorDrawn(CardDoor cardDoor) {

		for (Card card : getListsManager().hand) {

			if (!(card instanceof CardLabyrinthChamber))
				continue;

			CardLabyrinthChamber cardLabyrinthChamber = (CardLabyrinthChamber) card;

			if (!cardLabyrinthChamber.getESubType().equals(ESubType.KEY))
				continue;

			if (!cardLabyrinthChamber.getEColor().equals(cardDoor.getEColor()))
				continue;

			Flow.INSTANCE.executeGameState(ObtainDoorUsingKey.class);
			return;

		}

		CardModel.INSTANCE.transferCardFromDrawToLimboAnimateAsynchronous();
		Flow.INSTANCE.executeGameState(DrawCard.class);

	}

	private void handleCardNightmareDrawn() {
		Flow.INSTANCE.executeGameState(ResolveCardNightmare.class);
	}

}
