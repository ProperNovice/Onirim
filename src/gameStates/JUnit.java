package gameStates;

import cards.Card;
import cards.CardDoor;
import cards.CardDreamNightmare;
import cards.CardLabyrinthChamber;
import enums.EColor;
import enums.ESubType;
import gameStatesDefault.GameState;
import utils.Flow;

public class JUnit extends GameState {

	@Override
	public void execute() {

		addCardToHand(new CardLabyrinthChamber(EColor.BROWN, ESubType.KEY));
//		addCardToHand(new CardLabyrinthChamber(EColor.GREEN, ESubType.MOON));
//		addCardToHand(new CardLabyrinthChamber(EColor.RED, ESubType.SUN));
//		addCardToHand(new CardLabyrinthChamber(EColor.BLUE, ESubType.KEY));
//		addCardToHand(new CardLabyrinthChamber(EColor.RED, ESubType.SUN));

		addCardToDeck(new CardDreamNightmare());
		addCardToDeck(new CardLabyrinthChamber(EColor.BROWN, ESubType.KEY));
		addCardToDeck(new CardLabyrinthChamber(EColor.GREEN, ESubType.MOON));
		addCardToDeck(new CardLabyrinthChamber(EColor.RED, ESubType.SUN));
		addCardToDeck(new CardDoor(EColor.BROWN));
		addCardToDeck(new CardDoor(EColor.BLUE));
		addCardToDeck(new CardLabyrinthChamber(EColor.GREEN, ESubType.SUN));
		addCardToDeck(new CardLabyrinthChamber(EColor.BROWN, ESubType.SUN));
//		addCardToDeck(new CardLabyrinthChamber(EColor.BROWN, ESubType.KEY));
//		addCardToDeck(new CardLabyrinthChamber(EColor.BROWN, ESubType.SUN));
//		addCardToDeck(new CardLabyrinthChamber(EColor.GREEN, ESubType.KEY));
//		addCardToDeck(new CardDoor(EColor.BROWN));
//		addCardToDeck(new CardDoor(EColor.GREEN));
//		addCardToDeck(new CardDoor(EColor.BROWN));
//		addCardToDeck(new CardDreamNightmare());
//		addCardToDeck(new CardDreamNightmare());
//		addCardToDeck(new CardDreamNightmare());
//		addCardToDeck(new CardDreamNightmare());
//		addCardToDeck(new CardDreamNightmare());
//		getListsManager().deck.getArrayList().shuffle();

//		addCardToDraw(new CardLabyrinthChamber(EColor.BROWN, ESubType.KEY));
//		addCardToDraw(new CardLabyrinthChamber(EColor.GREEN, ESubType.MOON));
//		addCardToDraw(new CardLabyrinthChamber(EColor.RED, ESubType.SUN));

//		addCardToBoard(new CardLabyrinthChamber(EColor.BROWN, ESubType.KEY));
		addCardToBoard(new CardLabyrinthChamber(EColor.RED, ESubType.SUN));
		addCardToBoard(new CardLabyrinthChamber(EColor.GREEN, ESubType.MOON));
//		addCardToBoard(new CardLabyrinthChamber(EColor.RED, ESubType.SUN));
//		addCardToBoard(new CardLabyrinthChamber(EColor.RED, ESubType.SUN));
		addCardToBoard(new CardLabyrinthChamber(EColor.GREEN, ESubType.KEY));

//		addCardToLimbo(new CardLabyrinthChamber(EColor.BROWN, ESubType.SUN));
//		addCardToLimbo(new CardLabyrinthChamber(EColor.RED, ESubType.MOON));
//		addCardToLimbo(new CardLabyrinthChamber(EColor.GREEN, ESubType.MOON));

//		addDoor(EColor.GREEN);
//		addDoor(EColor.BROWN);
//		addDoor(EColor.GREEN);
//		addDoor(EColor.GREEN);
//		addDoor(EColor.GREEN);
//		addDoor(EColor.GREEN);
//		addDoor(EColor.GREEN);
//		addDoor(EColor.GREEN);

		Flow.INSTANCE.executeGameState(DrawCard.class);

	}

	public void addCardToHand(Card card) {

		getListsManager().hand.getArrayList().addLast(card);
		getListsManager().hand.relocateImageViews();

	}

	public void addCardToDeck(Card card) {

		getListsManager().deck.getArrayList().addLast(card);
		card.getImageView().flipBack();
		getListsManager().deck.relocateImageViews();

	}

	public void addCardToDraw(Card card) {

		getListsManager().draw.getArrayList().addLast(card);
		getListsManager().draw.relocateImageViews();

	}

	public void addCardToBoard(Card card) {

		getListsManager().board.getArrayList().addFirst(card);
		getListsManager().board.relocateImageViews();

	}

	public void addDoor(EColor eColor) {
		getListsManager().doors.addCardDoorAnimateAsynchronous(new CardDoor(eColor));
	}

	public void addCardToLimbo(Card card) {

		getListsManager().limbo.getArrayList().addFirst(card);
		getListsManager().limbo.relocateImageViews();

	}

}
