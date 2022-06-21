package br.com.utilities.sorts;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HeapSort {

	/**
	 * 
	 * @param o
	 * @return
	 */
	private double doubleValue(Object o) {
		double result = 0;
		if (o instanceof Double) {
			result = ((Double) o).doubleValue();
		} else if (o instanceof Float) {
			result = ((Float) o).doubleValue();
		} else if (o instanceof Long) {
			result = ((Long) o).doubleValue();
		} else if (o instanceof Integer) {
			result = ((Integer) o).doubleValue();
		} else if (o instanceof Short) {
			result = ((Short) o).doubleValue();
		} else if (o instanceof Byte) {
			result = ((Byte) o).doubleValue();
		}
		return result;
	}

	/**
	 * 
	 * @param unsortedList
	 * @param swapOne
	 * @param swapTwo
	 */
	private void _swap(List<Object> unsortedList, int swapOne, int swapTwo) {
		Object holder = unsortedList.get(swapOne);
		unsortedList.set(swapOne, unsortedList.get(swapTwo));
		unsortedList.set(swapTwo, holder);
	}

	/**
	 * 
	 * @param unsortedList
	 * @param start
	 * @param end
	 */
	private void _swiftDown(List<Object> unsortedList, int start, int end) {
		int root = start;
		while (root * 2 + 1 <= end) {
			int child = root * 2 + 1;
			int swap = root;
			if (doubleValue(unsortedList.get(swap)) < doubleValue(unsortedList.get(child))) {
				swap = child;
			}
			if (child + 1 <= end && doubleValue(unsortedList.get(swap)) < doubleValue(unsortedList.get(child + 1))) {
				swap = child + 1;
			}
			if (swap != root) {
				_swap(unsortedList, root, swap);
				root = swap;
			} else {
				return;
			}
		}
	}

	/**
	 * 
	 * @param unsortedList
	 * @param count
	 */
	private void heapify(List<Object> unsortedList, int count) {
		int start = count / 2 - 1;
		while (start >= 0) {
			_swiftDown(unsortedList, start, count - 1);
			start -= 1;
		}
	}

	/**
	 * 
	 * @param unsortedList
	 * @return
	 */
	private List<Object> heapSort(List<Object> unsortedList) {
		int count = unsortedList.size();
		heapify(unsortedList, count);
		int end = count - 1;
		while (end > 0) {
			_swap(unsortedList, end, 0);
			end -= 1;
			_swiftDown(unsortedList, 0, end);
		}
		return unsortedList;
	}

	/**
	 * objects can be only Byte, Short, Integer, Long, Float and Double
	 * 
	 * @param darray
	 * @return
	 */
	public List<Object> heapSort(Object[] darray) {
		List<Object> dlist = heapSort(Arrays.asList(darray));
		return dlist;
	}

	/**
	 * 
	 * @param dlist
	 */
	public void heapSort(LinkedList<Double> dlist) {
		LinkedList<Object> olist = new LinkedList<Object>();
		for (Double d : dlist) {
			olist.add(d);
		}
		olist = (LinkedList<Object>) heapSort(olist);
	}

	/**
	 * 
	 * @param darray
	 * @return
	 */
	public LinkedList<Double> heapSort(Double[] darray) {
		LinkedList<Object> olist = new LinkedList<Object>();

		for (Double d : darray) {
			olist.add(d);
		}
		Object[] oarray = olist.toArray(new Object[0]);
		olist = (LinkedList<Object>) heapSort(oarray);

		LinkedList<Double> result = new LinkedList<Double>();
		olist.stream().forEach(o -> {
			result.add((Double) o);
		});
		return result;
	}

	/**
	 * objects can be only Byte, Short, Integer, Long, Float and Double
	 * 
	 * @param values
	 * @return
	 */
	public LinkedList<Double> heapSortU(Object... values) {
		List<Object> olist = heapSort(values);
		LinkedList<Double> result = new LinkedList<Double>();
		olist.stream().forEach(o -> {
			result.add((Double) o);
		});
		return result;
	}

	/**
	 * 
	 * @param values
	 * @return
	 */
	public LinkedList<Double> heapSortU(Double... values) {
		return heapSort(values);
	}
}
