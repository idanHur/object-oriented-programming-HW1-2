package id_318247822;

import java.util.Vector;

public class Set<T extends Citizen> {
	Vector<T> arr;

	public Set() {
		arr = new Vector<T>();
	}

	public void add(T object) {
		if (!(arr.contains(object))) {
			arr.add(object);
		}
	}

	public int size() {
		return arr.size();
	}

	public T get(int index) {
		return arr.get(index);
	}

	public boolean contains(T object) {
		return arr.contains(object);
	}

	public int capacity() {
		return arr.capacity();
	}

}
