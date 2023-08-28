package listCredentials;

import managers.Credentials;
import utils.Enums.RearrangeTypeEnum;
import utils.Enums.RelocateTypeEnum;

public class ListCredentialsDeck extends ListCredentials {

	public ListCredentialsDeck() {

		super.coordinatesList = Credentials.INSTANCE.cDeck;
		super.rearrangeTypeEnum = RearrangeTypeEnum.STATIC;
		super.relocateTypeEnum = RelocateTypeEnum.CENTER;

	}

}
