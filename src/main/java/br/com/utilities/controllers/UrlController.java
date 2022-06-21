package br.com.utilities.controllers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.Charsets;
import org.springframework.http.MediaType;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 * @author gustavo
 *
 */
public class UrlController {

	/**
	 * 
	 * @param paths
	 * @return
	 */
	public String restUrl(String[] paths) {
		String result = "";
		for (String path : paths) {
			result += path;
		}
		return result;
	}

	/**
	 * 
	 * @param paths
	 * @return
	 */
	public String restUrlA(String... paths) {
		String result = "";
		if (paths instanceof String[]) {
			for (String path : paths) {
				result += path;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param conn
	 * @param origin
	 * @return
	 */
	public HttpURLConnection setConnCorsHeaders(HttpURLConnection conn, String origin) {
		conn.addRequestProperty("Origin", origin);
		conn.addRequestProperty("xorigin", origin);
		conn.addRequestProperty("Access-Control-Allow-Origin", origin);
		conn.addRequestProperty("Access-Control-Allow-Methods",
				"API, CRUNCHFYGET, GET, POST, PUT, UPDATE, DELETE, OPTIONS");
		conn.addRequestProperty("Access-Control-Max-Age", "5000");
		conn.addRequestProperty("Access-Control-Allow-Headers", "x-requested-with,Content-Type");
		return conn;
	}

	/**
	 * 
	 * @param conn
	 * @return
	 */
	public HttpURLConnection setConnStream(HttpURLConnection conn) {
		conn.setChunkedStreamingMode(1024);
		conn.setAllowUserInteraction(true);
		return conn;
	}

	/**
	 * send data and receive data by http 'POST' method
	 * 
	 * @param sURL
	 * @param message
	 * @param getResponse
	 * @param origin
	 * @return
	 */
	public Integer postTo(String sURL, String message, Boolean getResponse, String origin) {
		Integer result = null;
		try {
			byte[] postData = message.getBytes();

			URL url = new URL(sURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
			conn.setRequestProperty("charset", Charsets.UTF_8.name());
			conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
			conn.setUseCaches(false);
			if (origin != null) {
				conn = setConnCorsHeaders(conn, origin);
			}

			System.out.println("try to connect to " + sURL);
			conn.connect();
			if (getResponse != null ? getResponse : false) {
				result = conn.getResponseCode();// deixar este aqui tamb�m
			}

			// sending data
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.write(postData);

			if (getResponse != null ? getResponse : false) {
				result = conn.getResponseCode();// deixar este aqui tamb�m
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * send data and receive data by http 'POST' method
	 * 
	 * @param sURL
	 * @param message
	 * @param origin
	 * @return
	 */
	public Integer postToNoResponse(String sURL, String message, String origin) {
		return postTo(sURL, message, null, origin);
	}

	/**
	 * send data and receive data by http 'POST' method
	 * 
	 * @param sURL
	 * @param message
	 * @param origin
	 * @return
	 */
	public String postToAndReceiveFrom(String sURL, String message, String origin) {
		String result = null;
		try {
			byte[] postData = message.getBytes();

			URL url = new URL(sURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
			conn.setRequestProperty("charset", Charsets.UTF_8.name());
			conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
			conn.setUseCaches(false);
			if (origin != null) {
				conn = setConnCorsHeaders(conn, origin);
			}

			System.out.println("try to connect to " + sURL);
			conn.connect();

			// sending data
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.write(postData);

			// receiving data
			result = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while (br.ready()) {
				result += br.readLine();
			}
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * use to send data and receive data by http 'GET' method
	 * 
	 * @param sURL
	 * @param getResponse
	 * @param origin
	 * @return
	 */
	public Integer sendFrom(String sURL, Boolean getResponse, String origin) {
		Integer result = null;
		try {
			URL url = new URL(sURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			if (origin != null) {
				conn = setConnCorsHeaders(conn, origin);
			}

			System.out.println("try to connect to " + sURL);
			conn.connect();

			if (getResponse != null ? getResponse : false) {
				result = conn.getResponseCode();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * use to send data and receive data by http 'GET' method
	 * 
	 * @param sURL
	 * @param origin
	 * @return
	 */
	public Integer onlySend(String sURL, String origin) {
		return sendFrom(sURL, null, origin);
	}

	/**
	 * use to send data and receive data by http 'GET' method
	 * 
	 * @param sURL
	 * @param origin
	 * @return
	 */
	public String receiveFrom(String sURL, String origin) {
		String result = null;
		try {
			URL url = new URL(sURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			if (origin != null) {
				conn = setConnCorsHeaders(conn, origin);
			}

			System.out.println("try to connect to " + sURL);
			conn.connect();

			// receiving data
			result = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while (br.ready()) {
				result += br.readLine();
			}
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * use to send data and receive data by http 'GET' method
	 * 
	 * @param sURL
	 * @param values
	 * @param origin
	 * @return
	 */
	public String receiveFrom(String sURL, Map<String, Object> values, String origin) {
		String result = sURL + "?", aux = "";
		Iterator<Entry<String, Object>> i = values.entrySet().iterator();
		while (i.hasNext()) {
			Entry<String, Object> e = i.next();
			result += aux + e.getKey() + "=" + e.toString();
			aux = "&";
		}
		return receiveFrom(result, origin);
	}

	/**
	 * 
	 * @param url
	 * @param origin
	 * @return
	 */
	public JsonObject getJsonObject(String url, String origin) {
		String jsonString = receiveFrom(url, origin);
		JsonObject jsono = new JsonParser().parse(jsonString).getAsJsonObject();
		// JsonParser.parseString(jsonString).getAsJsonObject();
		return jsono;
	}

	/**
	 * 
	 * @param url
	 * @param origin
	 * @return
	 */
	public JsonArray getJsonArray(String url, String origin) {
		String jsonString = receiveFrom(url, origin);
		JsonArray jsona = new JsonParser().parse(jsonString).getAsJsonArray();
		// JsonParser.parseString(jsonString).getAsJsonArray();
		return jsona;
	}
}
