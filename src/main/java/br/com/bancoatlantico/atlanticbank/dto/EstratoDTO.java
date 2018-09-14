package br.com.bancoatlantico.atlanticbank.dto;

import java.util.Date;
import java.util.List;

import br.com.bancoatlantico.atlanticbank.model.Cedula;

public class EstratoDTO {

	private Date dataAtual;
	
	private String nome;
	
	private Double saldoAtual;
	
	private Double valorSaque;
	
	private List<Cedula> cedulas;

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(Double saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public Double getValorSaque() {
		return valorSaque;
	}

	public void setValorSaque(Double valorSaque) {
		this.valorSaque = valorSaque;
	}

	public List<Cedula> getCedulas() {
		return cedulas;
	}

	public void setCedulas(List<Cedula> cedulas) {
		this.cedulas = cedulas;
	}
	
	
}
