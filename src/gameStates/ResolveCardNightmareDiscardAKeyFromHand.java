package gameStates;

import cards.Card;
import cards.CardLabyrinthChamber;
import enums.EColor;
import gameStatesDefault.GameState;
import models.CardModel;
import utils.ArrayList;
import utils.Flow;

public class ResolveCardNightmareDiscardAKeyFromHand extends GameState {

	@Override
	public void execute() {

		ArrayList<CardLabyrinthChamber> listKeys = CardModel.INSTANCE.getKeysInHand();
		ArrayList<EColor> listDifferentColors = new ArrayList<>();

		for (CardLabyrinthChamber cardLabyrinthChamber : listKeys)
			if (!listDifferentColors.contains(cardLabyrinthChamber.getEColor()))
				listDifferentColors.addLast(cardLabyrinthChamber.getEColor());

		if (listDifferentColors.size() == 1)
			handleCardIconDiscardPressed(listKeys.getFirst());

		else {

			for (CardLabyrinthChamber cardLabyrinthChamber : listKeys)
				cardLabyrinthChamber.setIconDiscard();

		}

	}

	@Override
	public void handleCardIconDiscardPressed(Card card) {

		CardModel.INSTANCE.releaseIconsFromList(getListsManager().hand);
		CardModel.INSTANCE.transferCardFromHandToDiscardPileAnimateAsynchronous(card);
		Flow.INSTANCE.executeGameState(DrawCard.class);

	}

}
