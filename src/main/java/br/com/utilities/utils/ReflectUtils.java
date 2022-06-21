package br.com.utilities.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * @author gustavo
 *
 */
public abstract class ReflectUtils {

	/**
	 * Converte todos os atributos do tipo string de um bean para null Converte o
	 * atributo com a anotação de Id do objeto de 0 para null
	 * 
	 * @param <O>    Um objeto qualquer
	 * @param object
	 * @throws Exception
	 */
	public static final <O> void converterEmptyToNull(O object) throws Exception {

		Class<? extends Object> class1 = object.getClass();

		Field fields[] = class1.getDeclaredFields();

		// Varre os campos para obter os metodos get e set do objeto e em seguida
		// invocalos para
		// setar null caso a string seja nula
		for (Field field : fields) {

			if (field.getType().equals(String.class)) {
				String methodNameGet = generateGetSign(field.getName());
				String methodNameSet = generateSetSign(field.getName());

				Method methodGet = class1.getDeclaredMethod(methodNameGet, String.class);
				Method methodSet = class1.getDeclaredMethod(methodNameSet, String.class);

				String strValue = (String) methodGet.invoke(object, String.class);

				// if (StringUtils.isBlank(strValue)) {
				if (strValue.trim().isEmpty()) {
					strValue = null;
					methodSet.invoke(object, strValue);
				}
			}
			if (field.getType().equals(Character.class)) {
				String methodNameGet = generateGetSign(field.getName());
				String methodNameSet = generateSetSign(field.getName());

				Method methodGet = class1.getDeclaredMethod(methodNameGet, Character.class);
				Method methodSet = class1.getDeclaredMethod(methodNameSet, Character.class);

				Character charValue = (Character) methodGet.invoke(object, Character.class);

				if (!Character.isLetterOrDigit(charValue)) {
					charValue = null;
					methodSet.invoke(object, charValue);
				}
			}

		}

		Method methods[] = object.getClass().getDeclaredMethods();
		String methodName = null;
		Method methodSet = null;

		// Varre os metodos para obter os metodos get e set do objeto e em seguida
		// invocalos para
		// setar null caso o id seja 0
		for (Method method : methods) {
			Annotation annotations[] = method.getAnnotations();
			if (annotations.length > 0) {
				if (annotations[0].annotationType().getName().equals("javax.persistence.Id")) {
					methodName = method.getName();
					Object value = method.invoke(object);
					// Object value = method.invoke(object, null);
					methodName = methodName.replace("get", "set");

					for (Method method2 : methods) {
						if (method2.getName().equals(methodName)) {
							methodSet = method2;
						}
					}
					if (value != null && value.toString().equals("0")) {
						Object x = null;
						methodSet.invoke(object, x);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param field
	 * @return
	 */
	private static final String generateGetSign(String field) {
		String firstChar = field.substring(0, 1);
		field = field.substring(1, field.length());
		field = "get" + firstChar.toUpperCase() + field;
		return field;
	}

	/**
	 * 
	 * @param field
	 * @return
	 */
	private static final String generateSetSign(String field) {
		String firstChar = field.substring(0, 1);
		field = field.substring(1, field.length());
		field = "set" + firstChar.toUpperCase() + field;
		return field;
	}

	/**
	 * force set field when field is private
	 * 
	 * @param obj
	 * @param fieldName
	 * @param value
	 */
	public static final <T> void setField(Object obj, String fieldName, T value) {
		try {
			Field f = obj.getClass().getDeclaredField(fieldName);
			f.setAccessible(true);
			f.set(obj, value);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * force get field when field is private
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final <T> T getField(Object obj, String fieldName) {
		T result = null;
		try {
			Field f = obj.getClass().getDeclaredField(fieldName);
			f.setAccessible(true);
			result = (T) f.get(obj);
		} catch (NoSuchFieldException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * force user method when method is private
	 * 
	 * @param obj
	 * @param methodName
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final <T> T invokeMethod(Object obj, String methodName, Object[] parameters) {
		T result = null;
		try {
			Class<?>[] parameterTypes = null;
			if (parameters != null) {
				parameterTypes = new Class<?>[parameters.length];
				for (int i = 0; i < parameters.length; i++) {
					parameterTypes[i] = parameters[i].getClass();
				}
			} else {
				parameterTypes = new Class<?>[0];
			}
			Method m = obj.getClass().getDeclaredMethod(methodName, parameterTypes);
			m.setAccessible(true);
			result = (T) m.invoke(obj, parameters);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * force use constructor to create instance when constructor is private
	 * 
	 * @param clazz
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final <T> T create(Class<?> clazz, Object[] parameters) {
		T result = null;
		try {
			Class<?>[] parameterTypes = null;
			if (parameters != null) {
				parameterTypes = new Class<?>[parameters.length];
				for (int i = 0; i < parameters.length; i++) {
					parameterTypes[i] = parameters[i].getClass();
				}
			} else {
				parameterTypes = new Class<?>[0];
			}
			Constructor<T> c = (Constructor<T>) clazz.getConstructor(parameterTypes);
			c.setAccessible(true);
			result = c.newInstance(parameters);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}
}
