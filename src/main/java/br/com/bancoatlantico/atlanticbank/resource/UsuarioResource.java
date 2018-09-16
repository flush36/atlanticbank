package br.com.bancoatlantico.atlanticbank.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancoatlantico.atlanticbank.dto.ErroDTO;
import br.com.bancoatlantico.atlanticbank.dto.UsuarioDTO;
import br.com.bancoatlantico.atlanticbank.erros.MessageErrorException;
import br.com.bancoatlantico.atlanticbank.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping("logar")
	public ResponseEntity<?> logar(@RequestBody UsuarioDTO usuarioDTO) {
		try {
			return new ResponseEntity<>(usuarioService.logarUserService(usuarioDTO), HttpStatus.OK);
		}catch (MessageErrorException e) {
			return new ResponseEntity<>(new ErroDTO(e.getMensagem().getMsg()), HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
	}
}
