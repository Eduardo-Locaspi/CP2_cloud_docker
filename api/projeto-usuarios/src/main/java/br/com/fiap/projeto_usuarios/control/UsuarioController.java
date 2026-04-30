package br.com.fiap.projeto_usuarios.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	
	// UPDATE - Atualizar dados do usuário
    @PutMapping("/atualizar/{id}/{rm}")
    public Usuario atualizar(
            @PathVariable Long id, 
            @PathVariable String rm, 
            @RequestBody Usuario dadosAtualizados) {
        
        ChaveCompostaUsuario chave = new ChaveCompostaUsuario(id, rm);
        
        return repU.findById(chave).map(usuario -> {
            // Atualiza os campos permitidos
            usuario.setSenha(dadosAtualizados.getSenha());
            usuario.setStatus(dadosAtualizados.getStatus());
            usuario.setPessoa(dadosAtualizados.getPessoa());
            
            return repU.save(usuario);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }
	
    
 // DELETE - Remover usuário
    @DeleteMapping("/excluir/{id}/{rm}")
    public void excluir(@PathVariable Long id, @PathVariable String rm) {
        ChaveCompostaUsuario chave = new ChaveCompostaUsuario(id, rm);
        
        // Verifica se existe antes de deletar para evitar erro 500
        if (!repU.existsById(chave)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não existe");
        }
        
        repU.deleteById(chave);
    }

}
