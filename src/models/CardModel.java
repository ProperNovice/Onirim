package models;

import cards.Card;
import cards.CardDoor;
import cards.CardLabyrinthChamber;
import enums.EColor;
import managers.ListsManager;
import utils.ArrayList;
import utils.ListImageViewAbles;
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

	public void transferCardFromHandToDiscardPileAnimateAsynchronous(Card card) {

		ListsManager.INSTANCE.hand.getArrayList().remove(card);
		ListsManager.INSTANCE.discardPile.getArrayList().addFirst(card);

		ListsManager.INSTANCE.hand.animateAsynchronous();
		ListsManager.INSTANCE.discardPile.relocateImageViews();

	}

	public void transferCardFromDrawToDiscardPileAnimateSynchronousLock(Card card) {

		ListsManager.INSTANCE.draw.getArrayList().remove(card);
		ListsManager.INSTANCE.discardPile.getArrayList().addFirst(card);

		ListsManager.INSTANCE.discardPile.relocateImageViews();
		ListsManager.INSTANCE.draw.animateSynchronousLock();

	}

	public void transferCardFromDrawToDeckAnimateSynchronous(Card card) {

		card.getImageView().flip();

		ListsManager.INSTANCE.draw.getArrayList().remove(card);
		ListsManager.INSTANCE.deck.getArrayList().addFirst(card);
		ListsManager.INSTANCE.deck.relocateImageViews();

		ListsManager.INSTANCE.draw.animateSynchronousLock();

	}

	public void releaseIconsFromList(ListImageViewAbles<Card> list) {

		for (Card card : list) {

			card.releaseIconPlay();
			card.releaseIconDiscard();

		}

	}

	public void transferCardFromDeckToDoorsShuffleDeck(Card card) {

		card.getImageView().flip();

		ListsManager.INSTANCE.deck.getArrayList().remove(card);
		ListsManager.INSTANCE.doors.addCardDoorAnimateAsynchronous((CardDoor) card);

		shuffleDeck();

	}

	public void transferCardFromDrawToDoorsShuffleDeck() {

		Card card = ListsManager.INSTANCE.draw.getArrayList().removeFirst();
		ListsManager.INSTANCE.doors.addCardDoorAnimateAsynchronous((CardDoor) card);

	}

	public void transferCardFromHandToBoardHandleDoorDiscovered(Card card) {

		ListsManager.INSTANCE.hand.getArrayList().remove(card);
		ListsManager.INSTANCE.board.getArrayList().addFirst(card);

		ListsManager.INSTANCE.hand.animateAsynchronous();
		ListsManager.INSTANCE.board.animateAsynchronous();

		// check for door

		CardLabyrinthChamber cardLabyrinthChamberPlayed = (CardLabyrinthChamber) card;
		EColor eColorPlayed = cardLabyrinthChamberPlayed.getEColor();

		int consecutiveCardsWithSameColour = 0;

		for (Card cardTemp : ListsManager.INSTANCE.board) {

			CardLabyrinthChamber cardLabyrinthChamberTemp = (CardLabyrinthChamber) cardTemp;
			EColor eColorTemp = cardLabyrinthChamberTemp.getEColor();

			if (eColorPlayed.equals(eColorTemp))
				consecutiveCardsWithSameColour++;

		}

		if (consecutiveCardsWithSameColour % 3 > 0)
			return;

		for (Card cardTemp : ListsManager.INSTANCE.deck) {

			if (!(cardTemp instanceof CardDoor))
				continue;

			CardDoor cardDoor = (CardDoor) cardTemp;
			EColor eColor = cardDoor.getEColor();

			if (!eColor.equals(eColorPlayed))
				continue;

			transferCardFromDeckToDoorsShuffleDeck(cardTemp);
			return;

		}

	}

}
