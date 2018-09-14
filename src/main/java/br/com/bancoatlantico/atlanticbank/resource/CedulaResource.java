package br.com.bancoatlantico.atlanticbank.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancoatlantico.atlanticbank.dto.CedulaDTO;
import br.com.bancoatlantico.atlanticbank.erros.MessageErrorException;
import br.com.bancoatlantico.atlanticbank.model.Cedula;
import br.com.bancoatlantico.atlanticbank.service.CedulaService;

@RestController
@RequestMapping("cedula")
public class CedulaResource {

	@Autowired
	CedulaService cedulaService;
	
	@PostMapping("cadastrar")
	public ResponseEntity<?> cadastrarCedula(@RequestBody CedulaDTO cedulaDTO)  {
		try {
			return ResponseEntity.ok(cedulaService.cadastrarCedulasService(cedulaDTO));
		} catch (MessageErrorException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMensagem());
		}
		
	}


    @PostMapping("sacar")
	public ResponseEntity<?> saqueCedula(@RequestBody CedulaDTO cedulaDTO) {
        try {
            return ResponseEntity.ok(cedulaService.saqueCedula(cedulaDTO));
        } catch (MessageErrorException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMensagem());
        }
    }
	
	@GetMapping
	public ResponseEntity<List<Cedula>> getCedulas() {
		return ResponseEntity.ok(cedulaService.getCedulas());
	}
}
