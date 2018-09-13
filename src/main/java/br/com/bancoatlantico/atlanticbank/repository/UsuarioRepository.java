package br.com.bancoatlantico.atlanticbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bancoatlantico.atlanticbank.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
