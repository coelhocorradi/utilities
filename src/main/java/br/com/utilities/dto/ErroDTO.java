package br.com.utilities.dto;

import br.com.utilities.datetime.DateFormats;
import br.com.utilities.datetime.DateUtils;
import br.com.utilities.datetime.DateFormater;
import br.com.utilities.enums.EErrosDto;

public abstract class ErroDTO {

	private String erro;

	private Long codErro;

	private String dateError;

	public ErroDTO() {
		super();
		this.codErro = null;
		this.erro = null;
		this.dateError = null;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public Long getCodErro() {
		return codErro;
	}

	public void setCodErro(Long codErro) {
		this.codErro = codErro;
	}

	public String getDateError() {
		return dateError;
	}

	public void setDateError(String dateError) {
		this.dateError = dateError;
	}

	public void registraErro(Long codErro, String erro) {
		this.codErro = codErro;
		this.erro = erro;
		this.dateError = DateFormater.format(DateFormats.TIMESTAMP, DateUtils.now());
	}

	public void registraErro(EErrosDto erro, String informacao) {
		String str = erro.getErro();
		if (informacao != null) {
			str += "\\r\\n" + informacao;
		}
		registraErro(erro.getCodErro(), str);
	}

	public void registraErro(EErrosDto erro) {
		registraErro(erro, null);
	}

}
