package utils;

import java.lang.reflect.InvocationTargetException;

import utils.Interfaces.IImageViewAble;

public enum ObjectPool {

	INSTANCE;

	private HashMap<Class<?>, ArrayList<IImageViewAble>> objectPool = new HashMap<>();

	@SuppressWarnings("unchecked")
	public <T> T acquire(Class<T> objectClass) {

		T t = null;

		if (!this.objectPool.containsKey(objectClass))
			this.objectPool.put(objectClass, new ArrayList<IImageViewAble>());

		for (IImageViewAble iImageViewAble : this.objectPool.getValue(objectClass)) {

			if (iImageViewAble.getImageView().isVisible())
				continue;

			iImageViewAble.getImageView().setVisible(true);

			t = (T) iImageViewAble;
			break;

		}

		if (t == null) {

			t = createNewObject(objectClass);
			this.objectPool.getValue(objectClass).addLast((IImageViewAble) t);

		}

		return t;

	}

	private <T> T createNewObject(Class<T> objectClass) {

		try {

			return (T) objectClass.getConstructor().newInstance();

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		ShutDown.INSTANCE.execute();
		return null;

	}

	public void print() {

		String stars = "************";

		Logger.INSTANCE.logNewLine(stars);
		Logger.INSTANCE.log("object pool");

		for (Class<?> classObject : this.objectPool) {

			int used = 0;
			int available = 0;

			for (Object object : this.objectPool.getValue(classObject)) {

				IImageViewAble imageViewAble = (IImageViewAble) object;

				if (imageViewAble.getImageView().isVisible())
					used++;
				else
					available++;

			}

			Logger.INSTANCE.newLine();
			Logger.INSTANCE.log(classObject);
			Logger.INSTANCE.log("used -> " + used);
			Logger.INSTANCE.log("available -> " + available);

		}

		Logger.INSTANCE.newLine();
		Logger.INSTANCE.logNewLine(stars);

	}

}
