package com.projeto.mv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.projeto.mv.dto.ProdutoDTO;
import com.projeto.mv.model.Produto;

public interface ProdutoService {
	
	List<Produto> findAll();
	Optional<Produto> findByCodigoProduto(final long codigoProduto);
	Optional<Produto> saveProduto(final Produto produto);
	void deleteProduto(final long codigoProduto);
	Optional<Produto> editProduto(Produto produto);
	void alterarPrecoPorcentPorProduto(long codigoProduto, double porcentagem);
	void alterarPrecoPorcentGeral(long codigoProduto);
	
}
