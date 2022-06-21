package br.com.utilities.sorts;

import java.util.Arrays;
import java.util.List;

public class BubbleSort {

	private Double[] values;

	public Double[] sort(Double... values) {
		this.values = values;
		bubbleSort();
		return this.values;
	}

	public Double[] addAndSort(Double... values) {
		List<Double> list = Arrays.asList(this.values);
		list.addAll(Arrays.asList(values));
		this.values = list.toArray(this.values);
		bubbleSort();
		return this.values;
	}

	public void bubbleSort() {
		boolean trocado;
		do {
			trocado = false;
			for (int i = 0; i < values.length - 2; i++) {
				if (values[i] > values[i + 1]) {
					Double aux = values[i];
					values[i] = values[i + 1];
					values[i + 1] = aux;
					trocado = true;
				}
			}
		} while (trocado);
	}
}
