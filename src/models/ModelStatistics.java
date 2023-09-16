package models;

import cards.Card;
import cards.CardDoor;
import cards.CardDreamNightmare;
import cards.CardLabyrinthChamber;
import enums.EColor;
import enums.ESubType;
import managers.Credentials;
import managers.ListsManager;
import utils.AnimationTimerFX;
import utils.ArrayList;
import utils.HashMap;
import utils.Interfaces.IUpdateAble;
import utils.Numeric;
import utils.TextIndicator;
import utils.Vector2;

public enum ModelStatistics {

	INSTANCE;

	private HashMap<EColor, TextIndicator> eColors = new HashMap<>();
	private HashMap<ESubType, TextIndicator> eSubTypes = new HashMap<>();
	private HashMap<EColor, HashMap<ESubType, TextIndicatorNumeric>> valuesCardsLabyrinthChamber = new HashMap<>();
	private TextIndicator doors = new TextIndicator("door");
	private HashMap<EColor, TextIndicatorNumeric> valuesDoors = new HashMap<>();
	private ArrayList<TextIndicatorNumeric> listTextIndicatorsNumeric = new ArrayList<>();
	private TextIndicator nightmaresTextIndicator = new TextIndicator(),
			answersTextIndicator = new TextIndicator();
	private Vector2 nightmaresVector2 = new Vector2(), answersVector2 = new Vector2();

	private ModelStatistics() {

		double x, y, length = 0;
		int gap = 5;

		// colors

		for (EColor eColor : EColor.values())
			this.eColors.put(eColor, new TextIndicator(eColor.toString().toLowerCase()));

		for (EColor eColor : this.eColors)
			length = Math.max(this.eColors.getValue(eColor).getWidth(), length);

		x = Credentials.INSTANCE.gapBetweenBorders;
		x += length / 2;
		y = Credentials.INSTANCE.gapBetweenBorders;
		y += Credentials.INSTANCE.dCard.y;
		y += Credentials.INSTANCE.dGapBetweenComponents.y;
		y += Credentials.INSTANCE.textHeight;
		y += Credentials.INSTANCE.textHeight / 2;

		for (EColor eColor : EColor.values()) {

			this.eColors.getValue(eColor).relocateCenter(x, y);
			y += Credentials.INSTANCE.textHeight;

		}

		// sub types

		x = this.eColors.getValue(EColor.BROWN).getCoordinatesTopLeftX();
		x += this.eColors.getValue(EColor.BROWN).getWidth();
		x += gap * Credentials.INSTANCE.dGapBetweenComponents.x;
		y = this.eColors.getValue(EColor.RED).getCoordinatesTopLeftY();
		y -= Credentials.INSTANCE.textHeight;

		for (ESubType eSubType : ESubType.values()) {

			TextIndicator textIndicator = new TextIndicator(eSubType.toString().toLowerCase());
			this.eSubTypes.put(eSubType, textIndicator);
			textIndicator.relocateTopLeft(x, y);

			x += textIndicator.getWidth();
			x += gap * Credentials.INSTANCE.dGapBetweenComponents.x;

		}

		// values labyrinth chamber

		for (EColor eColor : EColor.values()) {

			this.valuesCardsLabyrinthChamber.put(eColor, new HashMap<>());

			for (ESubType eSubType : ESubType.values()) {

				x = this.eSubTypes.getValue(eSubType).getCoordinatesCenterX();
				y = this.eColors.getValue(eColor).getCoordinatesCenterY();

				this.valuesCardsLabyrinthChamber.getValue(eColor).put(eSubType,
						new TextIndicatorNumeric(x, y));

			}

		}

		// doors

		x = this.eSubTypes.getValue(ESubType.KEY).getCoordinatesTopLeftX();
		x += this.eSubTypes.getValue(ESubType.KEY).getWidth();
		x += gap * Credentials.INSTANCE.dGapBetweenComponents.x;
		y = this.eSubTypes.getValue(ESubType.SUN).getCoordinatesTopLeftY();
		this.doors.relocateTopLeft(x, y);

		// doors values

		x = this.doors.getCoordinatesCenterX();

		for (EColor eColor : EColor.values()) {

			y = this.eColors.getValue(eColor).getCoordinatesCenterY();
			this.valuesDoors.put(eColor, new TextIndicatorNumeric(x, y));

		}

		// nightmares

		this.nightmaresVector2.x = Credentials.INSTANCE.gapBetweenBorders;
		this.nightmaresVector2.y = this.eColors.getValue(EColor.BROWN).getCoordinatesTopLeftY();
		this.nightmaresVector2.addY(Credentials.INSTANCE.textHeight);
		this.nightmaresTextIndicator.relocateTopLeft(this.nightmaresVector2);

		// answers

		this.answersVector2 = this.nightmaresVector2.clone();
		this.answersVector2.addY(Credentials.INSTANCE.textHeight);
		this.answersTextIndicator.relocateTopLeft(this.answersVector2);

	}

	public void update() {

		for (TextIndicatorNumeric textIndicatorNumeric : this.listTextIndicatorsNumeric)
			textIndicatorNumeric.reset();

		int nightmares = 0, answers = 0;

		ArrayList<Card> list = new ArrayList<>();
		list.addAllLast(ListsManager.INSTANCE.deck.getArrayList());
		list.addAllLast(ListsManager.INSTANCE.draw.getArrayList());
		list.addAllLast(ListsManager.INSTANCE.limbo.getArrayList());

		for (Card card : list) {

			if (card instanceof CardLabyrinthChamber) {

				CardLabyrinthChamber cardLabyrinthChamber = (CardLabyrinthChamber) card;
				EColor eColor = cardLabyrinthChamber.getEColor();
				ESubType eSubType = cardLabyrinthChamber.getESubType();

				if (eSubType.equals(ESubType.KEY))
					answers++;

				this.valuesCardsLabyrinthChamber.getValue(eColor).getValue(eSubType).addOne();

			} else if (card instanceof CardDoor) {

				CardDoor cardDoor = (CardDoor) card;
				EColor eColor = cardDoor.getEColor();
				this.valuesDoors.getValue(eColor).addOne();
				answers--;

			} else if (card instanceof CardDreamNightmare) {

				nightmares++;
				answers--;

			}

		}

		answers += ModelCard.INSTANCE.getKeysInHand().size();

		this.nightmaresTextIndicator.setText("nightmares: " + nightmares);
		this.answersTextIndicator.setText("answers: " + answers);

	}

	private class TextIndicatorNumeric implements IUpdateAble {

		private TextIndicator textIndicator = new TextIndicator(0);
		private Numeric numeric = new Numeric(0);
		private Vector2 vector2 = null;

		public TextIndicatorNumeric(double x, double y) {

			listTextIndicatorsNumeric.addLast(this);
			this.vector2 = new Vector2(x, y);
			update();

		}

		public void addOne() {
			this.numeric.add(1);
			update();
		}

		public void reset() {

			this.numeric.set(0);
			AnimationTimerFX.INSTANCE.updateNextFrame(this);

		}

		@Override
		public void update() {

			this.textIndicator.setText(this.numeric.get());
			this.textIndicator.relocateCenter(this.vector2);

		}

	}

}
