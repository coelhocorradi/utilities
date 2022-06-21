package br.com.utilities.regexes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CNPJ {

	public static final CNPJ parse(String cnpj) {
		return new CNPJ(cnpj);
	}

	private String cnpj;

	private String inscricao;

	private String loja;

	private String digitoVerificador;

	private String pureNumber;

	private Boolean isCnpj;

	private CNPJ(String cnpj) {
		Pattern p = Pattern.compile(RegexUtils.cnpj);
		Matcher m = p.matcher(cnpj);
		if (m.find()) {
			this.cnpj = cnpj;
			this.inscricao = m.group(1).concat(m.group(2)).concat(m.group(3));
			this.loja = m.group(4);
			this.digitoVerificador = m.group(5);
			this.pureNumber = this.cnpj.replaceAll("\\s|\\-|\\/|\\.", "");
			this.isCnpj = true;
		} else {
			this.cnpj = cnpj;
			this.isCnpj = false;
		}
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getInscricao() {
		return inscricao;
	}

	public String getLoja() {
		return loja;
	}

	public String getDigitoVerificador() {
		return this.digitoVerificador;
	}

	public Boolean isCnpj() {
		return isCnpj;
	}

	public String getPureNumber() {
		return pureNumber;
	}

}
