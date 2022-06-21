package br.com.utilities.regexes;

public class RegexUtils {

	public static final String mask001 = "_|-|\\|/|\\.|\\s|\\(|\\)";

	public static final String maskEmail = ".+@.+\\.[a-z]+";

	public static final String maskDoubleBefore = "^[0-9]+(\\.)$";

	public static final String maskDoubleAfter = "^(\\.)[0-9+]$";

	public static final String maskDoubleNormalizes = "^[0-9]+(\\.)[0-9]+$";

	public static final String maskDouble = "^[0-9]+((\\.|,)[0-9]*)?$|^[0-9]*((\\.|,)[0-9]+)?$";

	public static final String maskDollar = "^[0-9]+(\\.[0-9]{1,2})?$";

	public static final String number0 = "^0+\\.0+$|^0+(\\.0+)?$|^(0+)?\\.0+$";

	public static final String boardBR = "^[a-zA-Z]{3}\\s[0-9]{4}$";

	public static final String boardMercosul = "^[a-zA-Z0-9]{7}$";

	public static final String fulltime = "^([0-1][0-9]|2[0-3]):([0-5][0-9])(:([0-5][0-9])(\\.([0-9]{3,}))?)?$";

	public static final String datetimemili = "^[0-9]{4}\\-(0[1-9]|1[0-2])\\-([0-2][0-9]|3[0-1])\\s([0-1][0-9]|2[0-3])\\:[0-5][0-9]\\:[0-5][0-9]\\.[0-9]{3}$";

	public static final String datetime = "^[0-9]{4}\\-(0[1-9]|1[0-2])\\-([0-2][0-9]|3[0-1])\\s([0-1][0-9]|2[0-3])\\:[0-5][0-9]\\:[0-5][0-9]$";

	public static final String calendar = "^([0-2][0-9]|3[0-1])\\-(0[1-9]|1[0-2])\\-[0-9]{4}\\s([0-1][0-9]|2[0-3])\\:[0-5][0-9]\\:[0-5][0-9]$";

	public static final String timestamp = "^[0-9]{4}(0[0-9]|1[0-2])([0-2][0-9]|3[0-1])([0-1][0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$";

	public static final String timestampmili = "^[0-9]{4}(0[0-9]|1[0-2])([0-2][0-9]|3[0-1])([0-1][0-9]|2[0-3])([0-5][0-9])([0-5][0-9])([0-9]{3})$";

	public static final String timestampmiliexp = "^[0-9]{4}(0[0-9]|1[0-2])([0-2][0-9]|3[0-1])([0-1][0-9]|2[0-3])([0-5][0-9])([0-5][0-9])([0-9]{3,})$";

	public static final String cnpj = "^(\\d{2})\\.(\\d{3})\\.(\\d{3})\\/(\\d{4})\\-(\\d{2})$";

	/**
	 * grupo 1 - código do país grupo 2 - código ddd grupo 3 - prefixo grupo 4 -
	 * nda. grupo 5 - ramal
	 * 
	 */
	public static final String phone = "^(\\+\\d{2})?\\s*\\((\\d{2})\\)\\s*(\\d\\s\\d{4}|\\d{4,5})(\\-?|\\s*)(\\d{4})$";

	/**
	 * 4 grupos de 4 dígitos cada
	 */
	public static final String cartaocredito = "^(\\d{4})\\-(\\d{4})\\-(\\d{4})\\-(\\d{4})$";

	public static final String cep = "^(\\d{2})(\\d{3})(\\-|\\s)?(\\d{2})$";

	public static final String email = "^([_A-Za-z][_\\-\\.0-9A-Za-z]*)@([_\\-0-9A-Za-z]+)\\.([_\\-0-9A-Za-z]+)(\\.([_\\-0-9A-Za-z]+))?$";

	public static final String dateTimeCanonical = "^\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}:\\d{2}(:\\d{2})?$";

	public static final String dateTimeBr = "^\\d{2}/\\d{2}/\\d{4}\\s\\d{2}:\\d{2}(:\\d{2})?$";

	public static final String dateCanonical = "^\\d{4}\\-\\d{2}\\-\\d{2}$";

	public static final String dateBr = "^\\d{2}/\\d{2}/\\d{4}$";

	public static final String time = "^\\d{2}:\\d{2}(:\\d{2})?$";

	// public static final String dateTimeWithWeekDay =
	// "^\\d{4}\\-(0[0-9]|1[0-2])\\-(0[1-9]|[1-2][0-9]|3[0-1])\\([A-Z]{4}\\)\\s([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])(\\.\\d{3})?$";
	public static final String dateTimeWithWeekDay = "^\\d{4}\\-(0[0-9]|1[0-2])\\-(0[1-9]|[1-2][0-9]|3[0-1])\\([A-Z]{4}\\)\\s([0-1][0-9]|2[0-3]):([0-5][0-9])$";

	// public static final String signedlong = "^\\-?\\d{63}$";

	// public static final String unsignedlong = "^\\d{64}$";

	public static final String datetimeortimestamp = "^((0[1-9]|[1-2][0-9]|3[0-1])\\/(0[1-9]|1[0-2])\\/([0-9]{4})|([0-9]{4})\\-(0[1-9]|1[0-2])\\-(0[1-9]|[1-2][0-9]|3[0-1]))(\\s|'T')([0-1][0-9]|2[0-3])\\:([0-5][0-9])(\\:([0-5][0-9])(\\.([0-9]{3}))?)?('([\\+|\\-]([0-9]|[0-1][0-9]|2[0-3]))')?";

	public static final String longvalue = "^(([0-9]{1,19})([Ll]?))|(([\\+\\-])?([0-9]{1,18})([Ll]?))$";

}
