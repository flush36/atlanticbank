package br.com.bancoatlantico.atlanticbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.bancoatlantico.atlanticbank.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	@Query("SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha")
	public Usuario logarUserRepository(@Param("login") String login, @Param("senha") String senha );

	@Query("SELECT u FROM Usuario u WHERE u.token = :token")
	public Usuario findByToken(@Param("token") String token);
}
