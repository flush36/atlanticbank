package br.com.bancoatlantico.atlanticbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bancoatlantico.atlanticbank.dto.CedulaDTO;
import br.com.bancoatlantico.atlanticbank.dto.ErroDTO;
import br.com.bancoatlantico.atlanticbank.erros.MessageErrorException;
import br.com.bancoatlantico.atlanticbank.model.Cedula;
import br.com.bancoatlantico.atlanticbank.model.Usuario;
import br.com.bancoatlantico.atlanticbank.repository.CedulaRepository;

@Service
public class CedulaService {

	@Autowired
	CedulaRepository cedulaRepository;
	
	public Cedula cadastrarCedulasService(CedulaDTO cedulaDTO, Usuario usuario) throws MessageErrorException {
		
		try {
			if(!usuario.getTipoUsuario().equals("A")) {
				throw new MessageErrorException(new ErroDTO(
						"Este recurso requer privilegio de adiministrador."));
			}
			Cedula cedula = cedulaRepository.selecionarCedula(cedulaDTO.getValorReal());
			cedula.setQuantidade(somarNotas(cedula.getQuantidade(), cedulaDTO.getQuantidade()));
			return cedulaRepository.save(cedula);
		}catch (NullPointerException e) {
			throw new MessageErrorException(new ErroDTO(
					"Este caixa trabalha apenas com as seguintes cedulas: 50, 20, 10, 5, 2, favor informar valor existente."));
		}
		
		
	}

	public Cedula saqueCedula(CedulaDTO cedulaDTO) throws MessageErrorException{
		try {
				Cedula cedula = cedulaRepository.selecionarCedula(cedulaDTO.getValorReal());
				cedula.setQuantidade(subtrairNotas(cedula.getQuantidade(), cedulaDTO.getQuantidade()));
				return cedulaRepository.save(cedula);
		}catch (Exception e) {
			throw new MessageErrorException(new ErroDTO(
					"Ocorreu um erro ao tentar efetuar seu pagamento, procure um gerente e informe o ocorrido."));
		}
	}

	
	private int somarNotas(int valorAtual, int valorAsomar) throws MessageErrorException {
		if(valorAsomar < 0) {
			throw new MessageErrorException(new ErroDTO("O valor não pode ser negativo"));
		}
		return valorAtual + valorAsomar;
	}

	private int subtrairNotas(int valorAtual, int valorSubtrair) throws MessageErrorException {
		if(valorSubtrair > valorAtual || valorSubtrair < 0 ) {
			throw new MessageErrorException(new ErroDTO("Quantidade de notas indisponivel para saque"));
		}
		return valorAtual - valorSubtrair;
	}
	
	public void sacarCedulas(List<CedulaDTO> cedulas) throws Exception {
		for (CedulaDTO cedulaDTO : cedulas) {
			saqueCedula(cedulaDTO);
		}
	}
	
	public List<Cedula> getCedulas() {
		return cedulaRepository.findAll();
	}
}

