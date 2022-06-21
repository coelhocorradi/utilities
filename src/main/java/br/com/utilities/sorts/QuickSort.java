package br.com.utilities.sorts;

import java.util.Arrays;
import java.util.List;

public class QuickSort {

	private Double[] values;

	public Double[] sort(Double... values) {
		this.values = values;
		quickSort(0, this.values.length);
		return this.values;
	}

	public Double[] addAndSort(Double... values) {
		List<Double> list = Arrays.asList(this.values);
		list.addAll(Arrays.asList(values));
		this.values = list.toArray(this.values);
		quickSort(0, this.values.length);
		return this.values;
	}

	private void quickSort(int inicio, int fim) {
		int i = inicio;
		int j = fim;
		Double pivo = values[(inicio + fim) / 2];

		while (i <= j) {
			while (values[i] < pivo) {
				i++;
			}
			while (values[j] > pivo) {
				j--;
			}
			if (i <= j) {
				Double aux = values[i];
				values[i] = values[j];
				values[j] = aux;
				i++;
				j--;
			}
		}
		if (inicio < j) {
			quickSort(inicio, j);
		}
		if (i < fim) {
			quickSort(i, fim);
		}
	}
}
