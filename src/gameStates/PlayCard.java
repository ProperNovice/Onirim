package gameStates;

import cards.Card;
import gameStatesDefault.GameState;

public class PlayCard extends GameState {

	@Override
	public void execute() {

		for (Card card : getListsManager().hand) {

			card.setIconPlay();
			card.setIconDiscard();

		}

	}

	@Override
	public void handleCardIconPlayPressed(Card card) {

	}

	@Override
	public void handleCardIconDiscardPressed(Card card) {

	}

}
