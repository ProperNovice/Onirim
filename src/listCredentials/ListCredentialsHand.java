package listCredentials;

import managers.Credentials;
import utils.Enums.RelocateTypeEnum;

public class ListCredentialsHand extends ListCredentials {

	public ListCredentialsHand() {

		super.coordinatesList = Credentials.INSTANCE.cHand;
		super.capacity = 5;
		super.relocateTypeEnum = RelocateTypeEnum.CENTER;

	}

}
