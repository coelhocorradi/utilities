package br.com.utilities.enums;

public enum EErrosDto {

	NAO_ENCONTRADO(0x0000000000000001L, "Registro não encontrado!"),

	ERRO_DURANTE_GRAVACAO(0x000000000000002L, "Erro durante gravação!"),

	CAMPO_NAO_DEFINIDO(0x000000000000004L, "Campo não definido!"),

	UNKNOW(0x0FFFFFFFFFFFFFFFL, "Erro desconhecido!");

	private Long codErro;

	private String erro;

	private EErrosDto(Long codErro, String erro) {
		this.codErro = codErro;
		this.erro = erro;
	}

	public Long getCodErro() {
		return codErro;
	}

	public String getErro() {
		return erro;
	}

	public static EErrosDto find(Long codErro) {
		EErrosDto result = EErrosDto.UNKNOW;
		boolean achou = false;
		int i = 0;
		if (!achou && i < EErrosDto.values().length) {
			if (Long.compare(EErrosDto.values()[i].codErro, codErro) == 0) {
				result = EErrosDto.values()[i];
				achou = true;
			} else {
				i++;
			}
		}
		return !achou ? result : EErrosDto.UNKNOW;
	}
}
