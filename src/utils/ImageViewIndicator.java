package utils;

import managers.Credentials;
import utils.Enums.MapImageViews;
import utils.Interfaces.IImageViewAble;

public enum ImageViewIndicator {

	INSTANCE;

	private ImageViewIndicator() {

	}

	public void setImage(IImageViewAble imageViewAble) {
		ImageViewClone.INSTANCE.setImage(imageViewAble);
	}

	public void setVisibleFalse() {
		ImageViewClone.INSTANCE.getImageView().setVisible(false);
	}

	private enum ImageViewClone implements IImageViewAble {

		INSTANCE;

		private HashMap<String, Image> map = new HashMap<>();

		private ImageViewClone() {

		}

		public void setImage(IImageViewAble imageViewAble) {

			Image image = imageViewAble.getImageView().getImageShowing();

			String filePath = image.getFilePath();

			if (!this.map.containsKey(filePath))
				this.map.put(filePath, new Image(filePath));

			Image imageClone = this.map.getValue(filePath);

			if (!MapImageViews.INSTANCE.getImageViewsMap().containsKey(this))
				new ImageView(imageClone, this);

			else {

				getImageView().setImage(imageClone);
				getImageView().setVisible(true);

			}

			getImageView().relocateTopLeft(Credentials.INSTANCE.cImageViewIndicator);

		}

	}

}
