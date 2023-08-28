package utils;

import javafx.scene.input.KeyCode;

public enum KeyCodeHandler {

	INSTANCE;

	private HashMap<KeyCode, Integer> keyCodes = new HashMap<KeyCode, Integer>();

	private KeyCodeHandler() {

		addKeyCode(KeyCode.Q);
		addKeyCode(KeyCode.W);
		addKeyCode(KeyCode.E);
		addKeyCode(KeyCode.R);

	}

	private void addKeyCode(KeyCode keyCode) {
		this.keyCodes.put(keyCode, this.keyCodes.size());
	}

	public int getKeyCodeInt(KeyCode keyCode) {

		if (this.keyCodes.containsKey(keyCode))
			return this.keyCodes.getValue(keyCode);
		else
			return -1;

	}

}
