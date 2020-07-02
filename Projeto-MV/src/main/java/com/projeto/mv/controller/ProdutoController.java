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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.mv.dto.ProdutoDTO;
import com.projeto.mv.model.Produto;
import com.projeto.mv.repository.ProdutoRepository;
import com.projeto.mv.service.ProdutoService;
import com.sun.org.apache.xpath.internal.operations.Mod;

@RestController
@RequestMapping("/product")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping(value = "/list")
	public ModelAndView findAll(){
		ModelAndView model = new ModelAndView("list_product");
		
		List<Produto> listaProdutos = produtoService.findAll();
		model.addObject("produtos", listaProdutos);
		
		return model;
	}
	
	@GetMapping(value = "/id/{codigoProduto}")
	public Optional<Produto> findByCodigoProduto(@PathVariable final long codigoProduto){
		return produtoService.findByCodigoProduto(codigoProduto);
	}
	
	@GetMapping(value="/new")
	public ModelAndView addUser(){
		ModelAndView model = new ModelAndView("add_product");
		
		return model;
	}
	
	@PostMapping(value="/add")
	public ModelAndView saveProduto(@ModelAttribute("produto") Produto produto){
		if(produto != null) {
			produtoService.saveProduto(produto);
		}
		return new ModelAndView("redirect:/product/list");
	}
	
	@GetMapping(value = "/edit/{codigoProduto}")
	public ModelAndView viewEditProduto(@PathVariable final long codigoProduto){
		ModelAndView model = new ModelAndView("edit_product");
		
		Optional<Produto> produto = produtoService.findByCodigoProduto(codigoProduto);
		if(produto != null) {
			model.addObject("produto", produto.get());
		}
		return model;	
	}
	
	@PostMapping(value="/editProduct")
	public ModelAndView editProduto(@ModelAttribute("produto") Produto produto){
		if( produto.getDescricaoProduto() != null ) {
			produtoService.editProduto(produto);

		}
				
		return new ModelAndView("redirect:/product/list");
	}
	

	@PostMapping(value="/reajuste")
	public ModelAndView addUser(@ModelAttribute("produtoAtualizado") Produto produto){

		produtoService.alterarPrecoPorcentPorProduto(produto.getCodigoProduto(), produto.getPorcentagem());

		return new ModelAndView("redirect:/product/list");
	}
	
	@GetMapping(value="/delete/{codigoProduto}")
	public ModelAndView deleteProduto(@PathVariable("codigoProduto") final long codigoProduto){
		produtoService.deleteProduto(codigoProduto);
		
		return new ModelAndView("redirect:/product/list");
	}
	
}
