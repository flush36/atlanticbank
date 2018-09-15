package br.com.bancoatlantico.atlanticbank.resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancoatlantico.atlanticbank.dto.CedulaDTO;
import br.com.bancoatlantico.atlanticbank.dto.ContaDTO;
import br.com.bancoatlantico.atlanticbank.dto.ErroDTO;
import br.com.bancoatlantico.atlanticbank.dto.ExtratoDTO;
import br.com.bancoatlantico.atlanticbank.erros.AlertErrorException;
import br.com.bancoatlantico.atlanticbank.erros.MessageErrorException;
import br.com.bancoatlantico.atlanticbank.model.Conta;
import br.com.bancoatlantico.atlanticbank.model.Usuario;
import br.com.bancoatlantico.atlanticbank.security.AllowConfiguration;
import br.com.bancoatlantico.atlanticbank.service.CedulaService;
import br.com.bancoatlantico.atlanticbank.service.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaResource {

	@Autowired
	ContaService contaService;
	
	@Autowired
	CedulaService cedulaService;
	
	
	@PostMapping("sacar")
	public ResponseEntity<?> sacar(@RequestBody ContaDTO contaDTO,
			@RequestAttribute(AllowConfiguration.AUTHETICATION) Usuario usuario) {
		try {
			List<CedulaDTO> cedulas = contaService.quantidadeCedulasParaPagar(contaDTO);
			cedulaService.verificarQuantidadeCedulasNoCaixa(cedulas);
			Conta contaAtualizada = contaService.sacar(usuario, contaDTO);
			cedulaService.sacarCedulas(cedulas);
			return ResponseEntity.ok(contaService.montarExtrato(usuario, cedulas, contaAtualizada));
			
		}catch (AlertErrorException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AlertErrorException(new ErroDTO(e.getMensagem().getMsg())));
		}catch (MessageErrorException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new MessageErrorException(new ErroDTO(e.getMensagem().getMsg())));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro interno, favor procurar a gerencia");
		}
	}
	
	@PostMapping("depositar")
	public ResponseEntity<?> depositar(@RequestBody ContaDTO contaDTO, @RequestAttribute(AllowConfiguration.AUTHETICATION) Usuario usuario) {
		 try {
			return ResponseEntity.status(HttpStatus.OK).body(contaService.depositar(contaDTO, usuario));
		} catch (MessageErrorException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new MessageErrorException(new ErroDTO(e.getMensagem().getMsg())));
		}
	}
	
//	@PostMapping
//	public ResponseEntity<?> cadastrarSaldo(@RequestBody ContaDTO contaDTO) {
//		Conta contaSalva = contaService.cadastrarSaldoService(contaDTO);
//		if(contaSalva !=null) {
//			return ResponseEntity.ok(contaSalva);
//		}
//		return ResponseEntity.badRequest().build();
//	}
}
