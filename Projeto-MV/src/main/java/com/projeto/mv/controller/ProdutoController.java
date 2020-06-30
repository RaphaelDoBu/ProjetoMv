package com.projeto.mv.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.PostPersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.mv.model.Produto;
import com.projeto.mv.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping(value = "/lista")
	public List<Produto> findAll(){
		return produtoRepository.findAll();		
	}
	
	@GetMapping(value = "/id/{codigoProduto}")
	public Optional<Produto> findByCodigoProduto(@PathVariable final long codigoProduto){
		return produtoRepository.findById(codigoProduto);		
	}
	
	@PostMapping(value="/novo")
	public Optional<Produto> saveProduto(@RequestBody final Produto produto){
		produtoRepository.save(produto);
		return produtoRepository.findById(produto.getCodigoProduto());
	}
	
//	@PutMapping(value = "/{codigoProduto}")
//	public Optional<Produto> putProduto(@PathVariable final long codigoProduto){
//		return produtoRepository.findById(codigoProduto);		
//	}
//	
//	@PatchMapping(value = "/{codigoProduto}")
//	public Optional<Produto> patchProduto(@PathVariable final long codigoProduto){
//		return produtoRepository.findById(codigoProduto);		
//	}
	
	@DeleteMapping(value = "/{codigoProduto}")
	public void deleteProduto(@PathVariable final long codigoProduto){
		produtoRepository.deleteById(codigoProduto);		
	}
	
}
