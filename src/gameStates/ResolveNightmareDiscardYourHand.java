package gameStates;

import cards.Card;
import gameStatesDefault.GameState;
import models.ModelCard;
import utils.Flow;

public class ResolveNightmareDiscardYourHand extends GameState {

	@Override
	public void execute() {

		for (Card card : getListsManager().hand.getArrayList().clone())
			ModelCard.INSTANCE.transferCardFromHandToDiscardPileRelocate(card);

		Flow.INSTANCE.executeGameState(DrawNewHand.class);

	}

}
