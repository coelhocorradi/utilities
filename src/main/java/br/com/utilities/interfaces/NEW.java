package br.com.utilities.interfaces;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

import com.google.common.reflect.TypeToken;

public interface NEW {

	public default <E> E create(Instanciate instanciar) {
		try {
			@SuppressWarnings("serial")
			Type type = new TypeToken<E>() {
			}.getType();
			@SuppressWarnings("unchecked")
			Constructor<E> constructor = (Constructor<E>) type.getClass().getConstructor(type.getClass());
			E e = (E) constructor.newInstance();
			return instanciar != null ? instanciar.instanciate(e) : null;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	public interface Instanciate {

		public abstract <E> E instanciate(E e);
	}
}
