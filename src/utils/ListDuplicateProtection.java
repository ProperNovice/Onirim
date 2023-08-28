package utils;

import managers.ListsManager;
import utils.Interfaces.IImageViewAble;

public enum ListDuplicateProtection {

	INSTANCE;

	private ListDuplicateProtection() {

	}

	public void execute(ListImageViewAbles<IImageViewAble> list, IImageViewAble object) {

		for (ListImageViewAbles<IImageViewAble> objectList : ListsManager.INSTANCE.lists) {

			if (objectList.equals(list)) {

				boolean alreadyFoundInstanceOfObject = false;

				for (IImageViewAble imageViewAble : list.getArrayList()) {

					if (!imageViewAble.equals(object))
						continue;

					if (alreadyFoundInstanceOfObject)
						ShutDown.INSTANCE.execute();

					alreadyFoundInstanceOfObject = true;

				}

			} else if (objectList.getArrayList().contains(object))
				ShutDown.INSTANCE.execute();

		}

	}

}
