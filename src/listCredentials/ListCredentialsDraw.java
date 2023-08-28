package listCredentials;

import managers.Credentials;
import utils.Enums.RelocateTypeEnum;

public class ListCredentialsDraw extends ListCredentials {

	public ListCredentialsDraw() {

		super.coordinatesList = Credentials.INSTANCE.cDraw;
		super.relocateTypeEnum = RelocateTypeEnum.CENTER;

	}

}
