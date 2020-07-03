package com.projeto.mv.dto;

public class ProdutoDTO {
	
	private Long codigo;
	private String descricao;
	private String nome;
	private double preco;
	private double porcentagem;

	public ProdutoDTO() {
	}

	public ProdutoDTO(String descricao, String nome, double preco, double porcentagem) {
		this.descricao = descricao;
		this.nome = nome;
		this.preco = preco;
		this.porcentagem = porcentagem;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public double getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(double porcentagem) {
		this.porcentagem = porcentagem;
	}

}
