package com.projeto.mv.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.PostPersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.mv.dto.ProdutoDTO;
import com.projeto.mv.model.Produto;
import com.projeto.mv.repository.ProdutoRepository;
import com.projeto.mv.service.ProdutoService;
import com.sun.org.apache.xpath.internal.operations.Mod;

/**
 * Classe que representa o objeto produto a ser manipulado
 * @author jose.raphael.s.bu
 *
 */
@RestController
@RequestMapping("/product")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	/**
	 * Método para exibir uma lista de produtos criados na página inicial
	 * @return
	 */
	@GetMapping(value = "/list")
	public ModelAndView findAll(){
		ModelAndView model = new ModelAndView("list_product");
		
		List<Produto> listaProdutos = produtoService.findAll();
		model.addObject("produtos", listaProdutos);
		
		return model;
	} 
	
	/**
	 * Método para buscar as informações de um produto, recebendo como parâmetro o ID (código do produto)
	 * @param codigoProduto
	 * @return
	 */
	@GetMapping(value = "/id/{codigoProduto}")
	public Optional<Produto> findByCodigoProduto(@PathVariable final long codigoProduto){
		return produtoService.findByCodigoProduto(codigoProduto);
	}
	
	/**
	 * Método para exibir a tela de criação de novo produto
	 * @return
	 */
	@GetMapping(value="/new")
	public ModelAndView addUser(){
		ModelAndView model = new ModelAndView("add_product");
		
		return model;
	}
	
	/**
	 * Método para criar um produto novo, recebendo como parâmetro um objeto preenchido do front
	 * @param produtoDTO
	 * @return
	 */
	@PostMapping(value="/add")
	public ModelAndView saveProduto(@ModelAttribute("produto") ProdutoDTO produtoDTO){
		if(produtoDTO != null) {
			Produto produto = new Produto();
			
			produto.setCodigoProduto(produtoDTO.getCodigoProduto());
			produto.setDescricaoProduto(produtoDTO.getDescricaoProduto());
			produto.setPrecoProduto(produtoDTO.getPrecoProduto());
			produtoService.saveProduto(produto);
		}
		return new ModelAndView("redirect:/product/list");
	}
	
	/**
	 * Método para exibir as informações de um produto para ser editado, recebendo como parâmetro o código de um produto
	 * @param codigoProduto
	 * @return
	 */
	@GetMapping(value = "/edit/{codigoProduto}")
	public ModelAndView viewEditProduto(@PathVariable final long codigoProduto){
		ModelAndView model = new ModelAndView("edit_product");
		
		Optional<Produto> produto = produtoService.findByCodigoProduto(codigoProduto);
		if(produto != null) {
			model.addObject("produto", produto.get());
		}
		return model;
	}
	
	/**
	 * Método para editar um produto, recebendo todas as informações necessárias para serem editadas de um determinado produto
	 * @param produtoDTO
	 * @return
	 */
	@PostMapping(value="/editProduct")
	public ModelAndView editProduto(@ModelAttribute("produto") ProdutoDTO produtoDTO){
		if( produtoDTO.getDescricaoProduto() != null ) {
			Produto produto = new Produto();
			
			produto.setCodigoProduto(produtoDTO.getCodigoProduto());
			produto.setDescricaoProduto(produtoDTO.getDescricaoProduto());
			produto.setPrecoProduto(produtoDTO.getPrecoProduto());
			
			produtoService.editProduto(produto);

		}
				
		return new ModelAndView("redirect:/product/list");
	}
	
	/**
	 * Método para calcular o reajuste do preço de um único produto, recebendo como parâmetro o produto já criado e um novo atributo (porcentagem)
	 * @param produtoDto
	 * @return
	 */
	@PostMapping(value="/reajusteUmProd")
	public ModelAndView reajustarPrecoUmProd(@ModelAttribute("produtoAtualizado") ProdutoDTO produtoDto){
		
		Produto produto = new Produto();
		
		produto.setCodigoProduto(produtoDto.getCodigoProduto());
		produto.setPorcentagem(produtoDto.getPorcentagem());

		produtoService.alterarPrecoPorcentPorProduto(produto.getCodigoProduto(), produto.getPorcentagem());

		return new ModelAndView("redirect:/product/list");
	}
	
	/**
	 * Método para calcular o reajuste do preço de todos os produtos, recebendo como parâmetro a porcentagem para ser calculado com o valor atual do preço
	 * @param produto
	 * @return
	 */
	@PostMapping(value="/reajusteTodosProd")
	public ModelAndView reajustarPrecoTodosProd(@RequestParam("porcentagem") double porcentagem){

		produtoService.alterarPrecoPorcentGeral(porcentagem);

		return new ModelAndView("redirect:/product/list");
	}
	
	/**
	 * Método para deletar um único produto, recebendo como parâmetro um código de um produto
	 * @param codigoProduto
	 * @return
	 */
	@GetMapping(value="/delete/{codigoProduto}")
	public ModelAndView deleteProduto(@PathVariable("codigoProduto") final long codigoProduto){
		produtoService.deleteProduto(codigoProduto);
		
		return new ModelAndView("redirect:/product/list");
	}
	
}
