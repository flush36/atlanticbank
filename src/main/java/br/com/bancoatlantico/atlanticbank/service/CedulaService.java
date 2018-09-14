package br.com.bancoatlantico.atlanticbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bancoatlantico.atlanticbank.dto.CedulaDTO;
import br.com.bancoatlantico.atlanticbank.dto.MessageErrorDTO;
import br.com.bancoatlantico.atlanticbank.model.Cedula;
import br.com.bancoatlantico.atlanticbank.repository.CedulaRepository;

@Service
public class CedulaService {

	@Autowired
	CedulaRepository cedulaRepository;
	
	public Cedula cadastrarCedulasService(CedulaDTO cedulaDTO) throws MessageErrorDTO {
		Cedula cedula = cedulaRepository.inserirQuantidade(cedulaDTO.getValorReal());
		cedula.setQuantidade(somarNotas(cedula.getQuantidade(), cedulaDTO.getQuantidade()));
		return cedulaRepository.save(cedula);
	}
	
	private int somarNotas(int valorAtual, int valorAsomar) throws MessageErrorDTO {
		if(valorAsomar < 0) {
			throw new MessageErrorDTO("valor invalido");
		}
		return valorAtual + valorAsomar;
	}
	
	
	public List<Cedula> getCedulas() {
		return cedulaRepository.findAll();
	}
}

