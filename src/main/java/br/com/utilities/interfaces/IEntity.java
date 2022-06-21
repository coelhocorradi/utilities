package br.com.utilities.interfaces;

import java.io.Serializable;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.utilities.gsonutils.GsonAdapter;

public interface IEntity<E> extends Serializable {

	public default E copy() {
		Type type = new TypeToken<E>() {
		}.getType();
		Gson gson = GsonAdapter.defaultGson();
		return gson.fromJson(gson.toJson(this), type);
	}

	public default String toJson() {
		Type type = new TypeToken<E>() {
		}.getType();
		return GsonAdapter.defaultGson().toJson(this, type);
	}

	public default E fromJson(String json) {
		Type type = new TypeToken<E>() {
		}.getType();
		return new Gson().fromJson(json, type);
	}

	public default E createNew() {
		Type type = new TypeToken<E>() {
		}.getType();
		return new Gson().fromJson("{}", type);
	}

	public default E createNew(IPopulate<E> populate) {
		Type type = new TypeToken<E>() {
		}.getType();
		E e = new Gson().fromJson("{}", type);
		if (populate != null) {
			e = populate.populate(e);
		}
		return e;
	}

	interface IPopulate<E> {

		public E populate(E e);
	}

	/**
	 * 
	 * exemplo de uso
	 * 
	 * TipoItem item = new IEntity<TipoItem>() {}.createNew((e) -> {
	 * e.setParam1(valor1); e.setParam2(valor2); ... e.setParamN(valorN); return e;
	 * });
	 * 
	 */
}
