package utils;

import java.util.Iterator;

public class HashMap<K, V> implements Iterable<K> {

	private ArrayList<K> keys = new ArrayList<>();
	private ArrayList<V> values = new ArrayList<>();

	public HashMap() {

	}

	public void put(K key, V value) {

		if (this.keys.contains(key))
			removeKey(key);

		this.keys.addLast(key);
		this.values.addLast(value);

	}

	public void removeKey(K key) {
		removeEntry(this.keys.indexOf(key));
	}

	public void removeValue(V value) {
		removeEntry(this.values.indexOf(value));
	}

	private void removeEntry(int index) {

		this.keys.remove(index);
		this.values.remove(index);

	}

	public V getValue(K key) {

		int index = this.keys.indexOf(key);
		return this.values.get(index);

	}

	public K getKey(V value) {

		int index = this.values.indexOf(value);
		return this.keys.get(index);

	}

	public void clear() {

		this.keys.clear();
		this.values.clear();

	}

	@Override
	public Iterator<K> iterator() {
		return this.keys.iterator();
	}

	public boolean containsKey(K key) {
		return this.keys.contains(key);
	}

	public boolean containsValue(V value) {
		return this.values.contains(value);
	}

	public int size() {
		return this.keys.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public void print() {

		Logger.INSTANCE.logNewLine("/*");

		Logger.INSTANCE.logNewLine("printing map");

		for (int index = 0; index < this.keys.size(); index++)
			Logger.INSTANCE.log(this.keys.get(index) + " -> " + this.values.get(index));

		Logger.INSTANCE.newLine();
		Logger.INSTANCE.logNewLine("*/");

	}

	public HashMap<K, V> clone() {

		HashMap<K, V> map = new HashMap<>();

		for (int index = 0; index < this.keys.size(); index++)
			map.put(this.keys.get(index), this.values.get(index));

		return map;

	}

}
