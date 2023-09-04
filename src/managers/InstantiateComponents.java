package managers;

import cards.Card;
import cards.CardDoor;
import cards.CardDreamNightmare;
import cards.CardLabyrinthChamber;
import enums.EColor;
import enums.ESubType;
import models.ModelStatistics;
import utils.ArrayList;

public enum InstantiateComponents {

	INSTANCE;

	private InstantiateComponents() {

		ModelStatistics.values();
		createDeck();

	}

	private void createDeck() {

		ArrayList<Card> deck = ListsManager.INSTANCE.deck.getArrayList();

		for (int counter = 1; counter <= 9; counter++)
			deck.addLast(new CardLabyrinthChamber(EColor.RED, ESubType.SUN));

		for (int counter = 1; counter <= 8; counter++)
			deck.addLast(new CardLabyrinthChamber(EColor.BLUE, ESubType.SUN));

		for (int counter = 1; counter <= 7; counter++)
			deck.addLast(new CardLabyrinthChamber(EColor.GREEN, ESubType.SUN));

		for (int counter = 1; counter <= 6; counter++)
			deck.addLast(new CardLabyrinthChamber(EColor.BROWN, ESubType.SUN));

		for (int counter = 1; counter <= 4; counter++)
			for (EColor eColor : EColor.values())
				deck.addLast(new CardLabyrinthChamber(eColor, ESubType.MOON));

		for (int counter = 1; counter <= 3; counter++)
			for (EColor eColor : EColor.values())
				deck.addLast(new CardLabyrinthChamber(eColor, ESubType.KEY));

		for (int counter = 1; counter <= 2; counter++)
			for (EColor eColor : EColor.values())
				deck.addLast(new CardDoor(eColor));

		for (int counter = 1; counter <= 10; counter++)
			deck.addLast(new CardDreamNightmare());

		deck.shuffle();
		ListsManager.INSTANCE.deck.relocateImageViews();

		for (Card card : ListsManager.INSTANCE.deck)
			card.getImageView().flip();

	}

}
