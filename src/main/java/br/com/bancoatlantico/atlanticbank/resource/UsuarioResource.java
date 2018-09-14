package br.com.bancoatlantico.atlanticbank.resource;

import java.util.Optional;

import br.com.bancoatlantico.atlanticbank.dto.ErroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancoatlantico.atlanticbank.dto.MessageErrorException;
import br.com.bancoatlantico.atlanticbank.model.Usuario;
import br.com.bancoatlantico.atlanticbank.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<?> getUsuario() {
		Optional<Usuario> usuarioLogado = usuarioService.getUsuarioService();
		if(usuarioLogado.isPresent()) {
			return ResponseEntity.ok(usuarioLogado);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroDTO("Usuario não encontrado"));
	}	
	
}
