package com.projeto.mv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Produto {
	
	@Id @SequenceGenerator(name = "TEST_SEQ", sequenceName = "TEST_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEST_SEQ")
	private Long codigoProduto;
	private String descricaoProduto;
	private double precoProduto;
	
	public Produto() {
	}

	public Produto(Long codigoProduto, String descricaoProduto, double precoProduto) {
		this.codigoProduto = codigoProduto;
		this.descricaoProduto = descricaoProduto;
		this.precoProduto = precoProduto;
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
}
