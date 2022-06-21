package br.com.utilities.utils;

import java.io.PrintStream;
import java.io.PrintWriter;

import com.google.gson.JsonObject;

import br.com.utilities.BuildConfig;

public class ExceptionUtils {

	public static void showStacktrace(Exception e) {
		if (BuildConfig.SHOW_STACKTRACE) {
			e.printStackTrace();
		}
	}

	public static void showStacktrace(Exception e, PrintStream s) {
		if (BuildConfig.SHOW_STACKTRACE) {
			e.printStackTrace(s);
		}
	}

	public static void showStacktrace(Exception e, PrintWriter s) {
		if (BuildConfig.SHOW_STACKTRACE) {
			e.printStackTrace(s);
		}
	}

	public static String toJson(Exception e) {
		JsonObject o = new JsonObject();
		o.addProperty("status", "error");
		o.addProperty("error", e.getMessage());
		return o.toString();
	}
}
