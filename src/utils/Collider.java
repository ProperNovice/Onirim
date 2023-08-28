package utils;

import managers.Credentials;

public enum Collider {

	INSTANCE;

	private ArrayList<ColliderShape> list = new ArrayList<>();

	public void addRectangleTopLeftCoordinates(double topLeftX, double topLeftY, double width,
			double height) {

		this.list.addLast(new ColliderRectangle(topLeftX, topLeftY, width, height,
				Credentials.INSTANCE.colliderVisibility));

	}

	public void addRectangleCenterCoordinates(double centerX, double centerY, double width,
			double height) {

		double topLeftX = centerX - width / 2;
		double topLeftY = centerY - height / 2;

		addRectangleTopLeftCoordinates(topLeftX, topLeftY, width, height);

	}

	public void addCircleCoordinates(double centerX, double centerY, double radius) {
		this.list.addLast(new ColliderCircle(centerX, centerY, radius));
	}

	public boolean contains(double x, double y) {

		for (ColliderShape collider : this.list)
			if (collider.contains(x, y))
				return true;

		return false;

	}

	public boolean contains(Vector2 numbersPair) {
		return contains(numbersPair.x, numbersPair.y);
	}

	private abstract class ColliderShape {

		public abstract boolean contains(double x, double y);

	}

	private class ColliderCircle extends ColliderShape {

		private double centerX, centerY, radius;

		public ColliderCircle(double centerX, double centerY, double radius) {

			this.centerX = centerX;
			this.centerY = centerY;
			this.radius = radius;

		}

		@Override
		public boolean contains(double x, double y) {

			double distance = Math
					.sqrt(Math.pow(this.centerX - x, 2) + Math.pow(this.centerY - y, 2));

			return distance <= this.radius;

		}

	}

	private class ColliderRectangle extends ColliderShape {

		private double leftX, topY, rightX, bottomY;

		public ColliderRectangle(double topLeftX, double topY, double width, double height,
				boolean visibility) {

			this.leftX = topLeftX;
			this.rightX = this.leftX + width;
			this.topY = topY;
			this.bottomY = this.topY + height;

			if (!visibility)
				return;

			new Rectangle(topLeftX, topY, width, height).toFront();

		}

		@Override
		public boolean contains(double x, double y) {

			if (x < this.leftX)
				return false;

			if (x > this.rightX)
				return false;

			if (y < this.topY)
				return false;

			if (y > this.bottomY)
				return false;

			return true;

		}

	}

}
