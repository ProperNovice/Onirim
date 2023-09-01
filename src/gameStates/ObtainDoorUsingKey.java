package gameStates;

import cards.Card;
import cards.CardDoor;
import cards.CardLabyrinthChamber;
import enums.ESubType;
import gameStatesDefault.GameState;
import models.CardModel;
import utils.Flow;

public class ObtainDoorUsingKey extends GameState {

	@Override
	public void execute() {

		Card card = getListsManager().draw.getArrayList().getFirst();

		card.setIconDiscard();
		card.setIconPlay();

	}

	@Override
	public void handleCardIconPlayPressed(Card cardPressed) {

		CardModel.INSTANCE.releaseIconsFromList(getListsManager().draw);
		CardModel.INSTANCE.transferCardFromDrawToDoorsShuffleDeck();

		CardDoor cardDoor = (CardDoor) cardPressed;

		// remove key

		for (Card card : getListsManager().hand) {

			if (!(card instanceof CardLabyrinthChamber))
				continue;

			CardLabyrinthChamber cardLabyrinthChamber = (CardLabyrinthChamber) card;

			if (!cardLabyrinthChamber.getESubType().equals(ESubType.KEY))
				continue;

			if (!cardLabyrinthChamber.getEColor().equals(cardDoor.getEColor()))
				continue;

			CardModel.INSTANCE.transferCardFromHandToDiscardPileAnimateAsynchronous(card);
			break;

		}

		Flow.INSTANCE.executeGameState(DrawCard.class);

	}

	@Override
	public void handleCardIconDiscardPressed(Card card) {

		CardModel.INSTANCE.releaseIconsFromList(getListsManager().draw);
		CardModel.INSTANCE.transferCardFromDrawToLimboAnimateAsynchronous();
		Flow.INSTANCE.executeGameState(DrawCard.class);

	}

}
