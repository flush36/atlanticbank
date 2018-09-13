package br.com.bancoatlantico.atlanticbank.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancoatlantico.atlanticbank.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<?> getUsuario() {
		return ResponseEntity.ok(usuarioService.getUsuarioService());
	}	
	
}
