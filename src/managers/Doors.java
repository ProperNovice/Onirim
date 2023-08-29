package managers;

import cards.CardDoor;
import utils.ArrayList;
import utils.HashMap;
import utils.Vector2;

public enum Doors {

	INSTANCE;

	private HashMap<Integer, Vector2> vectors2 = new HashMap<>();
	private ArrayList<DoorPosition> doorPositions = new ArrayList<>();

	private Doors() {

		createVectors2();

	}

	public void addCardDoorRelocate(CardDoor cardDoor) {

		this.doorPositions.addLast(new DoorPosition());
		this.doorPositions.getLast().addCardDoor(cardDoor);
		relocateDoorPositions();

	}

	private void relocateDoorPositions() {

		for (DoorPosition doorPosition : this.doorPositions)
			doorPosition
					.relocateDoor(this.vectors2.getValue(this.doorPositions.indexOf(doorPosition)));

	}

	private void createVectors2() {

		for (int counter = 0; counter < 8; counter++) {

			double x = Credentials.INSTANCE.cDoors.x;
			x += counter
					* (Credentials.INSTANCE.dCard.x + Credentials.INSTANCE.dGapBetweenComponents.x);

			double y = Credentials.INSTANCE.cDoors.y;

			this.vectors2.put(counter, new Vector2(x, y));

		}

	}

	private class DoorPosition {

		private CardDoor cardDoor = null;

		public DoorPosition() {

		}

		public void addCardDoor(CardDoor cardDoor) {
			this.cardDoor = cardDoor;
		}

//		public CardDoor removeCardDoor() {
//
//			CardDoor cardDoor = this.cardDoor;
//			this.cardDoor = null;
//
//			return cardDoor;
//
//		}

		public void relocateDoor(Vector2 vector2) {
			this.cardDoor.getImageView().relocateTopLeft(vector2);
		}

	}

}