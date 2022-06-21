package br.com.utilities.config;

public class BuildConfig {

	protected static boolean DEBUG = false;

	public static boolean isDebug() {
		return DEBUG;
	}

	public static void setDebug(boolean value) {
		DEBUG = value;
	}

	public static boolean SHOW_STACKTRACE = false;

	public static boolean showStacktrace() {
		return SHOW_STACKTRACE;
	}

	public static void setShowStacktrace(boolean value) {
		SHOW_STACKTRACE = value;
	}
}
