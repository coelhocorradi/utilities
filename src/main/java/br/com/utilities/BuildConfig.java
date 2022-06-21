package br.com.utilities;

public class BuildConfig {

	public static boolean DEBUG = false;

	public static void setDebug(boolean debug) {
		DEBUG = debug;
		resolve();
	}

	public static boolean ROTAS_DEBUG = false;

	public static void setRotasDebug(boolean rotasDebug) {
		ROTAS_DEBUG = rotasDebug;
		resolve();
	}

	public static boolean SHOW_STACKTRACE = true;

	public static void setShowStackTrace(boolean showStackTrace) {
		SHOW_STACKTRACE = showStackTrace;
		// resolve();
	}

	public static String ROTA_G_API;

	public static String ROTA_GESTAO_FROTA_API;

	public static String ROTA_JORNADA_API;

	public static String ROTA_EFACCESS_API;

	public static String ROTA_SGT_E_WS;

	public static void resolve() {
		ROTA_G_API = DEBUG && ROTAS_DEBUG ? "http://10.0.0.128/g_api/" : "http://10.0.0.128/g_api/";
		ROTA_GESTAO_FROTA_API = DEBUG && ROTAS_DEBUG ? "http://10.0.0.120/gestao-frota-api/api/v1/"
				: "http://localhost:8080/gestao-frota/api/v1/";
		ROTA_JORNADA_API = DEBUG && ROTAS_DEBUG ? "http://10.0.0.128/g_api/jornada_api/"
				: "http://10.0.0.128/g_api/jornada_api/";
		ROTA_EFACCESS_API = DEBUG && ROTAS_DEBUG ? "http://10.0.0.102/efAccessApi/api/v1/"
				: "http://localhost:8080/efAccessApi/api/v1/";
		ROTA_SGT_E_WS = DEBUG && ROTAS_DEBUG ? "http://www.expressofigueiredo.com.br/sgt-e/ws/"
				: "http://localhost:8080/sgt-e/ws/";
	}

	static {
		resolve();
	}

}
