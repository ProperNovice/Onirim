package listCredentials;

import managers.Credentials;
import utils.Enums.DirectionEnum;
import utils.Enums.LayerZListEnum;
import utils.Enums.RearrangeTypeEnum;
import utils.Enums.RelocateTypeEnum;
import utils.Vector2;

public class ListCredentials {

	public LayerZListEnum layerZListEnum = LayerZListEnum.TO_BACK_FIRST_IMAGEVIEW;
	private LayerZListEnum layerZListEnumSave = null;

	public int objectsPerRow = -1;
	private int objectsPerRowSave = -1;

	public RearrangeTypeEnum rearrangeTypeEnum = RearrangeTypeEnum.LINEAR;
	private RearrangeTypeEnum rearrangeTypeEnumSave = null;

	public RelocateTypeEnum relocateTypeEnum = RelocateTypeEnum.TOP_LEFT;
	private RelocateTypeEnum relocateTypeEnumSave = null;

	public DirectionEnum directionEnumHorizontal = DirectionEnum.RIGHT,
			directionEnumVertical = DirectionEnum.DOWN;
	private DirectionEnum directionEnumHorizontalSave = null, directionEnumVerticalSave = null;

	public boolean showListSize = false;
	private boolean showListSizeSave = false;

	public Vector2 coordinatesList = new Vector2(0, 0),
			gapBetweenComponents = Credentials.INSTANCE.dGapBetweenComponents.clone();
	private Vector2 coordinatesListSave = null, gapBetweenComponentsSave = null;

	public double listQuantityRatioImageViewDimensions = 0.5;
	private double listQuantityRatioImageViewDimensionsSave = 0.5;

	public int capacity = -1;
	private int capacitySave = -1;

	public final void saveCredentials() {

		this.layerZListEnumSave = this.layerZListEnum;
		this.objectsPerRowSave = this.objectsPerRow;
		this.rearrangeTypeEnumSave = this.rearrangeTypeEnum;
		this.relocateTypeEnumSave = this.relocateTypeEnum;
		this.directionEnumHorizontalSave = this.directionEnumHorizontal;
		this.directionEnumVerticalSave = this.directionEnumVertical;
		this.showListSizeSave = this.showListSize;
		this.coordinatesListSave = this.coordinatesList;
		this.gapBetweenComponentsSave = this.gapBetweenComponents;
		this.listQuantityRatioImageViewDimensionsSave = this.listQuantityRatioImageViewDimensions;
		this.capacitySave = this.capacity;

	}

	public final void loadCredentials() {

		this.layerZListEnum = this.layerZListEnumSave;
		this.objectsPerRow = this.objectsPerRowSave;
		this.rearrangeTypeEnum = this.rearrangeTypeEnumSave;
		this.relocateTypeEnum = this.relocateTypeEnumSave;
		this.directionEnumHorizontal = this.directionEnumHorizontalSave;
		this.directionEnumVertical = this.directionEnumVerticalSave;
		this.showListSize = this.showListSizeSave;
		this.coordinatesList = this.coordinatesListSave;
		this.gapBetweenComponents = this.gapBetweenComponentsSave;
		this.listQuantityRatioImageViewDimensions = this.listQuantityRatioImageViewDimensionsSave;
		this.capacity = this.capacitySave;

	}

}
