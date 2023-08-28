package utils;

import java.util.Collections;
import java.util.Iterator;

import utils.Interfaces.ISaveLoadStateAble;

public class ArrayList<T> implements Iterable<T>, ISaveLoadStateAble {

	protected java.util.ArrayList<T> list = new java.util.ArrayList<>();
	private java.util.ArrayList<T> listOriginal = new java.util.ArrayList<>();
	private java.util.ArrayList<T> listState = new java.util.ArrayList<>();
	private int capacity = -1;

	public ArrayList() {

	}

	public ArrayList(T[] list) {
		addAllLast(list);
	}

	public ArrayList(int capacity) {
		this.capacity = capacity;
	}

	private ArrayList(java.util.ArrayList<T> list, int capacity) {
		this.list = list;
		this.capacity = capacity;
	}

	public void add(int index, T element) {
		this.list.add(index, element);
	}

	public final void addFirst(T element) {
		add(0, element);
	}

	public final void addFirst(T element, int times) {

		for (int counter = 1; counter <= times; counter++)
			addFirst(element);

	}

	public final void addAllFirst(ArrayList<T> list) {

		list.reverse();

		for (T t : list)
			addFirst(t);

	}

	@SafeVarargs
	public final void addAllFirst(T... list) {
		addAllFirst(new ArrayList<>(list));
	}

	public void addLast(T element) {
		this.list.add(element);
	}

	public final void addLast(T element, int times) {

		for (int counter = 1; counter <= times; counter++)
			addLast(element);

	}

	public final void addAllLast(ArrayList<T> list) {

		for (T t : list)
			addLast(t);

	}

	@SafeVarargs
	public final void addAllLast(T... list) {

		for (T t : list)
			addLast(t);

	}

	public final ArrayList<T> clear() {

		ArrayList<T> list = clone();

		while (!isEmpty())
			removeRandom();

		return list;

	}

	public final boolean contains(Object o) {
		return this.list.contains(o);
	}

	public final T get(int index) {
		return this.list.get(index);
	}

	public final int indexOf(Object o) {
		return this.list.indexOf(o);
	}

	public final boolean isEmpty() {
		return this.list.isEmpty();
	}

	public final boolean isMaxCapacity() {

		if (this.capacity == -1)
			return false;
		else
			return this.list.size() == this.capacity;

	}

	public final boolean isOverCapacity() {

		if (this.capacity == -1)
			return false;
		else
			return this.list.size() > this.capacity;

	}

	public final int getCapacity() {
		return this.capacity;
	}

	public T remove(int index) {
		return this.list.remove(index);
	}

	public final void remove(T t) {

		int index = indexOf(t);
		remove(index);

	}

	public void shuffle() {

		java.util.ArrayList<T> listOriginal = new java.util.ArrayList<>(this.list);
		this.list.clear();

		while (!listOriginal.isEmpty())
			this.list.add(listOriginal
					.remove(Random.INSTANCE.getRandomNumber(0, listOriginal.size() - 1)));

	}

	public final T getFirst() {
		return get(0);
	}

	public final T removeFirst() {
		return remove(0);
	}

	public final T getLast() {
		return get(this.list.size() - 1);
	}

	public final T removeLast() {
		return remove(this.list.size() - 1);
	}

	public final T getRandom() {

		int randomIndex = Random.INSTANCE.getRandomNumber(0, this.list.size() - 1);
		return get(randomIndex);

	}

	public final T removeRandom() {

		int randomIndex = Random.INSTANCE.getRandomNumber(0, this.list.size() - 1);
		return remove(randomIndex);

	}

	public final int size() {
		return this.list.size();
	}

	public final void reverse() {
		Collections.reverse(this.list);
	}

	public final void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public final Iterator<T> iterator() {
		return this.list.iterator();
	}

	@Override
	public final ArrayList<T> clone() {

		java.util.ArrayList<T> arrayList = new java.util.ArrayList<>(this.list);
		ArrayList<T> arrayListToReturn = new ArrayList<T>(arrayList, this.capacity);

		return arrayListToReturn;

	}

	@Override
	public final void saveOriginal() {

		this.listOriginal.clear();
		this.listOriginal.addAll(this.list);

	}

	@Override
	public final void loadOriginal() {

		clear();

		for (T t : this.listOriginal)
			addLast(t);

	}

	@Override
	public final void saveState() {

		this.listState.clear();
		this.listState.addAll(this.list);

	}

	@Override
	public final void loadState() {

		clear();

		for (T t : this.listState)
			addLast(t);

	}

}
