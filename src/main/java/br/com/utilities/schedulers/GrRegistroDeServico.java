package br.com.utilities.schedulers;

import java.io.Serializable;
import java.util.Date;

public class GrRegistroDeServico implements Serializable {

	private static final long serialVersionUID = -1091534739263145795L;

	private String id;

	private String nome;

	private String descricao;

	private String caminho;

	private String expressaoCron;

	private Boolean servicoAtivo;

	private Boolean reagendarServico;

	private Date dataCriacao;

	private Date dataAtualizacao;

	private Date ultimaExecucao;

	private Boolean ativo;

	private String secUsersId;

	public GrRegistroDeServico() {
		super();
	}

	public GrRegistroDeServico(String id, String nome, String descricao, String caminho, String expressaoCron,
			Boolean servicoAtivo, Boolean reagendarServico, Date dataCriacao, Date dataAtualizacao, Date ultimaExecucao,
			Boolean ativo, String secUsersId) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.caminho = caminho;
		this.expressaoCron = expressaoCron;
		this.servicoAtivo = servicoAtivo;
		this.reagendarServico = reagendarServico;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
		this.ultimaExecucao = ultimaExecucao;
		this.ativo = ativo;
		this.secUsersId = secUsersId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getExpressaoCron() {
		return expressaoCron;
	}

	public void setExpressaoCron(String expressaoCron) {
		this.expressaoCron = expressaoCron;
	}

	public Boolean getServicoAtivo() {
		return servicoAtivo;
	}

	public void setServicoAtivo(Boolean servicoAtivo) {
		this.servicoAtivo = servicoAtivo;
	}

	public Boolean getReagendarServico() {
		return reagendarServico;
	}

	public void setReagendarServico(Boolean reagendarServico) {
		this.reagendarServico = reagendarServico;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Date getUltimaExecucao() {
		return ultimaExecucao;
	}

	public void setUltimaExecucao(Date ultimaExecucao) {
		this.ultimaExecucao = ultimaExecucao;
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
