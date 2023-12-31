package managers;

import utils.Enums.RearrangeTypeEnum;
import utils.Vector2;

public enum Credentials {

	INSTANCE;

	public final String primaryStageTitle = "Onirim", numbersImageViewColor = "black";
	public final boolean colliderVisibility = true;
	public double gapBetweenBorders = 25, textHeight = 50, selectEventHandlerAbleDimension = 100,
			animationStep = 4;
	public Vector2 dFrame, dGapBetweenComponents, dGapBetweenComponentsLineCast;
	public Vector2 cTextPanel, cImageViewIndicator;
	public RearrangeTypeEnum rearrangeTypeEnumText = RearrangeTypeEnum.PIVOT;

	public Vector2 dCard, dIcon;
	public Vector2 cHand, cDeck, cDraw, cBoard, cDoors, cLimbo, cDiscardPile;

	private Credentials() {

		double x = 0, y = 0;

		this.dFrame = new Vector2(2560 - 4 - 636, 1368 - 2);
		this.dGapBetweenComponents = new Vector2(4, 4);
		this.dGapBetweenComponentsLineCast = this.dGapBetweenComponents;

		// c image view indicator

		x = this.gapBetweenBorders;
		y = this.gapBetweenBorders;
		this.cImageViewIndicator = new Vector2(x, y);

		// d card

		x = 168;
		y = 260;
		this.dCard = new Vector2(x, y);

		// d icon

		x = this.dCard.x / 2;
		y = x;
		this.dIcon = new Vector2(x, y);

		// c hand

		x = this.dFrame.x / 2;
		x -= 2 * this.dCard.x;
		x -= 2 * this.dGapBetweenComponents.x;
		y = this.dFrame.y;
		y -= this.gapBetweenBorders;
		y -= this.dCard.y / 2;
		this.cHand = new Vector2(x, y);

		// c deck

		x = this.cHand.x;
		x -= this.dCard.x;
		x -= this.dGapBetweenComponents.x;
		y = this.cHand.y;
		y -= this.dCard.y;
		y -= this.dGapBetweenComponents.y;
		this.cDeck = new Vector2(x, y);

		// c draw

		x = this.cHand.x;
		y = this.cDeck.y;
		this.cDraw = new Vector2(x, y);

		// c board

		x = this.dFrame.x / 2;
		y = this.cDraw.y;
		y -= this.dCard.y;
		y -= this.dGapBetweenComponents.y;
		this.cBoard = new Vector2(x, y);

		// c doors

		x = 8 * this.dCard.x;
		x += 7 * this.dGapBetweenComponents.x;
		x = (this.dFrame.x - x) / 2;
		y = this.gapBetweenBorders;
		this.cDoors = new Vector2(x, y);

		// c limbo

		x = this.dFrame.x / 2;
		x += 3 * this.dCard.x;
		x += 3 * this.dGapBetweenComponents.x;
		y = this.cDeck.y;
		this.cLimbo = new Vector2(x, y);

		// c discard pile

		x = this.cHand.x;
		x -= 2 * (this.dCard.x + this.dGapBetweenComponents.x);
		y = this.cHand.y;
		this.cDiscardPile = new Vector2(x, y);

		// text

		y = this.dCard.y / 5;
		this.textHeight = y;

		// c text panel

		x = this.cDraw.x;
		x += this.dCard.x / 2;
		x += this.dGapBetweenComponents.x;
		y = this.cDraw.y;
		this.cTextPanel = new Vector2(x, y);

	}

}
