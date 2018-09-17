package br.com.bancoatlantico.atlanticbank.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bancoatlantico.atlanticbank.dto.CedulaDTO;
import br.com.bancoatlantico.atlanticbank.dto.ContaDTO;
import br.com.bancoatlantico.atlanticbank.dto.ErroDTO;
import br.com.bancoatlantico.atlanticbank.dto.ExtratoDTO;
import br.com.bancoatlantico.atlanticbank.erros.AlertErrorException;
import br.com.bancoatlantico.atlanticbank.erros.MessageErrorException;
import br.com.bancoatlantico.atlanticbank.model.Cedula;
import br.com.bancoatlantico.atlanticbank.model.Conta;
import br.com.bancoatlantico.atlanticbank.model.Usuario;
import br.com.bancoatlantico.atlanticbank.repository.CedulaRepository;
import br.com.bancoatlantico.atlanticbank.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	ContaRepository contaRepository;
	
	@Autowired
	CedulaRepository cedulaRepository;
	
	public List<CedulaDTO> quantidadeCedulasParaPagar(ContaDTO contaDTO) throws MessageErrorException {
		 if(contaDTO.getValor() == null || contaDTO.getValor() < 2 || contaDTO.getValor() == 3) {
			 throw new MessageErrorException(new ErroDTO("Valor indisponivel para saque."));
		 }
		 List<CedulaDTO> processarNotas = processarNotas(contaDTO.getValor());
		return processarNotas;
	}
	
	public Conta sacar(Usuario usuario, ContaDTO contaDTO) throws MessageErrorException {
		Conta contaUserBD = contaRepository.getByUser(usuario);
		if(contaUserBD.getSaldo() < contaDTO.getValor()) {
			throw new MessageErrorException(new ErroDTO("Valor indisponivel para saque."));
		}
		if(contaDTO.getValor() > 10000) {
			throw new MessageErrorException(new ErroDTO("Valor máximo para saque 10.000R$."));
		}
		contaUserBD.setSaldo(subtrairSaldo(contaUserBD.getSaldo(), contaDTO.getValor()));
		contaUserBD.setSaque(contaDTO.getValor());
		return contaRepository.save(contaUserBD);
	}
	
	private double subtrairSaldo(double valorSaldo, double valorSaque) {
		return valorSaldo - valorSaque;
	}
	
	private double somarSaldo(double valorSaldo, double valorDeposito) {
		return valorSaldo + valorDeposito;
	}
	
	public ExtratoDTO montarExtrato(Usuario usuario, List<CedulaDTO> cedulas, Conta conta) {
		ExtratoDTO extrato = new ExtratoDTO();
		extrato.setCedulas(cedulas);
		extrato.setDataAtual(new Date());
		extrato.setNome(usuario.getNome());
		extrato.setSaldoAtual(conta.getSaldo());
		extrato.setValorSaque(conta.getSaque());
		return extrato;
	}
	

	
	
	public List<CedulaDTO> processarNotas(Double valorSaque) throws MessageErrorException {
		CedulaDTO cinquenta = new CedulaDTO(50, 0, "50R$ ");
		CedulaDTO vinte = new CedulaDTO(20, 0, "20R$");
		CedulaDTO dez = new CedulaDTO(10, 0, "10R$");
		CedulaDTO cinco = new CedulaDTO(5, 0, "5R$");
		CedulaDTO dois = new CedulaDTO(2, 0, "2R$");

		Integer qtd_nota50 = 0;
		Integer qtd_nota20 = 0;
		Integer qtd_nota10 = 0;
		Integer qtd_nota5 = 0;
		Integer qtd_nota2 = 0;
		
		if(verificarPossivelPagar(valorSaque)) {
			throw new MessageErrorException(new ErroDTO("Este Caixa não tem essa quantidade disponivel."));
		}
		
		try {
			do {
				if (((valorSaque - cinquenta.getValorReal()) != 1 && (valorSaque - cinquenta.getValorReal()) != 3)
						&& valorSaque >= cinquenta.getValorReal()) {
					while (((valorSaque - cinquenta.getValorReal()) != 1 && (valorSaque - cinquenta.getValorReal()) != 3)
							&& valorSaque >= cinquenta.getValorReal() && verificarQuantidadeCedulasNoCaixa(cinquenta.getValorReal(), qtd_nota50)) {
						valorSaque = valorSaque - cinquenta.getValorReal();
						qtd_nota50++;
					}
				}
				if (((valorSaque - vinte.getValorReal()) != 1 && (valorSaque - vinte.getValorReal()) != 3)
						&& valorSaque >= vinte.getValorReal()) {
					while (((valorSaque - vinte.getValorReal()) != 1 && (valorSaque - vinte.getValorReal()) != 3)
							&& valorSaque >= vinte.getValorReal() && verificarQuantidadeCedulasNoCaixa(vinte.getValorReal(), qtd_nota20)) {
						valorSaque = valorSaque - vinte.getValorReal();
						qtd_nota20++;
					}
				}
				if (((valorSaque - dez.getValorReal()) != 1 && (valorSaque - dez.getValorReal()) != 3)
						&& valorSaque >= dez.getValorReal()  ) {
					while (((valorSaque - dez.getValorReal()) != 1 && (valorSaque - dez.getValorReal()) != 3)
							&& valorSaque >= dez.getValorReal() && verificarQuantidadeCedulasNoCaixa(dez.getValorReal(), qtd_nota10)) {
						valorSaque = valorSaque - dez.getValorReal();
						qtd_nota10++;
					}
				}
				if (((valorSaque - cinco.getValorReal()) != 1 && (valorSaque - cinco.getValorReal()) != 3)
						&& valorSaque >= cinco.getValorReal()) {
					while (((valorSaque - cinco.getValorReal()) != 1 && (valorSaque  - cinco.getValorReal()) != 3)
							&& valorSaque >= cinco.getValorReal() && verificarQuantidadeCedulasNoCaixa(cinco.getValorReal(), qtd_nota5)) {
						valorSaque = valorSaque - cinco.getValorReal();
						qtd_nota5++;
					}
				}
				if (((valorSaque - dois.getValorReal()) != 1 && (valorSaque - dois.getValorReal()) != 3)
						&& valorSaque >= dois.getValorReal()) {
					while (((valorSaque - dois.getValorReal()) != 1 && (valorSaque - dois.getValorReal()) != 3)
							&& valorSaque >= dois.getValorReal() && verificarQuantidadeCedulasNoCaixa(dois.getValorReal(), qtd_nota2)) {
						valorSaque = valorSaque - dois.getValorReal();
						qtd_nota2++;
					}
				}
			} while (valorSaque > 0);

			cinquenta.setQuantidade(qtd_nota50);
			vinte.setQuantidade(qtd_nota20);
			dez.setQuantidade(qtd_nota10);
			cinco.setQuantidade(qtd_nota5);
			dois.setQuantidade(qtd_nota2);

			List<CedulaDTO> cedulas = new ArrayList<>();
			cedulas.add(cinquenta);
			cedulas.add(vinte);
			cedulas.add(dez);
			cedulas.add(cinco);
			cedulas.add(dois);
			return cedulas;
		}catch (Exception e) {
			throw new MessageErrorException(new ErroDTO("Erro insperado, favor procurar o gerente mais próximo.."));
		}
		
		
	}

	public Conta depositar(ContaDTO contaDTO, Usuario usuario) throws MessageErrorException {
		if(contaDTO.getValor() <= 0 ) {
			throw new MessageErrorException(new ErroDTO("Por favor inserir o valor a ser depositado."));
		}
		Conta contaUserBD = contaRepository.getByUser(usuario);
		contaUserBD.setSaldo(somarSaldo(contaUserBD.getSaldo(), contaDTO.getValor()));
		contaUserBD.setDeposito(contaDTO.getValor());
		return contaRepository.save(contaUserBD);
	}
	
	
	private boolean verificarQuantidadeCedulasNoCaixa(Integer valorReal, Integer quantidade) {
		
			Cedula cedulaSelecionada = cedulaRepository.selecionarCedula(valorReal);
			if(cedulaSelecionada.getQuantidade() <= quantidade) {
				return false;
			}
			return true;
	}
	
	private boolean verificarPossivelPagar(Double valorSaque) {
		List<Cedula> cedulas = cedulaRepository.findAll();
		double saqueMaximo = 0;
		for (Cedula cedula : cedulas) {
			saqueMaximo += (cedula.getQuantidade() * cedula.getValorReal());
		}
		if(saqueMaximo < valorSaque)
			return true;
		return false;
	}

}
