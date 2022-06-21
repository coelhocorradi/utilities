package br.com.utilities.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.utilities.controllers.UrlController;

public abstract class JobUtils {

	/**
	 * 
	 * @param jobName
	 * @return
	 */
	public static String success(String jobName) {
		return SUCCESS.replace(TAG_SERVICE, jobName);
	}

	/**
	 * 
	 * @param jobName
	 */
	public static void successSysout(String jobName) {
		System.out.println(JobUtils.success(jobName));
	}

	/**
	 * 
	 * @param jobName
	 * @param cause
	 */
	public static void successSysout(String jobName, Throwable cause) {
		System.out.println(JobUtils.success(jobName));
		System.out.println(cause.getMessage());
	}

	/**
	 * 
	 * @param jobName
	 * @return
	 */
	public static String warning(String jobName) {
		return WARNING.replace(TAG_SERVICE, jobName);
	}

	/**
	 * 
	 * @param jobName
	 */
	public static void warningSysout(String jobName) {
		System.out.println(JobUtils.warning(jobName));
	}

	/**
	 * 
	 * @param jobName
	 * @param cause
	 */
	public static void warningSysout(String jobName, Throwable cause) {
		System.out.println(JobUtils.warning(jobName));
		System.out.println(cause.getMessage());
	}

	/**
	 * 
	 * @param jobName
	 * @return
	 */
	public static String error(String jobName) {
		return ERROR.replace(TAG_SERVICE, jobName);
	}

	/**
	 * 
	 * @param jobName
	 */
	public static void errorSysout(String jobName) {
		System.out.println(JobUtils.error(jobName));
	}

	/**
	 * 
	 * @param jobName
	 * @param cause
	 */
	public static void errorSysout(String jobName, Throwable cause) {
		System.out.println(JobUtils.error(jobName));
		System.out.println(cause.getMessage());
	}

	/**
	 * 
	 * @param jobName
	 * @return
	 */
	public static Exception JobException(String jobName) {
		return new Exception(JobUtils.error(jobName));
	}

	/**
	 * 
	 * @param jobName
	 * @param cause
	 * @return
	 */
	public static Exception JobExceptioin(String jobName, Throwable cause) {
		return new Exception(JobUtils.error(jobName), cause);
	}

	/**
	 * use 'POST' method to send messages
	 * 
	 * @param sURL
	 * @param message
	 * @param origin
	 * @return
	 */
	public static Integer updateJob(String sURL, String message, String origin) {
		return new UrlController().postToNoResponse(sURL, message, origin);
	}

	/**
	 * use 'GET' method to send messages
	 * 
	 * @param sURL
	 * @param origin
	 * @return
	 */
	public static Integer updateJob(String sURL, String origin) {
		return new UrlController().onlySend(sURL, origin);
	}

	/**
	 * 
	 * @param id
	 * @param status
	 * @param error
	 * @param chave
	 * @return
	 */
	public static Integer updateJobPost(Integer id, Integer status, String error, Object chave) {
		return updateJob(URL_POST, createJsonString(id, status, error, chave));
	}

	/**
	 * 
	 * @param id
	 * @param status
	 * @param chave
	 * @return
	 */
	public static Integer updateJobPost(Integer id, Integer status, Object chave) {
		return updateJobPost(id, status, null, chave);
	}

	/**
	 * 
	 * @param id
	 * @param chave
	 * @return
	 */
	public static Integer updateJobPost(Integer id, Object chave) {
		return updateJobPost(id, null, null, chave);
	}

	/**
	 * 
	 * @param id
	 * @param status
	 * @param error
	 * @param chave
	 * @param origin
	 * @return
	 */
	public static Integer updateJobGet(Integer id, Integer status, String error, Object chave, String origin) {
		String url = URL_GET//
				.replace(TAG_ID, id != null ? Integer.toString(id) : "")//
				.replace(TAG_STATUS, status != null ? Integer.toString(status) : "")//
				.replace(TAG_ERROR, error != null ? error : "")//
				.replace(TAG_CHAVE, chave != null ? TransformUtils.objectToString(chave) : "");//
		return updateJob(url, origin);
	}

	/**
	 * 
	 * @param id
	 * @param status
	 * @param chave
	 * @param origin
	 * @return
	 */
	public static Integer updateJobGet(Integer id, Integer status, Object chave, String origin) {
		return updateJobGet(id, status, null, chave, origin);
	}

	/**
	 * 
	 * @param id
	 * @param chave
	 * @param origin
	 * @return
	 */
	public static Integer updateJobGet(Integer id, Object chave, String origin) {
		return updateJobGet(id, null, null, chave, origin);
	}

	/**
	 * 
	 * @param id
	 * @param status
	 * @param error
	 * @param chave
	 * @return
	 */
	public static String createJsonString(Integer id, Integer status, String error, Object chave) {
		JsonObject jsono = new JsonObject();
		jsono.addProperty("id", id);
		jsono.addProperty("status", status);
		jsono.addProperty("error", error);
		jsono.addProperty("chave", TransformUtils.objectToString(chave));
		String jsonString = new Gson().toJson(jsono);
		return jsonString;
	}

	public static final String TAG_SERVICE = "<SERVICE>";

	public static final String TAG_ID = "<ID>";

	public static final String TAG_STATUS = "<STATUS>";

	public static final String TAG_ERROR = "<ERROR>";

	public static final String TAG_CHAVE = "<CHAVE>";

	public static final String SUCCESS = "O serviço \"" + TAG_SERVICE + "\" foi executado com sucesso!";

	public static final String WARNING = "O serviço \"" + TAG_SERVICE + "\" foi executado, porém requer atenção!";

	public static final String ERROR = "Ocorreu algum erro ao executar o serviço \"" + TAG_SERVICE + "\"!";

	public static final String URL_POST = "http://10.0.0.39/monitor2/status.php";

	public static final String URL_GET = URL_POST + "?id=<ID>&status=<STATUS>&erro=<ERROR>&chave=<CHAVE>";

	public static final class Status {

		public static final Integer OFF = 0;

		public static final Integer ON = 1;

		public static final Integer END = ON;

		public static final Integer JOB_STARTED = 2;

		public static final Integer PROCESS_STARTED = 3;

		public static final Integer PROCESS_CANCELED = 4;

	}

}
