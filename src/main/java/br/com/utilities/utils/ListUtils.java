package br.com.utilities.utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author gustavo
 *
 */
public abstract class ListUtils {

	/**
	 * 
	 * @param array
	 * @return
	 */
	public static final <E> List<E> arrayToList(E[] array) {
		List<E> list = new LinkedList<E>();
		for (E e : array) {
			list.add(e);
		}
		return list;
	}

	/**
	 * filter element inserted on list by clazz, E element can be the same clazz
	 * 
	 * @param list
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final <E> List<E> filter(List<Object> list, Class<?> clazz) {
		List<E> result = new LinkedList<E>();
		Iterator<Object> i = list.iterator();
		while (i.hasNext()) {
			Object o = i.next();
			if (o.getClass() == clazz) {
				result.add((E) o);
			}
		}
		return result;
	}

	public static void println(List<String> list) {
		println(list.toArray(new String[0]));
	}

	public static void println(String[] list) {
		StringBuilder sb = new StringBuilder();
		for (String s : list) {
			sb.append(s).append(System.lineSeparator());
		}
		System.out.println(sb.toString());
	}
}
