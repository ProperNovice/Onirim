package gameStates;

import cards.Card;
import cards.CardDoor;
import gameStatesDefault.EndGameLost;
import gameStatesDefault.GameState;
import models.CardModel;
import utils.ArrayList;
import utils.Flow;

public class TriggerProphecy extends GameState {

	@Override
	public void execute() {

		int cardsToDraw = Math.min(getListsManager().deck.getArrayList().size(), 5);

		if (cardsToDraw == 0)
			proceed();

		else {

			CardModel.INSTANCE.transferCardFromDeckToDrawAnimateSynchronousLock(cardsToDraw);
			setUpDiscard();

		}

	}

	@Override
	public void handleCardIconDiscardPressed(Card card) {

		CardModel.INSTANCE.releaseIconsFromList(getListsManager().draw);
		CardModel.INSTANCE.transferCardFromDrawToDiscardPileAnimateSynchronousLock(card);

		setUpPutCardOnTopOfTheDeck();

	}

	@Override
	public void handleCardIconPlayPressed(Card card) {

		CardModel.INSTANCE.releaseIconsFromList(getListsManager().draw);
		CardModel.INSTANCE.transferCardFromDrawToDeckAnimateSynchronous(card);

		setUpPutCardOnTopOfTheDeck();

	}

	private void setUpDiscard() {

		ArrayList<Card> cardsCanBeDestroyed = new ArrayList<>();

		for (Card card : getListsManager().draw)
			if (!(card instanceof CardDoor))
				cardsCanBeDestroyed.addLast(card);

		if (cardsCanBeDestroyed.isEmpty())
			Flow.INSTANCE.executeGameState(EndGameLost.class);

		for (Card card : cardsCanBeDestroyed)
			card.setIconDiscard();

	}

	private void setUpPutCardOnTopOfTheDeck() {

		if (getListsManager().draw.getArrayList().isEmpty())
			proceed();

		else if (getListsManager().draw.getArrayList().size() == 1)
			handleCardIconPlayPressed(getListsManager().draw.getArrayList().getFirst());

		else
			for (Card card : getListsManager().draw)
				card.setIconPlay();

	}

	private void proceed() {
		Flow.INSTANCE.executeGameState(DrawCard.class);
	}

}
