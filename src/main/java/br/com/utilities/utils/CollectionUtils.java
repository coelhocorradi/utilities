package br.com.utilities.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.utilities.interfaces.IMatcher;

public abstract class CollectionUtils {

	/**
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final <E> E[] toArray(List<E> list) {
		return list.toArray((E[]) new Object[list.size()]);
	}

	/**
	 * Junta dois ou mais arrays em um único array
	 * 
	 * @param excludeRepeated true para excluir repetidos, false para manter todos
	 *                        os elementos mesmo repetidos
	 * @param eArrays
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final <E> E[] mergeNoRepeat(E[]... eArrays) {
		List<E> result = new ArrayList<E>();
		for (E[] arr : eArrays) {
			List<E> aux = Arrays.asList(arr);
			aux.forEach(e -> {
				if (!result.contains(e)) {
					result.add(e);
				}
			});
		}
		return result.toArray((E[]) new Object[0]);
	}

	/**
	 * Junta dois ou mais arrays em um único array
	 * 
	 * @param eArrays
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final <E> E[] merge(E[]... eArrays) {
		List<E> list = new ArrayList<E>();
		for (E[] l : eArrays) {
			list.addAll(Arrays.asList(l));
		}
		return list.toArray((E[]) new Object[0]);
	}

	@SuppressWarnings("unchecked")
	public static final <E> E[] removeNullValues(E[] arr) {
		return Arrays.asList(arr).stream().filter(e -> e != null).collect(Collectors.toList())
				.toArray((E[]) new Object[0]);
	}

	@SuppressWarnings("unchecked")
	public static final <E> E[] removeClones(E[] arr) {
		List<E> list = Arrays.asList(arr);
		List<E> result = new ArrayList<E>();
		list.forEach(e -> {
			if (!result.contains(e)) {
				result.add(e);
			}
		});
		return result.toArray((E[]) new Object[0]);
	}

	public static <E> E[] sortArray(E[] arr, Comparator<E> comparator) {
		return Arrays.asList(arr).stream().sorted(comparator).collect(Collectors.toList()).toArray(arr);
	}

	public static <E> boolean isEmpty(E[] arr) {
		boolean result = arr == null;
		if (!result) {
			result = arr.length <= 0;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <E> E[] toArray(E... e) {
		return e;
	}

	/**
	 * 
	 * @param array
	 * @return
	 * 
	 * @deprecated
	 * @see Arrays.asList(E[] array)
	 */
	@Deprecated
	public static final <E> List<E> arrayToList(E[] array) {
		List<E> list = new LinkedList<E>();
		for (E e : array) {
			list.add(e);
		}
		return list;
	}

	/**
	 * filter element inserted on list by clazz, E element can be the same clazz
	 * util when you need filter a list of objects or filter object list super class
	 * by child clazz
	 * 
	 * @param list
	 * @param clazz
	 * @return
	 */
	public static final <E> List<E> filterByClass(List<E> list, Class<?> clazz) {
		return list.stream().filter(e -> e.getClass().equals(clazz)).collect(Collectors.toList());
	}

	public static <E> List<E> removeNullValues(List<E> list) {
		return list.stream().filter(e -> e != null).collect(Collectors.toList());
	}

	public static <E> List<E> removeClones(List<E> list) {
		List<E> result = new ArrayList<E>();
		list.forEach(e -> {
			if (!result.contains(e)) {
				result.add(e);
			}
		});
		return result;
	}

	public static <E> boolean isEmpty(List<E> list) {
		boolean result = list == null;
		if (!result) {
			result = list.size() <= 0;
		}
		return result;
	}

	public static <E> List<E> filter(List<E> list, IMatcher<E> matcher) {
		List<E> result = new LinkedList<E>();
		for (E e1 : list) {
			if (!result.stream().anyMatch(e2 -> matcher.match(e1, e2))) {
				result.add(e1);
			}
		}
		return result;
	}
}
