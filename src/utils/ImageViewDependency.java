package utils;

import utils.Interfaces.IImageViewAble;

public enum ImageViewDependency {

	INSTANCE;

	private HashMap<IImageViewAble, ArrayList<IImageViewAble>> map = new HashMap<>();

	private ImageViewDependency() {

	}

	public void addDependency(IImageViewAble parent, IImageViewAble child) {

		ArrayList<IImageViewAble> list = null;

		if (!containsDependency(parent)) {

			list = new ArrayList<>();
			this.map.put(parent, list);

		} else
			list = getDependency(parent);

		list.addLast(child);

	}

	public boolean containsDependency(IImageViewAble imageViewAble) {
		return this.map.containsKey(imageViewAble);
	}

	public ArrayList<IImageViewAble> getDependency(IImageViewAble imageViewAble) {
		return this.map.getValue(imageViewAble);
	}

	public void removeDependency(IImageViewAble parent, IImageViewAble child) {

		this.map.getValue(parent).remove(child);

		if (this.map.getValue(parent).isEmpty())
			this.map.removeKey(parent);

	}

	public void removeAllDependenciesFromParent(IImageViewAble parent) {
		this.map.removeKey(parent);
	}

	public void removeAllDependenciesAsChild(IImageViewAble child) {

		for (IImageViewAble parent : this.map)
			if (this.map.getValue(parent).contains(child))
				this.map.getValue(parent).remove(child);

		clearEmptyParents();

	}

	private void clearEmptyParents() {

		for (IImageViewAble parent : this.map.clone())
			if (this.map.getValue(parent).isEmpty())
				this.map.removeKey(parent);

	}

	public void clearDependencies() {
		this.map.clear();
	}

}
