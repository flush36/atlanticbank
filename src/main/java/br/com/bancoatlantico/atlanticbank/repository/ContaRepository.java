package br.com.bancoatlantico.atlanticbank.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.bancoatlantico.atlanticbank.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer>{

}
