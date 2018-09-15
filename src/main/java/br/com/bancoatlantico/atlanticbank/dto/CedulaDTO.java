package br.com.bancoatlantico.atlanticbank.dto;

public class CedulaDTO {

	private Integer valorReal;
	
	private Integer quantidade;

	private String descricao;


	public CedulaDTO(Integer valor, Integer quantidade, String descricao) {
		this.valorReal = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getValorReal() {
		return valorReal;
	}

	public void setValorReal(Integer valorReal) {
		this.valorReal = valorReal;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
