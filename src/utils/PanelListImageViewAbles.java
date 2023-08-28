package utils;

import managers.Credentials;
import utils.Enums.RearrangeTypeEnum;
import utils.Enums.RelocateTypeEnum;
import utils.Interfaces.IImageViewAble;

public enum PanelListImageViewAbles {

	INSTANCE;

	private ListImageViewAbles<? extends IImageViewAble> list = null;
	private boolean panelIsVisible = false;

	private PanelListImageViewAbles() {

	}

	public void executePanel(ListImageViewAbles<? extends IImageViewAble> list) {

		if (this.panelIsVisible)
			hideList();
		else
			showList(list);

	}

	private void showList(ListImageViewAbles<? extends IImageViewAble> list) {

		this.panelIsVisible = true;

		this.list = list;
		this.list.getListCredentials().saveCredentials();

		this.list.getListCredentials().coordinatesList = Credentials.INSTANCE.dFrame.clone();
		this.list.getListCredentials().coordinatesList.x /= 2;
		this.list.getListCredentials().coordinatesList.y /= 2;
		this.list.getListCredentials().relocateTypeEnum = RelocateTypeEnum.CENTER;
		this.list.getListCredentials().rearrangeTypeEnum = RearrangeTypeEnum.PIVOT;
		this.list.relocateImageViews();

		LayerZ.INSTANCE.showImageViewAblesToPanel(this.list);

	}

	private void hideList() {

		this.panelIsVisible = false;

		this.list.getListCredentials().loadCredentials();
		this.list.relocateImageViews();

		LayerZ.INSTANCE.hideImageViewAblesFromPanel();

	}

}
