package utils;

import utils.Interfaces.IImageViewAble;

public class ArrayListImageView<T> extends ArrayList<T> {

	private ListImageViewAbles<IImageViewAble> list = null;
	private Runnable runnableShowListSize = null;

	public ArrayListImageView(ListImageViewAbles<IImageViewAble> list,
			Runnable runnableShowListSize) {

		this.list = list;
		this.runnableShowListSize = runnableShowListSize;

	}

	@Override
	public void add(int index, T element) {

		super.add(index, element);
		objectAddedToList((IImageViewAble) element);

	}

	@Override
	public void addLast(T element) {

		super.addLast(element);
		objectAddedToList((IImageViewAble) element);

	}

	@Override
	public T remove(int index) {

		T t = super.remove(index);
		objectRemovedFromList((IImageViewAble) t);

		return t;

	}

	@Override
	public void shuffle() {

		super.shuffle();
		this.list.layerZSort();

	}

	private void objectAddedToList(IImageViewAble object) {

		ListDuplicateProtection.INSTANCE.execute(this.list, object);
		this.runnableShowListSize.run();

	}

	private void objectRemovedFromList(IImageViewAble object) {

		this.runnableShowListSize.run();

	}

}
