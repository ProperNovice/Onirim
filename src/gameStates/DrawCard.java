package gameStates;

import cards.Card;
import cards.CardDoor;
import cards.CardDreamNightmare;
import cards.CardLabyrinth;
import cards.CardLabyrinthChamber;
import gameStatesDefault.EndGameLost;
import gameStatesDefault.EndGameWon;
import gameStatesDefault.GameState;
import models.ModelCard;
import utils.Animation;
import utils.ArrayList;
import utils.Flow;

public class DrawCard extends GameState {

	@Override
	public void execute() {

		boolean gameWon = false;

		if (getListsManager().doors.getDoors().size() == 8) {

			ArrayList<Card> list = new ArrayList<>();
			list.addAllLast(getListsManager().deck.getArrayList());
			list.addAllLast(getListsManager().draw.getArrayList());
			list.addAllLast(getListsManager().limbo.getArrayList());

			gameWon = true;

			for (Card card : list)
				if (card instanceof CardDreamNightmare)
					gameWon = false;

		}

		if (gameWon)
			Flow.INSTANCE.executeGameState(EndGameWon.class);

		else if (getListsManager().hand.getArrayList().isMaxCapacity()) {

			Animation.INSTANCE.moveAsynchronousToSynchronous();
			ModelCard.INSTANCE.transferCardsFromLimboToDeckAnimateSynchronous();
			ModelCard.INSTANCE.shuffleDeck();
			Flow.INSTANCE.executeGameState(PlayCard.class);

		} else if (getListsManager().deck.getArrayList().isEmpty())
			Flow.INSTANCE.executeGameState(EndGameLost.class);

		else {

			Card card = ModelCard.INSTANCE.transferOneCardFromDeckToDrawAnimateSynchronousLock();
			Class<? extends Card> cardClass = card.getClass();

			if (CardLabyrinth.class.isAssignableFrom(cardClass))
				handleCardLabyrinthDrawn();
			else if (CardDoor.class.isAssignableFrom(cardClass))
				handleCardDoorDrawn((CardDoor) card);
			else if (CardDreamNightmare.class.isAssignableFrom(cardClass))
				handleCardNightmareDrawn();

		}

	}

	private void handleCardLabyrinthDrawn() {

		ModelCard.INSTANCE.transferCardFromDrawToHandRelocate();
		Flow.INSTANCE.executeGameState(DrawCard.class);

	}

	private void handleCardDoorDrawn(CardDoor cardDoor) {

		for (CardLabyrinthChamber cardLabyrinthChamber : ModelCard.INSTANCE.getKeysInHand()) {

			if (!cardLabyrinthChamber.getEColor().equals(cardDoor.getEColor()))
				continue;

			Flow.INSTANCE.executeGameState(ObtainDoorUsingKey.class);
			return;

		}

		ModelCard.INSTANCE.transferCardFromDrawToLimboRelocate();
		Flow.INSTANCE.executeGameState(DrawCard.class);

	}

	private void handleCardNightmareDrawn() {
		Flow.INSTANCE.executeGameState(ResolveCardNightmare.class);
	}

}
