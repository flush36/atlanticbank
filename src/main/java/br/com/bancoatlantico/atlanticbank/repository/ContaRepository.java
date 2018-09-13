package br.com.bancoatlantico.atlanticbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bancoatlantico.atlanticbank.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer>{

}
