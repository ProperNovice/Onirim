package managers;

import cards.CardDoor;
import utils.Animation;
import utils.ArrayList;
import utils.Enums.AnimationSynchEnum;
import utils.HashMap;
import utils.Vector2;

public enum Doors {

	INSTANCE;

	private HashMap<Integer, Vector2> vectors2 = new HashMap<>();
	private ArrayList<DoorPosition> doorPositions = new ArrayList<>();

	private Doors() {

		createDoorPositions();
		createVector2();

	}

	public void removeDoorAnimateAsynchronous(CardDoor cardDoor) {

		for (DoorPosition doorPosition : this.doorPositions)
			if (doorPosition.containsCardDoor(cardDoor))
				doorPosition.removeCardDoor();

		animateDoorPositions();

	}

	public ArrayList<CardDoor> getDoors() {

		ArrayList<CardDoor> list = new ArrayList<>();

		for (DoorPosition doorPosition : this.doorPositions)
			if (doorPosition.containsCardDoor())
				list.addLast(doorPosition.getCardDoor());

		return list;

	}

	public void addCardDoorAnimateAsynchronous(CardDoor cardDoor) {

		this.doorPositions.addLast(new DoorPosition());
		this.doorPositions.getLast().addCardDoor(cardDoor);
		animateDoorPositions();
//		Lock.INSTANCE.lock();

	}

	private void animateDoorPositions() {

		for (DoorPosition doorPosition : this.doorPositions.clone())
			if (doorPosition.isEmpty()) {
				this.doorPositions.remove(doorPosition);
				this.doorPositions.addLast(doorPosition);
			}

		for (DoorPosition doorPosition : this.doorPositions)
			if (!doorPosition.isEmpty())
				doorPosition.animateAsynchronous(
						this.vectors2.getValue(this.doorPositions.indexOf(doorPosition)));

	}

	private void createDoorPositions() {

		for (int counter = 1; counter <= 8; counter++)
			this.doorPositions.addLast(new DoorPosition());

	}

	private void createVector2() {

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

		public void removeCardDoor() {
			this.cardDoor = null;
		}

		public void animateAsynchronous(Vector2 vector2) {
			Animation.INSTANCE.animateTopLeft(this.cardDoor, vector2,
					AnimationSynchEnum.ASYNCHRONOUS);
		}

		public boolean containsCardDoor() {
			return this.cardDoor != null;
		}

		public boolean containsCardDoor(CardDoor cardDoor) {

			if (this.cardDoor == null || !this.cardDoor.equals(cardDoor))
				return false;
			else
				return true;
		}

		public CardDoor getCardDoor() {
			return this.cardDoor;
		}

		public boolean isEmpty() {
			return this.cardDoor == null;
		}

	}

}
