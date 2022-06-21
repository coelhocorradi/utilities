package br.com.utilities.schedulers;

import java.io.Serializable;
import java.util.Date;

public class GrRegistroDeServicoStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String id;

	public String grRegistroDeServicoId;

	public String origem;

	public String status;

	public String informacao;

	public Date dataExecucao;

	public Boolean ativo;

	public String secUsersId;

	public GrRegistroDeServicoStatus() {
		super();
	}

	public GrRegistroDeServicoStatus(String id, String grRegistroDeServicoId, String origem, String status,
			String informacao, Date dataExecucao, Boolean ativo, String secUsersId) {
		super();
		this.id = id;
		this.grRegistroDeServicoId = grRegistroDeServicoId;
		this.origem = origem;
		this.status = status;
		this.informacao = informacao;
		this.dataExecucao = dataExecucao;
		this.ativo = ativo;
		this.secUsersId = secUsersId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGrRegistroDeServicoId() {
		return grRegistroDeServicoId;
	}

	public void setGrRegistroDeServicoId(String grRegistroDeServicoId) {
		this.grRegistroDeServicoId = grRegistroDeServicoId;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInformacao() {
		return informacao;
	}

	public void setInformacao(String informacao) {
		this.informacao = informacao;
	}

	public Date getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getSecUsersId() {
		return secUsersId;
	}

	public void setSecUsersId(String secUsersId) {
		this.secUsersId = secUsersId;
	}

}
