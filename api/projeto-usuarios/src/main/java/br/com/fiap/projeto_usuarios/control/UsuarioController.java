package br.com.fiap.projeto_usuarios.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.projeto_usuarios.model.ChaveCompostaUsuario;
import br.com.fiap.projeto_usuarios.model.Usuario;
import br.com.fiap.projeto_usuarios.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repU;
	
	@GetMapping(value = "/todos")
	public List<Usuario> listarTodos(){
		return repU.findAll();
	}
	
	@PostMapping("/novo")
	public String inserirUsuario(@RequestBody Usuario usuario) {
		repU.save(usuario);
		return "Usuário inserido: " + usuario.getChaveComposta().getRm();
	}
	
	@GetMapping("/chave_composta")
	public Usuario retornarUsuarioPorChaveComposta(@RequestParam Long id, 
			@RequestParam String rm) {
		
		ChaveCompostaUsuario chave_composta = new ChaveCompostaUsuario(id, rm);
		
		Optional<Usuario> op = repU.findById(chave_composta);
		
		if(op.isPresent()) {
			return op.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		
	}
	

}
