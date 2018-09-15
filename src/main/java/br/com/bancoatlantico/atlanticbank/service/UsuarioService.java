package br.com.bancoatlantico.atlanticbank.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bancoatlantico.atlanticbank.dto.ErroDTO;
import br.com.bancoatlantico.atlanticbank.dto.UsuarioDTO;
import br.com.bancoatlantico.atlanticbank.erros.MessageErrorException;
import br.com.bancoatlantico.atlanticbank.model.Usuario;
import br.com.bancoatlantico.atlanticbank.repository.UsuarioRepository;
import br.com.bancoatlantico.atlanticbank.util.Utils;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public Usuario logarUserService(UsuarioDTO usuarioDTO) throws MessageErrorException {
			if(usuarioDTO.getLogin() != null && usuarioDTO.getSenha() != null) {
				 Usuario usuarioLogado = usuarioRepository.logarUserRepository
						(usuarioDTO.getLogin(), Utils.md5(usuarioDTO.getSenha()));
				 usuarioLogado.setToken(gerarToken(usuarioDTO.getLogin(), usuarioDTO.getSenha()));
				 usuarioRepository.save(usuarioLogado);
				 return usuarioLogado;
			}
			throw new MessageErrorException(new ErroDTO("Os dados de login são obrigatórios"));
	}
	
	private String gerarToken(String login, String senha) {
		return Utils.md5(Math.random() * 10+0 + login + senha + new Date().getTime());
	}

	public Usuario findByToken(String token) throws MessageErrorException {
		 Usuario findByToken = usuarioRepository.findByToken(token);
		 if(findByToken == null ) {
			 throw new MessageErrorException(new ErroDTO("Não existe uma sessão para este usuario."));
		 }
		return findByToken;
	}
}
