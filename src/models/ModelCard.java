package models;

import cards.Card;
import cards.CardDoor;
import cards.CardDreamNightmare;
import cards.CardLabyrinthChamber;
import enums.EColor;
import enums.ESubType;
import managers.Credentials;
import managers.ListsManager;
import utils.Animation;
import utils.ArrayList;
import utils.Enums.AnimationSynchEnum;
import utils.ListImageViewAbles;
import utils.Lock;
import utils.Sleep;
import utils.Vector2;

public enum ModelCard {

	INSTANCE;

	private ModelCard() {

	}

	public Card transferOneCardFromDeckToDrawAnimateSynchronousLock() {

		transferCardsFromDeckToDrawAnimateSynchronousLock(1);
		return ListsManager.INSTANCE.draw.getArrayList().getLast();

	}

	public void transferCardsFromDeckToDrawAnimateSynchronousLock(int value) {

		for (int counter = 1; counter <= value; counter++) {

			Card card = ListsManager.INSTANCE.deck.getArrayList().removeFirst();
			card.getImageView().flipFront();
			ListsManager.INSTANCE.draw.getArrayList().addLast(card);

			if (value == 1)
				ListsManager.INSTANCE.draw.animateSynchronous();
			else
				ListsManager.INSTANCE.draw.relocateImageViews();

		}

		Lock.INSTANCE.lock();

		if (value == 1)
			Sleep.INSTANCE.sleep(100);

	}

	public void transferCardFromDrawToHandRelocate() {

		Card card = ListsManager.INSTANCE.draw.getArrayList().removeFirst();
		ListsManager.INSTANCE.hand.getArrayList().addLast(card);
		ListsManager.INSTANCE.hand.relocateImageViews();

	}

	public void transferCardFromDrawToLimboRelocate() {

		Card card = ListsManager.INSTANCE.draw.getArrayList().removeFirst();
		ListsManager.INSTANCE.limbo.getArrayList().addFirst(card);
		ListsManager.INSTANCE.limbo.relocateImageViews();

	}

	public void transferCardsFromLimboToDeckAnimateSynchronous() {

		ArrayList<Card> limbo = ListsManager.INSTANCE.limbo.getArrayList().clear();

		limbo.reverse();

		for (Card card : limbo) {

			Vector2 vector2 = card.getImageView().getCoordinatesCenter();
			vector2.substractX(Credentials.INSTANCE.dCard.x);
			vector2.substractX(Credentials.INSTANCE.dGapBetweenComponents.x);
			Animation.INSTANCE.animateCenter(card, vector2, AnimationSynchEnum.SYNCHRONOUS);

		}

		Lock.INSTANCE.lock();

		ListsManager.INSTANCE.deck.getArrayList().addAllFirst(limbo);
		ListsManager.INSTANCE.deck.relocateImageViews();

		for (Card card : ListsManager.INSTANCE.deck)
			if (!card.getImageView().isFlippedBack())
				card.getImageView().flip();

	}

	public void shuffleDeck() {

		ListsManager.INSTANCE.deck.getArrayList().shuffle();
		ListsManager.INSTANCE.deck.layerZSort();

	}

	public void transferCardFromHandToDiscardPileRelocate(Card card) {

		ListsManager.INSTANCE.hand.getArrayList().remove(card);
		ListsManager.INSTANCE.discardPile.getArrayList().addFirst(card);

		ListsManager.INSTANCE.hand.relocateImageViews();
		ListsManager.INSTANCE.discardPile.relocateImageViews();

	}

	public void transferCardFromDrawToDiscardPileRelocate(Card card) {

		ListsManager.INSTANCE.draw.getArrayList().remove(card);
		ListsManager.INSTANCE.discardPile.getArrayList().addFirst(card);

		ListsManager.INSTANCE.discardPile.relocateImageViews();
		ListsManager.INSTANCE.draw.relocateImageViews();

	}

	public void transferCardFromDrawToDiscardPileRelocate() {

		Card card = ListsManager.INSTANCE.draw.getArrayList().removeFirst();
		ListsManager.INSTANCE.discardPile.getArrayList().addFirst(card);

		ListsManager.INSTANCE.discardPile.relocateImageViews();

	}

	public void transferCardFromDrawToDeckRelocate(Card card) {

		card.getImageView().flip();

		ListsManager.INSTANCE.draw.getArrayList().remove(card);
		ListsManager.INSTANCE.deck.getArrayList().addFirst(card);
		ListsManager.INSTANCE.deck.relocateImageViews();
		ListsManager.INSTANCE.draw.relocateImageViews();

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
		ListsManager.INSTANCE.doors.addCardDoor((CardDoor) card);

		shuffleDeck();

	}

	public void transferCardFromDrawToDoorsShuffleDeck() {

		Card card = ListsManager.INSTANCE.draw.getArrayList().removeFirst();
		ListsManager.INSTANCE.doors.addCardDoor((CardDoor) card);

	}

	public void transferCardFromHandToBoardHandleDoorDiscovered(Card card) {

		ListsManager.INSTANCE.hand.getArrayList().remove(card);
		ListsManager.INSTANCE.board.getArrayList().addFirst(card);

		ListsManager.INSTANCE.hand.relocateImageViews();
		ListsManager.INSTANCE.board.relocateImageViews();

		// check for door

		CardLabyrinthChamber cardLabyrinthChamberPlayed = (CardLabyrinthChamber) card;
		EColor eColorPlayed = cardLabyrinthChamberPlayed.getEColor();

		int consecutiveCardsWithSameColour = 0;

		for (Card cardTemp : ListsManager.INSTANCE.board) {

			CardLabyrinthChamber cardLabyrinthChamberTemp = (CardLabyrinthChamber) cardTemp;
			EColor eColorTemp = cardLabyrinthChamberTemp.getEColor();

			if (eColorPlayed.equals(eColorTemp))
				consecutiveCardsWithSameColour++;
			else
				break;

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

	public ArrayList<CardLabyrinthChamber> getKeysInHand() {

		ArrayList<CardLabyrinthChamber> list = new ArrayList<>();

		for (Card card : ListsManager.INSTANCE.hand) {

			if (!(card instanceof CardLabyrinthChamber))
				continue;

			CardLabyrinthChamber cardLabyrinthChamber = (CardLabyrinthChamber) card;

			if (!cardLabyrinthChamber.getESubType().equals(ESubType.KEY))
				continue;

			list.addLast(cardLabyrinthChamber);

		}

		return list;

	}

	public void transferCardFromDoorsToLimboRelocate(CardDoor cardDoor) {

		ListsManager.INSTANCE.doors.removeDoor(cardDoor);
		ListsManager.INSTANCE.limbo.getArrayList().addFirst(cardDoor);
		ListsManager.INSTANCE.limbo.relocateImageViews();

	}

	public boolean gameIsWon() {

		boolean gameWon = false;

		if (ListsManager.INSTANCE.doors.getDoors().size() == 8) {

			ArrayList<Card> list = new ArrayList<>();
			list.addAllLast(ListsManager.INSTANCE.deck.getArrayList());
			list.addAllLast(ListsManager.INSTANCE.draw.getArrayList());
			list.addAllLast(ListsManager.INSTANCE.limbo.getArrayList());

			gameWon = true;

			for (Card card : list)
				if (card instanceof CardDreamNightmare)
					gameWon = false;

		}

		return gameWon;

	}

}
