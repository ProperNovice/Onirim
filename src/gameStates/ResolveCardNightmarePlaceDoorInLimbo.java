package gameStates;

import cards.Card;
import cards.CardDoor;
import gameStatesDefault.GameState;
import models.ModelCard;
import utils.ArrayList;
import utils.Flow;

public class ResolveCardNightmarePlaceDoorInLimbo extends GameState {

	@Override
	public void execute() {

		ArrayList<CardDoor> listDoors = getListsManager().doors.getDoors();

		if (listDoors.size() == 1)
			handleCardIconPlayPressed(listDoors.getFirst());
		else
			for (CardDoor cardDoor : listDoors)
				cardDoor.setIconPlay();

	}

	@Override
	public void handleCardIconPlayPressed(Card card) {

		for (CardDoor cardDoor : getListsManager().doors.getDoors())
			cardDoor.releaseIconPlay();

		ModelCard.INSTANCE.transferCardFromDoorsToDeckShuffleDeckRelocate((CardDoor) card);
		Flow.INSTANCE.executeGameState(DrawCard.class);

	}

}
