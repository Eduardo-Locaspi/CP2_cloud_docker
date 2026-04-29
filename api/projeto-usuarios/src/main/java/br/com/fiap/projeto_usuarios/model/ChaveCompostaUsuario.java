package br.com.fiap.projeto_usuarios.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class ChaveCompostaUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String rm;

	public ChaveCompostaUsuario() {

	}

	public ChaveCompostaUsuario(Long id, String rm) {
		super();
		this.id = id;
		this.rm = rm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRm() {
		return rm;
	}

	public void setRm(String rm) {
		this.rm = rm;
	}

}
