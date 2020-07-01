package com.projeto.mv.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.PostPersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.mv.dto.ProdutoDTO;
import com.projeto.mv.model.Produto;
import com.projeto.mv.repository.ProdutoRepository;
import com.projeto.mv.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping(value = "/lista")
	public ModelAndView findAll(){
		ModelAndView model = new ModelAndView("list_product");
		
		List<Produto> list = produtoService.findAll();
		model.addObject("produtos", list);
		
		return model;
	}
	
	@GetMapping(value = "/id/{codigoProduto}")
	public Optional<Produto> findByCodigoProduto(@PathVariable final long codigoProduto){
		return produtoService.findByCodigoProduto(codigoProduto);
	}
	
	@GetMapping(value="/add-produto")
	public ModelAndView addUser(){
		ModelAndView model = new ModelAndView("add_product");
		
		return model;
	}
	
	@PostMapping(value="/novo")
	public ModelAndView  saveProduto(@ModelAttribute("produto") Produto produto){
		if(produto != null) {
			produtoService.saveProduto(produto);
		}
		return new ModelAndView("redirect:/produto/lista");
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
		produtoService.deleteProduto(codigoProduto);		
	}
	
}
