package gameStates;

import cards.Card;
import enums.EText;
import gameStatesDefault.GameState;
import models.ModelCard;
import models.ModelStatistics;
import utils.Flow;

public class StartGame extends GameState {

	@Override
	public void execute() {

		getListsManager().loadListsOriginal();

		ModelStatistics.INSTANCE.update();

		for (Card card : getListsManager().deck) {

			card.getImageView().flipBack();
			card.releaseIconPlay();
			card.releaseIconDiscard();

		}

		getListsManager().deck.relocateImageViews();
		getListsManager().doors.clearDoors();

		EText.START_GAME.show();

	}

	@Override
	protected void executeTextOption(EText eText) {

		ModelCard.INSTANCE.shuffleDeck();
		Flow.INSTANCE.executeGameState(DrawNewHand.class);

	}

}
