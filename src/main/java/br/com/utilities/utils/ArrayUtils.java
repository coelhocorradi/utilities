package br.com.utilities.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author gustavo
 *
 */
public abstract class ArrayUtils {

	/**
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final <E> E[] listToArray(List<E> list) {
		E[] arr = (E[]) new Object[list.size()];
		return list.toArray(arr);
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
	public static final <E> E[] mergeArray(boolean excludeRepeated, E[]... eArrays) {
		List<E> aux = new ArrayList<E>();
		for (E[] arr : eArrays) {
			for (E e : arr) {
				if (excludeRepeated && !aux.contains(e)) {
					aux.add(e);
				} else {
					aux.add(e);
				}
			}
		}
		return aux.toArray((E[]) new Object[0]);
	}

	/**
	 * Junta dois ou mais arrays em um único array
	 * 
	 * @param eArrays
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final <E> E[] mergeArray(E[]... eArrays) {
		return mergeArray(false, eArrays);
	}
}
