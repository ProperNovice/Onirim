package gameStates;

import cards.Card;
import cards.CardLabyrinthChamber;
import enums.EColor;
import gameStatesDefault.GameState;
import models.ModelCard;
import utils.ArrayList;
import utils.Flow;

public class ResolveCardNightmareDiscardKeyFromHand extends GameState {

	@Override
	public void execute() {

		ArrayList<CardLabyrinthChamber> listKeys = ModelCard.INSTANCE.getKeysInHand();
		ArrayList<EColor> listDifferentColors = new ArrayList<>(1);

		for (CardLabyrinthChamber cardLabyrinthChamber : listKeys)
			if (!listDifferentColors.contains(cardLabyrinthChamber.getEColor()))
				listDifferentColors.addLast(cardLabyrinthChamber.getEColor());

		if (listDifferentColors.isMaxCapacity())
			handleCardIconDiscardPressed(listKeys.getFirst());

		else {

			for (CardLabyrinthChamber cardLabyrinthChamber : listKeys)
				cardLabyrinthChamber.setIconDiscard();

		}

	}

	@Override
	public void handleCardIconDiscardPressed(Card card) {

		ModelCard.INSTANCE.releaseIconsFromList(getListsManager().hand);

		ModelCard.INSTANCE.transferCardFromDrawToDiscardPileRelocate();
		ModelCard.INSTANCE.transferCardFromHandToDiscardPileRelocate(card);

		Flow.INSTANCE.executeGameState(DrawCard.class);

	}

}
