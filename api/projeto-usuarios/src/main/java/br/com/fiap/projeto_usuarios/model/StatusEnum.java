package br.com.fiap.projeto_usuarios.model;

public enum StatusEnum {

	ATIVO("Ativo"), INATIVO("Inativo"), BLOQUEADO("Bloqueado"), SUSPENSO("Suspenso");
	
	private String descricao;
	
	StatusEnum(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}
