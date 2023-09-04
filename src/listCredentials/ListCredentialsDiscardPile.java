package listCredentials;

import managers.Credentials;
import utils.Enums.LayerZListEnum;
import utils.Enums.RearrangeTypeEnum;
import utils.Enums.RelocateTypeEnum;

public class ListCredentialsDiscardPile extends ListCredentials {

	public ListCredentialsDiscardPile() {

		super.coordinatesList = Credentials.INSTANCE.cDiscardPile;
		super.rearrangeTypeEnum = RearrangeTypeEnum.STATIC;
		super.relocateTypeEnum = RelocateTypeEnum.CENTER;
		super.layerZListEnum = LayerZListEnum.TO_FRONT_FIRST_IMAGEVIEW;
		super.showListSize = true;

	}

}
