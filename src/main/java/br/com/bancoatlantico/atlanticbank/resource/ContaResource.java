package br.com.bancoatlantico.atlanticbank.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancoatlantico.atlanticbank.dto.ContaDTO;
import br.com.bancoatlantico.atlanticbank.model.Conta;
import br.com.bancoatlantico.atlanticbank.repository.ContaRepository;
import br.com.bancoatlantico.atlanticbank.service.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaResource {

	@Autowired
	ContaService contaService;
	
	@PostMapping
	public ResponseEntity<?> cadastrarSaldo(@RequestBody ContaDTO contaDTO) {
		Conta contaSalva = contaService.cadastrarSaldoService(contaDTO);
		if(contaSalva !=null) {
			return ResponseEntity.ok(contaSalva);
		}
		return ResponseEntity.badRequest().build();
	}
}
