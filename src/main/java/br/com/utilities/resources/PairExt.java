package br.com.utilities.resources;

import org.apache.commons.math3.util.Pair;

import br.com.utilities.utils.ReflectUtils;

/**
 * 
 * @author gustavo
 *
 * @param <V>
 * @param <K>
 */
public class PairExt<V, K> extends Pair<K, V> {

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public PairExt(K key, V value) {
		super(key, value);
	}

	/**
	 * 
	 * @param key
	 */
	public void setKey(K key) {
		ReflectUtils.<K>setField(this, fieldKey, key);
	}

	/**
	 * 
	 * @param value
	 */
	public void setValue(V value) {
		ReflectUtils.<V>setField(this, fieldValue, value);
	}

	private static final String fieldKey = "key";

	private static final String fieldValue = "value";
}
