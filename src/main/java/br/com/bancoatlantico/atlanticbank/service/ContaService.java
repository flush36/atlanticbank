package br.com.bancoatlantico.atlanticbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.bancoatlantico.atlanticbank.dto.ContaDTO;
import br.com.bancoatlantico.atlanticbank.model.Conta;
import br.com.bancoatlantico.atlanticbank.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	ContaRepository contaRepository;
	
	public Conta cadastrarSaldoService(@RequestBody ContaDTO contaDTO) {
		return contaRepository.getContaByToken(contaDTO.getToken());
	}
	
}
