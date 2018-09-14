package br.com.bancoatlantico.atlanticbank.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class MessageErrorDTO extends Exception{
	
	private String mensagem;
	
	public MessageErrorDTO(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	
}
