package br.com.utilities.regexes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Phone {

	public static final Phone Parse(String phoneNumber) {
		return new Phone(phoneNumber);
	}

	private String phoneNumber;

	private String pureNumber;

	private String countryCode;

	private String ddd;

	private String prefix;

	private String ramal;

	private Boolean isCellPhone;

	private Boolean isPhoneNumber;

	private Phone(String phoneNumber) {
		Pattern p = Pattern.compile(RegexUtils.phone);
		Matcher m = p.matcher(phoneNumber);
		if (m.find()) {
			this.isPhoneNumber = true;
			this.phoneNumber = phoneNumber;
			this.countryCode = m.group(1).replaceAll("\\+", "");
			this.ddd = m.group(2);
			this.prefix = m.group(3).replaceAll("\\s", "");
			this.isCellPhone = this.prefix.length() > 4;
			this.ramal = m.group(5);
			this.pureNumber = phoneNumber.replaceAll("\\s|\\+|\\-|\\(|\\)", "");
		} else {
			this.isCellPhone = false;
			this.isPhoneNumber = false;
			this.phoneNumber = phoneNumber;
		}
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public String getDDD() {
		return this.ddd;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public String getRamal() {
		return this.ramal;
	}

	public Boolean isPhoneNumber() {
		return this.isPhoneNumber;
	}

	public Boolean isCellPhone() {
		return this.isCellPhone;
	}

	public String getPureNumber() {
		return this.pureNumber;
	}
}