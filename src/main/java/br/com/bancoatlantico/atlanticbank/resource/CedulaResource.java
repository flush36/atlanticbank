package br.com.bancoatlantico.atlanticbank.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancoatlantico.atlanticbank.dto.CedulaDTO;
import br.com.bancoatlantico.atlanticbank.dto.MessageErrorDTO;
import br.com.bancoatlantico.atlanticbank.model.Cedula;
import br.com.bancoatlantico.atlanticbank.service.CedulaService;

@RestController
@RequestMapping("/cedula")
public class CedulaResource {

	@Autowired
	CedulaService cedulaService;
	
	@PostMapping(name="/cadastrar")
	public ResponseEntity<?> cadastrarCedula(@RequestBody CedulaDTO cedulaDTO) throws MessageErrorDTO {
		try {
			return ResponseEntity.ok(cedulaService.cadastrarCedulasService(cedulaDTO));
		} catch (MessageErrorDTO e) {
			throw new MessageErrorDTO("a quantidade de cedulas deve ser maior que 0");
		}
		
	}
	
	@GetMapping
	public ResponseEntity<List<Cedula>> getCedulas() {
		return ResponseEntity.ok(cedulaService.getCedulas());
	}
}
