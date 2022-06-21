package br.com.utilities.utils;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

public abstract class TypeUtils {

	public static <T> Type getType(T t) {
		Type type = new TypeToken<T>() {
		}.getType();
		return type;
	}

	public static <T1, T2> boolean verifyType(T1 t1, T2 t2) {
		Type type1 = getType(t1);
		Type type2 = getType(t2);
		return type1.equals(type2);
	}

	public static <T1, T2> boolean verifyType2(T1 t1, T2 t2) {
		return t1.getClass() == t2.getClass();
	}

}
