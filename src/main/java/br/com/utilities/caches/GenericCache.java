package br.com.utilities.caches;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * 
 * @author gustavo
 *
 * @param <T>
 */
public abstract class GenericCache<T> {

	protected List<T> list;

	public GenericCache() {
		list = new ArrayList<T>();
	}

	public abstract boolean find(T t, Object... args);

	public T find(Object... args) {
		T result = null;
		boolean found = false;
		for (int i = 0; i < list.size() && !found; i++) {
			T t = list.get(i);
			found = find(t, args);
			if (found) {
				result = t;
			}
		}
		return result;
	}

	public boolean find(T t) {
		boolean result = false;
		for (int i = 0; i < list.size() && !result; i++) {
			T aux = list.get(i);
			result = aux.equals(t);
		}
		return result;
	}

	public void insertOrUpdate(T t) {
		if (find(t)) {
			list.set(list.indexOf(t), t);
		} else {
			list.add(t);
		}
	}

}
