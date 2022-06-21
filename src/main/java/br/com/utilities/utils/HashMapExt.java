package br.com.utilities.utils;

import java.util.HashMap;

public class HashMapExt<K, V> extends HashMap<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HashMapExt<K, V> set(K k, V v) {
		put(k, v);
		return this;
	}

	public HashMapExt<K, V> setAll(K[] k, V[] v) {
		if (k != null && k.length > 0 && v != null && v.length > 0 && k.length >= v.length) {
			int max = k.length;
			for (int i = 0; i < max; i++) {
				K k1 = k[i];
				V v1 = i < v.length ? v[i] : null;
				put(k1, v1);
			}
		}
		return this;
	}
}
