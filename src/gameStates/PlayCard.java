package gameStates;

import cards.Card;
import cards.CardLabyrinthChamber;
import enums.ESubType;
import gameStatesDefault.GameState;
import models.ModelCard;
import utils.Flow;

public class PlayCard extends GameState {

	@Override
	public void execute() {

		setUpCardsCanBePlayed();
		setUpCardsCanBeDiscarded();

	}

	@Override
	public void handleCardIconPlayPressed(Card card) {

		ModelCard.INSTANCE.releaseIconsFromList(getListsManager().hand);
		ModelCard.INSTANCE.transferCardFromHandToBoardHandleDoorDiscovered(card);

		Flow.INSTANCE.executeGameState(DrawCard.class);

	}

	@Override
	public void handleCardIconDiscardPressed(Card card) {

		ModelCard.INSTANCE.releaseIconsFromList(getListsManager().hand);
		ModelCard.INSTANCE.transferCardFromHandToDiscardPileRelocate(card);

		CardLabyrinthChamber cardLabyrinthChamber = (CardLabyrinthChamber) card;
		ESubType eSubType = cardLabyrinthChamber.getESubType();

		if (eSubType.equals(ESubType.KEY))
			Flow.INSTANCE.executeGameState(TriggerProphecy.class);
		else
			Flow.INSTANCE.executeGameState(DrawCard.class);

	}

	private void setUpCardsCanBePlayed() {

		if (getListsManager().board.getArrayList().isEmpty()) {

			for (Card card : getListsManager().hand)
				card.setIconPlay();

			return;

		}

		Card cardBoard = getListsManager().board.getArrayList().getFirst();
		CardLabyrinthChamber cardLabyrinthChamberBoard = (CardLabyrinthChamber) cardBoard;
		ESubType eSubTypeBoard = cardLabyrinthChamberBoard.getESubType();

		for (Card cardHand : getListsManager().hand) {

			CardLabyrinthChamber cardLabyrinthChamberHand = (CardLabyrinthChamber) cardHand;
			ESubType eSubTypeHand = cardLabyrinthChamberHand.getESubType();

			if (!eSubTypeHand.equals(eSubTypeBoard))
				cardHand.setIconPlay();

		}

	}

	private void setUpCardsCanBeDiscarded() {

		for (Card card : getListsManager().hand)
			card.setIconDiscard();

	}

}
