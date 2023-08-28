package managers;

import cards.Card;

public enum CardModel {

	INSTANCE;

	private CardModel() {

	}

	public Card transferCardFromDeckToDrawAnimateSynchronousLock() {

		Card card = ListsManager.INSTANCE.deck.getArrayList().removeFirst();
		card.getImageView().flipFront();
		ListsManager.INSTANCE.draw.getArrayList().addLast(card);
		ListsManager.INSTANCE.draw.animateSynchronousLock();

		return card;

	}

}
