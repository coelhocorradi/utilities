package br.com.utilities.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import br.com.utilities.constants.Times;
import br.com.utilities.interfaces.IWorker;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.TlsVersion;

/**
 * 
 * @author gustavo
 *
 */
public abstract class HttpUtils {

	public static final Hosts localHost = new Hosts("localhost", "http://localhost:8080");

	/**
	 * @see localHost
	 */
	private static String[] crossOrigins = new String[] {};

	private static HashMap<String, Hosts> hosts = new HashMap<String, Hosts>();

	public Hosts getHostByName(String name) {
		return hosts.get(name);
	}

	public String getHostAddressByName(String name) {
		return hosts.get(name).getHostAddress();
	}

	public String[] getCrossOrigins() {
		return crossOrigins;
	}

	/**
	 * 
	 * @param host
	 * @param value
	 * @return
	 */
	public static String setHost(String host, String value) {
		return (host != null ? host : localHost) + "/" + value;
	}

	private static String urlEncode(String myUrl) {
		return myUrl.replace(" ", "%20");
	}

	public static byte[] httpBytes(String myUrl, String myData, String mimeType, boolean receive) throws Exception {

		URL url = new URL(urlEncode(myUrl));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		if (myData != null) {
			conn.setRequestMethod("POST");
		} else {
			conn.setRequestMethod("GET");
		}
		conn.setRequestProperty("Content-type", mimeType);

		if (myData != null) {
			conn.setDoOutput(true);
			conn.setDoInput(true);
			OutputStream out = new BufferedOutputStream(conn.getOutputStream());
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
			writer.write(myData);
			writer.flush();
			writer.close();
			out.close();
		}

		conn.setReadTimeout(Times._30s);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if (receive) {
			conn.connect();
			int statusCode = conn.getResponseCode();

			if ((statusCode & 200) == 200) {
				BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
				int seek = 0;
				byte[] aux = new byte[1024];
				while ((seek = bis.read(aux)) != -1) {
					baos.write(aux, 0, seek);
				}
			}
		} else {
			conn.connect();
			@SuppressWarnings("unused")
			int statusCode = conn.getResponseCode();
		}

		return baos.toByteArray();
	}

	public static String httpRequest(String myUrl, String myData, String mimeType, String explicitMethodRequest,
			boolean receive) throws Exception {

		URL url = new URL(urlEncode(myUrl));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		if (explicitMethodRequest != null) {
			conn.setRequestMethod(explicitMethodRequest);
		} else if (myData != null) {
			conn.setRequestMethod("POST");
		} else {
			conn.setRequestMethod("GET");
		}

		conn.setRequestProperty("Content-type", mimeType);

		if (myData != null) {
			conn.setDoOutput(true);
			conn.setDoInput(true);
			OutputStream out = new BufferedOutputStream(conn.getOutputStream());
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
			writer.write(myData);
			writer.flush();
			writer.close();
			out.close();
		}

		conn.setReadTimeout(Times._30s);
		String result = null;
		if (receive) {
			conn.connect();
			int statusCode = conn.getResponseCode();

			if ((statusCode & 200) == 200) {
				StringBuilder response = new StringBuilder();
				InputStreamReader isr = new InputStreamReader(conn.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				String line = null;
				while ((line = br.readLine()) != null) {
					response.append(line);
				}
				result = response.toString();
			}
		} else {
			conn.connect();
			@SuppressWarnings("unused")
			int statusCode = conn.getResponseCode();
		}

		return result;
	}

	public static void httpGet(String myUrl, String mimeType) throws Exception {
		httpRequest(myUrl, null, mimeType, null, false);
	}

	public static void httpPost(String myUrl, String myData, String mimeType) throws Exception {
		httpRequest(myUrl, myData, mimeType, null, false);
	}

	public static String httpGetAndReceive(String myUrl, String mimeType) throws Exception {
		return httpRequest(myUrl, null, mimeType, null, true);
	}

	public static String httpPostAndReceive(String myUrl, String myData, String mimeType) throws Exception {
		return httpRequest(myUrl, myData, mimeType, null, true);
	}

	public static String httpsRequest(String myUrl, String myData, String mimeType, String explicitMethodRequest,
			boolean receive) throws Exception {
		URL url = new URL(urlEncode(myUrl));
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

		conn.setSSLSocketFactory(HttpsURLConnection.getDefaultSSLSocketFactory());
		conn.setHostnameVerifier(HttpsURLConnection.getDefaultHostnameVerifier());

		if (explicitMethodRequest != null) {
			conn.setRequestMethod(explicitMethodRequest);
		} else if (myData != null) {
			conn.setRequestMethod("POST");
		} else {
			conn.setRequestMethod("GET");
		}
		conn.setRequestProperty("Content-type", mimeType);

		if (myData != null) {
			conn.setDoOutput(true);
			conn.setDoInput(true);
			OutputStream out = new BufferedOutputStream(conn.getOutputStream());
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
			writer.write(myData);
			writer.flush();
			writer.close();
			out.close();
		}

		String result = null;
		conn.setReadTimeout(Times._30s);
		if (receive) {
			conn.connect();

			int statusCode = conn.getResponseCode();
			if ((statusCode & 200) == 200) {
				StringBuilder response = new StringBuilder();
				InputStreamReader isr = new InputStreamReader(conn.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				String line = null;
				while ((line = br.readLine()) != null) {
					response.append(line);
				}
				result = response.toString();
			}
		} else {
			conn.connect();
			@SuppressWarnings("unused")
			int statusCode = conn.getResponseCode();
		}

		return result;
	}

	public static void httpsGet(String myUrl, String mimeType) throws Exception {
		httpsRequest(myUrl, null, mimeType, null, false);
	}

	public static void httpsPost(String myUrl, String myData, String mimeType) throws Exception {
		httpsRequest(myUrl, myData, mimeType, null, false);
	}

	public static String httpsGetAndReceive(String myUrl, String mimeType) throws Exception {
		return httpsRequest(myUrl, null, mimeType, null, true);
	}

	public static String httpsPostAndReceive(String myUrl, String myData, String mimeType) throws Exception {
		return httpsRequest(myUrl, myData, mimeType, null, true);
	}

	public static String trustEveryoneHttpsRequest(String myUrl, String myData, String mimeType,
			String explicitMethodRequest, boolean receive) throws Exception {

		HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
		SSLContext sslcontext = SSLContext.getInstance("TLS");
		sslcontext.init(null, new X509TrustManager[] { new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) {
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}
		} }, new SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());

		URL url = new URL(urlEncode(myUrl));
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

		if (explicitMethodRequest != null) {
			conn.setRequestMethod(explicitMethodRequest);
		} else if (myData != null) {
			conn.setRequestMethod("POST");
		} else {
			conn.setRequestMethod("GET");
		}
		conn.setRequestProperty("Content-type", mimeType);

		if (myData != null) {
			conn.setDoOutput(true);
			conn.setDoInput(true);

			OutputStream out = new BufferedOutputStream(conn.getOutputStream());
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
			writer.write(myData);
			writer.flush();
			writer.close();
			out.close();
		}

		String result = null;
		conn.setReadTimeout(15 * 1000);
		if (receive) {
			conn.connect();

			int statusCode = conn.getResponseCode();

			StringBuilder response = new StringBuilder();
			if ((statusCode & 200) == 200) {
				InputStreamReader isr = new InputStreamReader(conn.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				String line = null;
				while ((line = br.readLine()) != null) {
					response.append(line);
				}
			}
			result = response.toString();
		} else {
			conn.connect();
			@SuppressWarnings("unused")
			int statusCode = conn.getResponseCode();
		}

		return result;
	}

	public static void trustEveryoneHttpsGet(String myUrl, String mimeType) throws Exception {
		trustEveryoneHttpsRequest(myUrl, null, mimeType, null, false);
	}

	public static void trustEveryoneHttpsPost(String myUrl, String myData, String mimeType) throws Exception {
		trustEveryoneHttpsRequest(myUrl, myData, mimeType, null, false);
	}

	public static String trustEveryoneHttpsGetAndReceive(String myUrl, String myData, String mimeType)
			throws Exception {
		return trustEveryoneHttpsRequest(myUrl, null, mimeType, null, true);
	}

	public static String trustEveryoneHttpsPostAndReceive(String myUrl, String myData, String mimeType)
			throws Exception {
		return trustEveryoneHttpsRequest(myUrl, myData, mimeType, null, true);
	}

	public static String okHttpClientRequest(String myUrl, String myData, String mimeType, String explictMethodRequest,
			boolean receive) throws Exception {
		ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)//
				.tlsVersions(TlsVersion.values())//
				.cipherSuites(CipherSuite.TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA, //
						CipherSuite.TLS_DH_anon_EXPORT_WITH_RC4_40_MD5, //
						CipherSuite.TLS_DH_anon_WITH_3DES_EDE_CBC_SHA, //
						CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA, //
						CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256, //
						CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256, //
						CipherSuite.TLS_DH_anon_WITH_AES_256_CBC_SHA, //
						CipherSuite.TLS_DH_anon_WITH_AES_256_CBC_SHA256, //
						CipherSuite.TLS_DH_anon_WITH_DES_CBC_SHA, //
						CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384, //
						CipherSuite.TLS_DH_anon_WITH_RC4_128_MD5)//
				.build();

		OkHttpClient client = new OkHttpClient.Builder().connectionSpecs(Collections.singletonList(spec)).build();

		Request request = null;
		MediaType mediaType = MediaType.parse(mimeType + "; charset=utf-8");

		if (explictMethodRequest != null) {
			RequestBody requestBody = RequestBody.create(myData != null ? myData : "", mediaType);
			request = new Request.Builder().url(urlEncode(myUrl)).method(explictMethodRequest, requestBody).build();
		} else if (myData != null) {
			RequestBody requestBody = RequestBody.create(myData, mediaType);
			request = new Request.Builder().url(urlEncode(myUrl)).post(requestBody).build();
		} else {
			request = new Request.Builder().url(urlEncode(myUrl)).get().build();
		}

		String result = null;
		if (receive) {
			Response response = client.newCall(request).execute();
			result = response.message();
		} else {
			client.newCall(request).execute();
		}

		return result;
	}

	public static void okHttpClientGet(String myUrl, String myData, String mimeType) throws Exception {
		okHttpClientRequest(myUrl, null, mimeType, null, false);
	}

	public static void okHttpClientPost(String myUrl, String myData, String mimeType) throws Exception {
		okHttpClientRequest(myUrl, myData, mimeType, null, false);
	}

	public static String okHttpClientGetAndReceive(String myUrl, String myData, String mimeType) throws Exception {
		return okHttpClientRequest(myUrl, null, mimeType, null, true);
	}

	public static String okHttpClientPostAndReceive(String myUrl, String myData, String mimeType) throws Exception {
		return okHttpClientRequest(myUrl, myData, mimeType, null, true);
	}

	private static final String CURL_COMMAND_LINE = "curl -H \"Content-Type: __MIME_TYPE__\" -X POST -d __DATA__ __URL__";

	private static String curlPostRequest(String myUrl, String myData, String mimeType, boolean receive)
			throws Exception {
		myData = myData != null ? myData : "";
		String command = CURL_COMMAND_LINE.replace("__MIME_TYPE__", mimeType).replace("__DATA__", myData)
				.replace("__URL__", myUrl);

		Process proc = Runtime.getRuntime().exec(new String[] { "sh", "-c", command });

		String result = null;
		if (receive) {
			StringBuilder sb = new StringBuilder();
			InputStreamReader isr = new InputStreamReader(proc.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			result = sb.toString();
		}

		proc.waitFor();
		return result;
	}

	public static void curlSendPost(String myUrl, String myData, String mimeType) throws Exception {
		curlPostRequest(myUrl, myData, mimeType, false);
	}

	public static String curlSendPostAndReceive(String myUrl, String myData, String mimeType) throws Exception {
		return curlPostRequest(myUrl, myData, mimeType, true);
	}

	public static String curlSendPostAndReceive(String myUrl, String mimeType) throws Exception {
		return curlPostRequest(myUrl, null, mimeType, true);
	}

	public static Map<String, String> urlGetQueryParams(String url) {
		Map<String, String> result = new HashMap<String, String>();
		URI uri = URI.create(url);
		String query = uri.getQuery();
		try {
			for (String par : query.split("&")) {
				String[] arr = par.split("=");
				String name = URLDecoder.decode(arr[0], "UTF-8");
				String value = arr.length > 1 ? URLDecoder.decode(arr[1], "UTF-8") : null;
				result.put(name, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String makeUrl(String baseUrl, Map<String, Object> map) {
		StringBuilder result = new StringBuilder(baseUrl != null ? baseUrl + "?" : "");
		try {
			String vg = "";
			for (Map.Entry<String, Object> e : map.entrySet()) {
				result.append(vg).append(URLEncoder.encode(e.getKey(), "UTF-8")).append("=")
						.append(URLEncoder.encode(TransformUtils.objectToString(e.getValue()), "UTF-8"));
				vg = "&";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	public static String makeUrlByMap(String baseUrl, IWorker<Map<String, Object>> work) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (work != null) {
			work.work(map);
		}
		return makeUrl(baseUrl, map);
	}

	public static String makeUrl(String baseUrl, JsonObject jo) {
		StringBuilder result = new StringBuilder(baseUrl != null ? baseUrl + "?" : "");
		try {
			String vg = "";
			for (Map.Entry<String, JsonElement> e : jo.entrySet()) {
				result.append(vg).append(URLEncoder.encode(e.getKey(), "UTF-8")).append("=")
						.append(URLEncoder.encode(e.getValue().toString(), "UTF-8"));
				vg = "&";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	public static String makeUrlByJson(String baseUrl, IWorker<JsonObject> work) {
		JsonObject jo = new JsonObject();
		if (work != null) {
			work.work(jo);
		}
		return makeUrl(baseUrl, jo);
	}

	public static String postData(Map<String, Object> map) {
		return makeUrl(null, map);
	}
}
