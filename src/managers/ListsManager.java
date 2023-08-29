package managers;

import cards.Card;
import listCredentials.ListCredentialsBoard;
import listCredentials.ListCredentialsDeck;
import listCredentials.ListCredentialsDiscardPile;
import listCredentials.ListCredentialsDraw;
import listCredentials.ListCredentialsHand;
import listCredentials.ListCredentialsLimbo;
import utils.ArrayList;
import utils.Interfaces.IImageViewAble;
import utils.ListImageViewAbles;

public enum ListsManager {

	INSTANCE;

	public final ArrayList<ListImageViewAbles<IImageViewAble>> lists = new ArrayList<ListImageViewAbles<IImageViewAble>>();
	public ListImageViewAbles<Card> hand, deck, draw, board, limbo, discardPile;
	public Doors doors = Doors.INSTANCE;

	public void instantiate() {

		this.hand = new ListImageViewAbles<>(ListCredentialsHand.class);
		this.deck = new ListImageViewAbles<>(ListCredentialsDeck.class);
		this.draw = new ListImageViewAbles<>(ListCredentialsDraw.class);
		this.board = new ListImageViewAbles<>(ListCredentialsBoard.class);
		this.limbo = new ListImageViewAbles<>(ListCredentialsLimbo.class);
		this.discardPile = new ListImageViewAbles<>(ListCredentialsDiscardPile.class);

	}

	public void saveListsOriginal() {

		for (ListImageViewAbles<IImageViewAble> list : this.lists)
			list.getArrayList().saveOriginal();

	}

	public void loadListsOriginal() {

		for (ListImageViewAbles<IImageViewAble> list : this.lists)
			list.getArrayList().clear();

		for (ListImageViewAbles<IImageViewAble> list : this.lists)
			list.getArrayList().loadOriginal();

	}

}
