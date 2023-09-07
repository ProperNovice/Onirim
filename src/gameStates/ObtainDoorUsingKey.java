package gameStates;

import cards.Card;
import cards.CardDoor;
import cards.CardLabyrinthChamber;
import enums.ESubType;
import enums.EText;
import gameStatesDefault.GameState;
import models.ModelCard;
import utils.Flow;

public class ObtainDoorUsingKey extends GameState {

	@Override
	public void execute() {

		Card card = getListsManager().draw.getArrayList().getFirst();

		card.setIconDiscard();
		card.setIconPlay();

		EText.OBTAIN_DOOR.show();
		EText.PUT_IT_IN_LIMBO.show();

	}

	@Override
	protected void executeTextOption(EText eText) {

		switch (eText) {

		case OBTAIN_DOOR:
			handleCardIconPlayPressed(getListsManager().draw.getArrayList().getFirst());
			break;

		case PUT_IT_IN_LIMBO:
			handleCardIconDiscardPressed(getListsManager().draw.getArrayList().getFirst());
			break;

		default:
			break;

		}

	}

	@Override
	public void handleCardIconPlayPressed(Card cardPressed) {

		concealText();

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

			ModelCard.INSTANCE.transferCardFromHandToDiscardPileRelocate(card);
			break;

		}

		// remove door

		ModelCard.INSTANCE.releaseIconsFromList(getListsManager().draw);
		ModelCard.INSTANCE.transferCardFromDrawToDoorsShuffleDeck();

		Flow.INSTANCE.executeGameState(DrawCard.class);

	}

	@Override
	public void handleCardIconDiscardPressed(Card card) {

		concealText();

		ModelCard.INSTANCE.releaseIconsFromList(getListsManager().draw);
		ModelCard.INSTANCE.transferCardFromDrawToLimboRelocate();
		Flow.INSTANCE.executeGameState(DrawCard.class);

	}

}
