package br.com.fiap.projeto_usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.projeto_usuarios.model.ChaveCompostaUsuario;
import br.com.fiap.projeto_usuarios.model.Usuario;

public interface UsuarioRepository extends
JpaRepository<Usuario,ChaveCompostaUsuario> {

}
