package gameStates;

import cards.Card;
import cards.CardDoor;
import cards.CardDream;
import gameStatesDefault.GameState;
import models.ModelCard;
import utils.ArrayList;
import utils.Flow;

public class ResolveCardNightmareRevealFirstFiveCards extends GameState {

	@Override
	public void execute() {

		int cardsToReveal = Math.min(getListsManager().deck.getArrayList().size(), 5);

		ModelCard.INSTANCE.transferCardsFromDeckToDrawAnimateSynchronousLock(cardsToReveal);

		ArrayList<Card> cardsToLimbo = new ArrayList<>();
		ArrayList<Card> cardsToDiscardPile = new ArrayList<>();

		for (Card card : getListsManager().draw.getArrayList().clear()) {

			if (card instanceof CardDoor || card instanceof CardDream)
				cardsToLimbo.addLast(card);
			else
				cardsToDiscardPile.addLast(card);

		}

		cardsToLimbo.reverse();
		cardsToDiscardPile.reverse();

		getListsManager().limbo.getArrayList().addAllFirst(cardsToLimbo);
		getListsManager().discardPile.getArrayList().addAllFirst(cardsToDiscardPile);

		getListsManager().limbo.animateSynchronous();
		getListsManager().discardPile.animateSynchronousLock();

		Flow.INSTANCE.executeGameState(DrawCard.class);

	}

}
