package gameStates;

import cards.Card;
import cards.CardLabyrinthChamber;
import enums.ESubType;
import gameStatesDefault.GameState;

public class PlayCard extends GameState {

	@Override
	public void execute() {

		setUpCardsCanBePlayed();

	}

	@Override
	public void handleCardIconPlayPressed(Card card) {

	}

	@Override
	public void handleCardIconDiscardPressed(Card card) {

	}

	private void setUpCardsCanBePlayed() {

		if (getListsManager().board.getArrayList().isEmpty()) {

			for (Card card : getListsManager().hand)
				card.setIconPlay();

			return;

		}

		Card cardBoard = getListsManager().board.getArrayList().getLast();
		CardLabyrinthChamber cardLabyrinthChamberBoard = (CardLabyrinthChamber) cardBoard;
		ESubType eSubTypeBoard = cardLabyrinthChamberBoard.getESubType();

		for (Card cardHand : getListsManager().hand) {

			if (!(cardHand instanceof CardLabyrinthChamber))
				continue;

			CardLabyrinthChamber cardLabyrinthChamberHand = (CardLabyrinthChamber) cardHand;
			ESubType eSubTypeHand = cardLabyrinthChamberHand.getESubType();

			if (!eSubTypeHand.equals(eSubTypeBoard))
				cardHand.setIconPlay();

		}

	}

}
