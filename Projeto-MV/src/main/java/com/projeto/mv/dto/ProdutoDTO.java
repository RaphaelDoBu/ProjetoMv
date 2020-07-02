package com.projeto.mv.dto;

public class ProdutoDTO {
	
	private Long codigoProduto;
	private String descricaoProduto;
	private double precoProduto;
	private double porcentagem;
	
	public ProdutoDTO() {
	}

	public ProdutoDTO(Long codigoProduto, String descricaoProduto, double precoProduto, double porcentagem) {
		this.codigoProduto = codigoProduto;
		this.descricaoProduto = descricaoProduto;
		this.precoProduto = precoProduto;
		this.porcentagem = porcentagem;
	}

	public Long getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public double getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(double precoProduto) {
		this.precoProduto = precoProduto;
	}
	

	public double getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(double porcentagem) {
		this.porcentagem = porcentagem;
	}

}
