package br.com.utilities;

/**
 * 
 * @author gustavo
 *
 */
public abstract class Debug {

	private static boolean DEBUG = false;

	/**
	 * 
	 * @return
	 */
	public static boolean isDebug() {
		return DEBUG;
	}

	/**
	 * 
	 * @param debugMode
	 */
	public static void setDebugMode(boolean debugMode) {
		DEBUG = debugMode;
	}

	/**
	 * 
	 */
	public static void debugOn() {
		DEBUG = true;
	}

	/**
	 * 
	 */
	public static void debugOff() {
		DEBUG = false;
	}
}
