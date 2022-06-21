package br.com.utilities.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.spec.KeySpec;
import java.sql.Blob;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.sql.rowset.serial.SerialBlob;

//import com.sun.istack.internal.NotNull;

import br.com.utilities.constants.Masks;
import br.com.utilities.enums.ECountriesRegions;
import br.com.utilities.regexes.RegexUtils;

/**
 * 
 * @author gustavo
 *
 */
public abstract class StringUtils {

	private final static int asc = 249;
	// private final static int NUM_CHARS = 5;

	private static SecretKey skey;
	private static KeySpec ks;
	private static PBEParameterSpec ps;
	private static final String algorithm = "PBEWithMD5AndDES";
	private static Encoder enc = Base64.getEncoder();
	private static Decoder dec = Base64.getDecoder();

	private static final int iterationCount = 20;
	private static final byte[] paramsSpec = new byte[] { 3, 1, 4, 1, 5, 9, 2, 6 };
	private static final char[] secretKey = "EAlGeEen3/m8/YkO".toCharArray();

	static {
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithm);
			ps = new PBEParameterSpec(paramsSpec, iterationCount);
			ks = new PBEKeySpec(secretKey);
			skey = skf.generateSecret(ks);
		} catch (java.security.NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		} catch (java.security.spec.InvalidKeySpecException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * if find into value some pattern values return true, or false otherwise.
	 * 
	 * @param value
	 * @param pattern - regex expression
	 * @return
	 */
	public static boolean has(String value, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(value);
		return m.find();
	}

	/**
	 * remove _|-|\|/|.|\s|(|) of all strings
	 * 
	 * @param value - String contendo o campo com mascara
	 * @return String com os valores sem a mascara
	 */
	public static String removeMask(String value) {
		if (value != null) {
			value = value.replaceAll(RegexUtils.mask001, "");
			/*
			 * value = value.replace("-", "").replace("_",
			 * "").replace("\\", "").replace("/", "").replace("(", "") .replace(")",
			 * "").replace(".", "").replace(" ", "");
			 */
			return value.isEmpty() ? null : value;
		}
		return null;
	}

	/**
	 * Converte um Blob para String.
	 * 
	 * @param blob
	 * @return
	 */
	public static String blodToString(Blob blob) {
		String result = null;
		if (blob != null) {
			try {
				StringBuilder texto = new StringBuilder();

				BufferedReader br = new BufferedReader(new InputStreamReader(blob.getBinaryStream()));

				while (br.ready()) {
					br.read();
				}
				/*
				 * int i = 0; //byte b[] = new byte[2048]; char b[] = new char[2048]; while ((i
				 * = br.read(b)) != -1) { texto.append(new String(b, 0, i)); }
				 */
				result = texto.toString().trim();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Converte uma String para Blob.
	 * 
	 * @param str
	 * @return
	 */
	public static Blob stringToBlob(String str) {
		Blob result = null;
		try {
			result = new SerialBlob(str.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isValidMail(String email) {
		boolean result = false;
		if (email != null) {
			result = Pattern.compile(RegexUtils.maskEmail).matcher(email).matches();
		}
		return result;
	}

	/**
	 * Valida se um valor de cpf é válido
	 * 
	 * @param cpfSemFormatacao - string do cpf sem formata
	 * @return true se for válido, false caso contrário
	 */
	public static boolean isCPFValido(String cpfSemFormatacao) {

		if (cpfSemFormatacao.length() != 11 || cpfSemFormatacao.equals("00000000000")
				|| cpfSemFormatacao.equals("99999999999"))
			return false;

		String digitos = cpfSemFormatacao.substring(0, 9);
		String dvs = cpfSemFormatacao.substring(9, 11);

		String dv1 = gerarDV(digitos);
		String dv2 = gerarDV(digitos + dv1);

		return dvs.equals(dv1 + dv2);
	}

	/**
	 * gerar os digitos verificadores necessários para validação do cpf
	 * 
	 * @param digitos - String necessária para gerar o digito verificador
	 * @return o digito verificador
	 */
	private static String gerarDV(String digitos) {
		int peso = digitos.length() + 1;
		int dv = 0;

		for (int i = 0; i < digitos.length(); i++) {
			dv += Integer.parseInt(digitos.substring(i, i + 1)) * peso;
			peso--;
		}

		dv = 11 - (dv % 11);

		if (dv > 9) {
			return "0";
		}

		return String.valueOf(dv);
	}

	public static String randomString(char[] sequence, int numOfCharacters) {
		Random r = new Random();
		char[] buf = new char[numOfCharacters];

		for (int i = 0; i < buf.length; i++) {
			buf[i] = sequence[r.nextInt(sequence.length)];
		}
		return new String(buf);
	}

	private static final char[] numeric = "1234567890".toCharArray();

	private static final char[] literal = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	private static final char[] literalENumeric = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();

	public static String randomStringLiteral(int numOfChars) {
		return randomString(literal, numOfChars);
	}

	public static String randomStringNumeric(int numOfChars) {
		return randomString(numeric, numOfChars);
	}

	public static String randomLiteralAndNumeric(int numOfChars) {
		return randomString(literalENumeric, numOfChars);
	}

	/*
	 * public static String randomLiteralPassword() { return randomString(literal,
	 * NUM_CHARS); }
	 * 
	 * public static String randomNumericPassword() { return randomString(numeric,
	 * NUM_CHARS); }
	 * 
	 * public static String randomNumericAndLiteralPassword() { return
	 * randomString(literalENumeric, NUM_CHARS); }
	 */

	/**
	 * 
	 * @param senha
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String criptografarSenha(String senha) throws UnsupportedEncodingException {
		if (senha == null) {
			return null;
		}
		String senhaCriptografada = "";
		for (int tamanhoSenha = senha.toCharArray().length; tamanhoSenha < 10; tamanhoSenha++)
			senha += "�";

		for (char caracter : senha.toCharArray())
			senhaCriptografada += (char) ((caracter) + 127);
		return new String(new StringBuilder("").append(senhaCriptografada.charAt(0))
				.append(senhaCriptografada.charAt(3)).append(senhaCriptografada.charAt(2))
				.append(senhaCriptografada.charAt(1)).append(senhaCriptografada.charAt(8))
				.append(senhaCriptografada.charAt(5)).append(senhaCriptografada.charAt(7))
				.append(senhaCriptografada.charAt(6)).append(senhaCriptografada.charAt(9))
				.append(senhaCriptografada.charAt(4)).toString().getBytes("Cp1252"), "LATIN1");
	}

	/**
	 * 
	 * @param senhaCriptografada
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String descriptografarSenha(String senhaCriptografada) throws UnsupportedEncodingException {
		if (senhaCriptografada == null) {
			return null;
		}
		String senha = "";
		int codigoAscii;
		for (char caracter : senhaCriptografada.toCharArray()) {
			codigoAscii = (caracter) - 127;
			if (codigoAscii == asc) {
				senha += " ";
			} else {
				senha += (char) codigoAscii;
			}
		}
		return new String(new StringBuilder("").append(senha.charAt(0)).append(senha.charAt(3)).append(senha.charAt(2))
				.append(senha.charAt(1)).append(senha.charAt(9)).append(senha.charAt(5)).append(senha.charAt(7))
				.append(senha.charAt(6)).append(senha.charAt(4)).append(senha.charAt(8)).toString().trim()
				.getBytes("LATIN1"), "Cp1252").replace("?", "");
	}

	/**
	 * 
	 * @param texto
	 * @return
	 */
	public static final String formatarParaLatin1(String texto) {
		try {
			texto = new String(texto.getBytes(), "LATIN1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return texto;
	}

	/**
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static final String encrypt(final String text) throws Exception {
		final Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, skey, ps);
		return enc.encodeToString(cipher.doFinal());
	}

	/**
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static final String decrypt(final String text) throws Exception {
		String ret = null;
		final Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, skey, ps);
		try {
			ret = new String(cipher.doFinal(dec.decode(text)));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}

	/**
	 * formatar valor para moeda corrente
	 * 
	 * @param valor
	 * @return
	 */
	public static String formatCurrentCoin(Object value) {
		String result = null;
		if (value != null) {
			// TODO - verificar se precisa propgramar o restante
			if (value instanceof Double) {

			} else if (value instanceof Float) {

			} else if (value instanceof Long) {

			} else if (value instanceof BigInteger) {

			} else if (value instanceof BigDecimal) {

			} else if (value instanceof Integer) {

			} else if (value instanceof Short) {

			} // ... outros precisa?
			result = NumberFormat.getCurrencyInstance().format(value);
		}
		return result;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static String normalizeDoubleString(String value) {
		String result = null;
		if (value != null) {
			result = value;
			if (result.matches(RegexUtils.maskDouble)) {
				result.replaceAll(",", ".");
				if (!result.matches(RegexUtils.maskDoubleBefore)) {
					result = "0" + result;
				} else if (!result.matches(RegexUtils.maskDoubleAfter)) {
					result += "0";
				}
			}
			if (result.matches(RegexUtils.maskDoubleNormalizes)) {
				System.out.println("convert " + value + " to " + result + " successfully!");
			} else {
				System.out.println("convert " + value + " not successfully, result is " + result);
			}
		}
		return result;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static String doubleToMoney(Double value) {
		return String.format(Masks.maskMoney, value);
	}

	public static String formatWithPrefix(String prefix, String format, Object value) {
		return (prefix != null ? prefix : "") + String.format(format, value);
	}

	public static String format4dHex(String prefix, Integer value) {
		return formatWithPrefix(prefix, Masks.mask4dHex, value);
	}

	public static String format6dHex(String prefix, Integer value) {
		return formatWithPrefix(prefix, Masks.mask6dHex, value);
	}

	public static String format8dHex(String prefix, Integer value) {
		return formatWithPrefix(prefix, Masks.mask8dHex, value);
	}

	/**
	 * validate value if null, or empty or 0 or 0.0
	 * 
	 * @param o
	 * @return
	 */
	public static final boolean valueIsEmpty(Object o) {
		boolean result = false;
		result = o == null;
		if (!result) {
			result = o.toString().isEmpty() || o.toString().matches(RegexUtils.number0);
		}
		return result;
	}

	public static final boolean isEmpty(String s) {
		return s == null || (s != null && s.trim().isEmpty());
	}

	/**
	 * validar a placa
	 * 
	 * @param board
	 * @return
	 */
	public static final ECountriesRegions validateBoard(String board) {
		ECountriesRegions result = ECountriesRegions.UNKNOW;
		if (board.matches(RegexUtils.boardBR)) {
			result = ECountriesRegions.BR;
		} else if (board.matches(RegexUtils.boardMercosul)) {
			result = ECountriesRegions.Mercosul;
		}
		return result;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static final String[] getOnlyWords(String str) {
		String[] parts = str.split("[^0-9A-Za-z]");
		parts = Arrays.stream(parts).filter(new Predicate<String>() {
			@Override
			public boolean test(String t) {
				return !t.isEmpty();
			}
		}).toArray(String[]::new);
		return parts;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static final String[] getOnlyNumbers(String str) {
		String[] parts = str.split("[^0-9]");
		parts = Arrays.stream(parts).filter(new Predicate<String>() {
			@Override
			public boolean test(String t) {
				return !t.isEmpty();
			}
		}).toArray(String[]::new);
		return parts;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static final String[] getOnlyLetters(String str) {
		String[] parts = str.split("[^A-Za-z]");
		parts = Arrays.stream(parts).filter(new Predicate<String>() {
			@Override
			public boolean test(String t) {
				return !t.isEmpty();
			}
		}).toArray(String[]::new);
		return parts;
	}

	/**
	 * 
	 * @param parts
	 * @return
	 */
	public static final String stringToSqlLike(String[] parts) {
		String result = "%";
		String vg = "";
		for (String part : parts) {
			if (!part.isEmpty()) {
				result += vg + part;
				vg = "%";
			}
		}
		result += "%";
		return result;
	}

	/**
	 * 
	 * @param parts
	 * @return
	 */
	public static final String stringToSqlLikeStartWich(String[] parts) {
		String result = "";
		String vg = "";
		for (String part : parts) {
			if (!part.isEmpty()) {
				result += vg + part;
				vg = "%";
			}
		}
		result += "%";
		return result;
	}

	/**
	 * 
	 * @param parts
	 * @return
	 */
	public static final String stringToSqlLikeEndWich(String[] parts) {
		String result = "%";
		String vg = "";
		for (String part : parts) {
			if (!part.isEmpty()) {
				result += vg + part;
				vg = "%";
			}
		}
		result += "";
		return result;
	}

	/**
	 * 
	 * @param startWich
	 * @param parts
	 * @param endWich
	 * @return
	 */
	public static final String stringToSqlLike(String startWich, String[] parts, String endWich) {
		String result = startWich != null ? (startWich.matches("\\.|%|\\^|\\+|\\?") ? "%" : startWich) : "";
		String vg = "";
		for (String part : parts) {
			if (!part.isEmpty()) {
				result += vg + part;
				vg = "%";
			}
		}
		result = endWich != null ? (endWich.matches("\\.|%|\\$|\\+|\\?") ? "%" : endWich) : "";
		return result;
	}

	public static final String replaceAll(String[] patterns, String[] values, String str) {
		String result = "";
		try {
			if (str == null) {
				throw new Exception("A string informada n�o pode ser nula!");
			}
			if (values == null) {
				throw new Exception("Os valores para substitui��o n�o podem ser nulos!");
			}
			if (patterns == null) {
				throw new Exception("Os valores que ser�o substituidos n�o podem ser nulos!");
			}
			if (values.length != patterns.length) {
				throw new Exception(
						"O n�mero de valores a serem substituidos n�o coincidem com o n�mero de valores para substitui��o!");
			}

			result = str;
			// HashMap<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < Math.min(values.length, patterns.length); i++) {
				result = result.replaceAll(patterns[i], values[i]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static final String replaceAll(HashMap<String, String> map, String str) {
		String result = "";
		try {
			if (map == null) {
				throw new Exception("Os valores a serem substituidos n�o podem ser nulos!");
			}
			if (str == null) {
				throw new Exception("A string informada n�o pode ser nula!");
			}

			result = str;
			for (Entry<String, String> e : map.entrySet()) {
				result = result.replaceAll(e.getKey(), e.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean getValueAsBoolean(String valor, Boolean defValue) {
		boolean result = defValue != null ? defValue : false;
		if (valor != null) {
			switch (valor.trim().toUpperCase()) {
			case "TRUE":
			case "T":
			case "V":
			case "VERDADEIRO":
			case "VERDADE":
			case "YES":
			case "Y":
			case "SIM":
			case "S":
			case "1": {
				result = true;
			}
				break;
			default:
				result = false;
				break;
			}
		}
		return result;
	}

	public static byte getValueAsByte(String valor, Byte defValue) {
		Byte i = defValue != null ? defValue : 0;
		if (valor != null) {
			try {
				i = Byte.valueOf(valor.trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return i.byteValue();
	}

	public static double getValueAsDouble(String valor, Double defValue) {
		Double d = defValue != null ? defValue : 0.0;
		if (valor != null) {
			try {
				d = Double.valueOf(valor.trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return d.doubleValue();
	}

	public static float getValueAsFloat(String valor, Float defValue) {
		Float d = defValue != null ? defValue : 0f;
		if (valor != null) {
			try {
				d = Float.valueOf(valor.trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return d.floatValue();
	}

	public static int getValueAsInteger(String valor, Integer defValue) {
		Integer i = defValue != null ? defValue : 0;
		if (valor != null) {
			try {
				i = Integer.valueOf(valor.trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return i.intValue();
	}

	public static long getValueAsLong(String valor, Integer defValue) {
		Long l = defValue != null ? defValue : 0L;
		if (valor != null) {
			try {
				l = Long.valueOf(valor.trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return l.longValue();
	}

	public static short getValueAsShort(String valor, Short defValue) {
		Short i = defValue != null ? defValue : 0;
		if (valor != null) {
			try {
				i = Short.valueOf(valor.trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return i.shortValue();
	}

	public static String getValueAsString(String valor, String defValue) {
		String d = defValue != null ? defValue : "";
		if (valor != null) {
			d = valor.trim();
		}
		return d;
	}

	public static List<Boolean> getValuesAsBoolean(List<String> values, Boolean... defValues) {
		List<Boolean> result = defValues != null ? Arrays.asList(defValues) : new ArrayList<Boolean>();
		if (values != null && values.size() > 0) {
			result = new ArrayList<Boolean>();
			for (String s : values) {
				result.add(getValueAsBoolean(s, null));
			}
		}
		return result;
	}

	public static List<Byte> getValuesAsByte(List<String> values, Byte... defValues) {
		List<Byte> result = defValues != null ? Arrays.asList(defValues) : new ArrayList<Byte>();
		if (values != null && values.size() > 0) {
			result = new ArrayList<Byte>();
			for (String s : values) {
				result.add(getValueAsByte(s, null));
			}
		}
		return result;
	}

	public static List<Double> getValuesAsDouble(List<String> values, Double... defValues) {
		List<Double> result = defValues != null ? Arrays.asList(defValues) : new ArrayList<Double>();
		if (values != null && values.size() > 0) {
			result = new ArrayList<Double>();
			for (String s : values) {
				result.add(getValueAsDouble(s, null));
			}
		}
		return result;
	}

	public static List<Float> getValuesAsFloat(List<String> values, Float... defValues) {
		List<Float> result = defValues != null ? Arrays.asList(defValues) : new ArrayList<Float>();
		if (values != null && values.size() > 0) {
			result = new ArrayList<Float>();
			for (String s : values) {
				result.add(getValueAsFloat(s, null));
			}
		}
		return result;
	}

	public static List<Integer> getValuesAsInteger(List<String> values, Integer... defValues) {
		List<Integer> result = defValues != null ? Arrays.asList(defValues) : new ArrayList<Integer>();
		if (values != null && values.size() > 0) {
			result = new ArrayList<Integer>();
			for (String s : values) {
				result.add(getValueAsInteger(s, null));
			}
		}
		return result;
	}

	public static List<Long> getValuesAsInteger(List<String> values, Long... defValues) {
		List<Long> result = defValues != null ? Arrays.asList(defValues) : new ArrayList<Long>();
		if (values != null && values.size() > 0) {
			result = new ArrayList<Long>();
			for (String s : values) {
				result.add(getValueAsLong(s, null));
			}
		}
		return result;
	}

	public static List<Short> getValuesAsShort(List<String> values, Short... defValues) {
		List<Short> result = defValues != null ? Arrays.asList(defValues) : new ArrayList<Short>();
		if (values != null && values.size() > 0) {
			result = new ArrayList<Short>();
			for (String s : values) {
				result.add(getValueAsShort(s, null));
			}
		}
		return result;
	}

	public static boolean isNullOrEmpty(String value) {
		return value == null || (value != null && value.trim().isEmpty());
	}

	/**
	 * Avalia se uma string é nula, e retorna a string sem espaços antes ou depois
	 * 
	 * @param value, string a ser avaliada
	 * @return string sem espaços antes ou depois, ou nulo
	 */
	public static String validateNullTrim(String value) {
		return value != null ? value.trim() : null;
	}

	/**
	 * Convert tipos Double, Float, Long, Integer, Short, Byte e Boolean para
	 * string, coloca String entre aspas como "#{value}", e em outros casos execita
	 * o método toString do objeto
	 * 
	 * formata o objeto no padrao {"value" : #{value} , "type" : #{type} } se ext =
	 * true, ou retorna o valor em string como descricao acima
	 * 
	 * util para construir string no estilo de json
	 * 
	 * @param obj
	 * @param ext
	 * @return string que o representa
	 */
	public static String convertToString(Object obj, boolean ext) {
		String result = "null";
		if (obj != null) {
			if (obj instanceof String) {
				result = "\"" + ((String) obj) + "\"";
			} else if (obj instanceof Double) {
				result = Double.toString((Double) obj);
			} else if (obj instanceof Float) {
				result = Float.toString((Float) obj);
			} else if (obj instanceof Long) {
				result = Long.toString((Long) obj);
			} else if (obj instanceof Integer) {
				result = Integer.toString((Integer) obj);
			} else if (obj instanceof Short) {
				result = Short.toString((Short) obj);
			} else if (obj instanceof Byte) {
				result = Byte.toString((Byte) obj);
			} else if (obj instanceof Boolean) {
				result = Boolean.toString((Boolean) obj);
			} else {
				result = ((Object) obj).toString();
			}
			if (ext) {
				// atenção - obj.getClass.getName retorna o nome com o pacote não apenas o nome
				// simples
				// acho melhor deixar assim pois até mesmo facilita saber de onde vem cada
				// pacote
				result = "{ \"value\" : " + result + " , \"type\" : \"" + (obj.getClass().getName()) + "\" }";
			}
		} else {
			if (ext) {
				result = "{ \"value\" : " + result + " , \"type\" : \"null\" }";
			}
		}
		return result;
	}

	/**
	 * Convert tipos Double, Float, Long, Integer, Short, Byte e Boolean para
	 * string, coloca String entre aspas como "#{value}", e em outros casos execita
	 * o método toString do objeto
	 * 
	 * util para construir string no estilo de json
	 * 
	 * @param obj
	 * @return string que o representa
	 */
	public static String convertToString(Object obj) {
		return StringUtils.convertToString(obj, false);
	}

	/**
	 * Convert tipos Double, Float, Long, Integer, Short, Byte e Boolean para
	 * string, coloca String entre aspas como "#{value}", e em outros casos execita
	 * o método toString do objeto
	 * 
	 * formata o objeto no padrao {"value" : #{value} , "type" : #{type} }
	 * 
	 * util para construir string no estilo de json
	 * 
	 * @param obj
	 * @return string que o representa
	 */
	public static String convertToStringExt(Object obj) {
		return StringUtils.convertToString(obj, true);
	}

}
