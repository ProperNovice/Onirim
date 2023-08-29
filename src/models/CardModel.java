package models;

import cards.Card;
import managers.ListsManager;
import utils.ArrayList;
import utils.Lock;

public enum CardModel {

	INSTANCE;

	private CardModel() {

	}

	public Card transferOneCardFromDeckToDrawAnimateSynchronousLock() {

		transferCardFromDeckToDrawAnimateSynchronousLock(1);
		return ListsManager.INSTANCE.draw.getArrayList().getLast();

	}

	public void transferCardFromDeckToDrawAnimateSynchronousLock(int value) {

		for (int counter = 1; counter <= value; counter++) {

			Card card = ListsManager.INSTANCE.deck.getArrayList().removeFirst();
			card.getImageView().flipFront();
			ListsManager.INSTANCE.draw.getArrayList().addLast(card);
			ListsManager.INSTANCE.draw.animateSynchronous();

		}

		Lock.INSTANCE.lock();

	}

	public void transferCardFromDrawToHandAnimateAsynchronous() {

		Card card = ListsManager.INSTANCE.draw.getArrayList().removeFirst();
		ListsManager.INSTANCE.hand.getArrayList().addLast(card);
		ListsManager.INSTANCE.hand.animateAsynchronous();

	}

	public void transferCardFromDrawToLimboAnimateAsynchronous() {

		Card card = ListsManager.INSTANCE.draw.getArrayList().removeFirst();
		ListsManager.INSTANCE.limbo.getArrayList().addFirst(card);
		ListsManager.INSTANCE.limbo.animateAsynchronous();

	}

	public void transferCardsFromLimboToDeckAnimateSynchronousLock() {

		ArrayList<Card> limbo = ListsManager.INSTANCE.limbo.getArrayList().clear();

		ListsManager.INSTANCE.deck.getArrayList().addAllFirst(limbo);
		ListsManager.INSTANCE.deck.animateSynchronousLock();

		for (Card card : ListsManager.INSTANCE.deck)
			if (!card.getImageView().isFlippedBack())
				card.getImageView().flip();

	}

	public void shuffleDeck() {

		ListsManager.INSTANCE.deck.getArrayList().shuffle();
		ListsManager.INSTANCE.deck.layerZSort();

	}

}
