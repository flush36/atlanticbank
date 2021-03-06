package br.com.bancoatlantico.atlanticbank.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.bancoatlantico.atlanticbank.dto.ContaDTO;
import br.com.bancoatlantico.atlanticbank.model.Conta;
import br.com.bancoatlantico.atlanticbank.model.Usuario;

public interface ContaRepository extends JpaRepository<Conta, Integer>{

	@Query("SELECT u FROM Conta u WHERE u.fkuser = :usuario")
	public Conta getByUser(@Param("usuario") Usuario usuario);
}
