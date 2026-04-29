package br.com.fiap.projeto_usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan
@EntityScan
@EnableJpaRepositories
@SpringBootApplication
public class ProjetoUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoUsuariosApplication.class, args);
	}

}
