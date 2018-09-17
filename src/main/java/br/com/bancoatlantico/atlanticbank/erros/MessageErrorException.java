package br.com.bancoatlantico.atlanticbank.erros;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.bancoatlantico.atlanticbank.dto.ErroDTO;

public class MessageErrorException extends Exception{
	
	private ErroDTO mensagem;
	
	public MessageErrorException(ErroDTO mensagem) {
		this.mensagem = mensagem;
	}

	public ErroDTO getMensagem() {
		return mensagem;
	}
}
