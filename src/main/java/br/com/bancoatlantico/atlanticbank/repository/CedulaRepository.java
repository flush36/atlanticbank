package br.com.bancoatlantico.atlanticbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.bancoatlantico.atlanticbank.model.Cedula;

public interface CedulaRepository extends JpaRepository<Cedula, Integer>{

	@Query("SELECT u FROM Cedula u WHERE u.valorReal = :valorReal")
	public Cedula inserirQuantidade(@Param("valorReal") int valorReal);
}
