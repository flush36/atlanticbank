package br.com.bancoatlantico.atlanticbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bancoatlantico.atlanticbank.repository.CedulaRepository;

@Service
public class CedulaService {

	@Autowired
	CedulaRepository cedulaRepository;
}
