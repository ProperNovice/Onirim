package listCredentials;

import managers.Credentials;
import utils.Enums.LayerZListEnum;
import utils.Enums.RearrangeTypeEnum;
import utils.Enums.RelocateTypeEnum;

public class ListCredentialsLimbo extends ListCredentials {

	public ListCredentialsLimbo() {

		super.coordinatesList = Credentials.INSTANCE.cLimbo;
		super.rearrangeTypeEnum = RearrangeTypeEnum.STATIC;
		super.relocateTypeEnum = RelocateTypeEnum.CENTER;
		super.layerZListEnum = LayerZListEnum.TO_FRONT_FIRST_IMAGEVIEW;

	}

}
