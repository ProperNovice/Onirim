package gameStatesDefault;

import enums.EText;

public class EndGameWon extends EndGame {

	@Override
	protected EText getEText() {
		return EText.YOU_WON;
	}

}
