package gameStatesDefault;

import enums.EText;

public class EndGameLost extends EndGame {

	@Override
	protected EText getEText() {
		return EText.YOU_LOST;
	}

}
