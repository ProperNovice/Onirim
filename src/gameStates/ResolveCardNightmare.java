package gameStates;

import enums.EText;
import gameStatesDefault.GameState;
import models.ModelCard;
import utils.ArrayList;
import utils.Flow;
import utils.HashMap;

public class ResolveCardNightmare extends GameState {

	private HashMap<EText, Class<? extends GameState>> map = new HashMap<>();
	private ArrayList<EText> list = new ArrayList<>();

	@Override
	public void execute() {

		createHashMap();

		discardKeyHandFromYourHand();
		placeDoorInLimbo();
		revealTheFirstFiveCards();
		this.list.addLast(EText.DISCARD_YOUR_HAND);

		for (EText eText : this.list)
			eText.show();

	}

	@Override
	protected void executeTextOption(EText eText) {

		ModelCard.INSTANCE.transferCardFromDrawToDiscardPileRelocate();
		Flow.INSTANCE.executeGameState(this.map.getValue(eText));

	}

	private void discardKeyHandFromYourHand() {

		if (!ModelCard.INSTANCE.getKeysInHand().isEmpty())
			this.list.addLast(EText.DISCARD_A_KEY_FROM_YOUR_HAND);

	}

	private void placeDoorInLimbo() {

		if (!getListsManager().doors.getDoors().isEmpty())
			this.list.addLast(EText.PLACE_A_DOOR_IN_LIMBO);

	}

	private void revealTheFirstFiveCards() {

		if (!getListsManager().draw.getArrayList().isEmpty())
			this.list.addLast(EText.REVEAL_THE_FIRST_FIVE_CARDS);

	}

	private void createHashMap() {

		this.map.put(EText.DISCARD_A_KEY_FROM_YOUR_HAND,
				ResolveCardNightmareDiscardAKeyFromHand.class);
		this.map.put(EText.PLACE_A_DOOR_IN_LIMBO, ResolveCardNightmarePlaceDoorInLimbo.class);
		this.map.put(EText.REVEAL_THE_FIRST_FIVE_CARDS,
				ResolveCardNightmareRevealFirstFiveCards.class);

	}

}
