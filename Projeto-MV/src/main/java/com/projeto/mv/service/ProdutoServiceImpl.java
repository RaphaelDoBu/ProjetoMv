package com.projeto.mv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.mv.dto.ProdutoDTO;
import com.projeto.mv.model.Produto;
import com.projeto.mv.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService{
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public List<Produto> findAll() {
		return produtoRepository.findAll();	
	}

	@Override
	public Optional<Produto> findByCodigoProduto(long codigoProduto) {
		return produtoRepository.findById(codigoProduto);
	}

	@Override
	public Optional<Produto> saveProduto(Produto produto) {
		produtoRepository.save(produto);
		return produtoRepository.findById(produto.getCodigoProduto());
	}

	@Override
	public void deleteProduto(long codigoProduto) {
		produtoRepository.deleteById(codigoProduto);
	}

}
