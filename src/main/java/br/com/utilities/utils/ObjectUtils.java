package br.com.utilities.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

public class ObjectUtils {

	public static <O> O assign(O dst, O org) {
		if (Objects.nonNull(org)) {
			dst = org;
		} else {
			// verificar se tem como criar objeto construtor aqui
			dst = null;
		}
		return dst;
	}

	@SuppressWarnings("unchecked")
	public static <O> O assingNew(O dst) {
		O result = null;
		if (dst != null) {
			try {
				Class<?> c = Class.forName(dst.getClass().getName());
				Constructor<?> cons = c.getConstructor(String.class);
				result = (O) cons.newInstance();
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public static <O> boolean isEmpty(O o) {
		boolean result = true;
		if (o instanceof List<?>) {
			result = CollectionUtils.isEmpty((List<?>) o);
		} else if (o instanceof Class<?>[]) {
			result = CollectionUtils.isEmpty((Class<?>[]) o);
		} else if (o instanceof Class<?>) {
			result = o == null;
		}
		return result;
	}
}
