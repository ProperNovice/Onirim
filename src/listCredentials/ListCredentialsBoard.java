package listCredentials;

import managers.Credentials;
import utils.Enums.DirectionEnum;
import utils.Enums.LayerZListEnum;
import utils.Enums.RearrangeTypeEnum;
import utils.Enums.RelocateTypeEnum;

public class ListCredentialsBoard extends ListCredentials {

	public ListCredentialsBoard() {

		super.coordinatesList = Credentials.INSTANCE.cBoard;
		super.rearrangeTypeEnum = RearrangeTypeEnum.PIVOT;
		super.relocateTypeEnum = RelocateTypeEnum.CENTER;
		super.gapBetweenComponents.x = Credentials.INSTANCE.dCard.x * 0.25;
		super.directionEnumHorizontal = DirectionEnum.LEFT;
		super.layerZListEnum = LayerZListEnum.TO_FRONT_FIRST_IMAGEVIEW;

	}

}
