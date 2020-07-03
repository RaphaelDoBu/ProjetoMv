package com.projeto.mv.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.projeto.mv.dto.ProdutoDTO;
import com.projeto.mv.model.Produto;
import com.projeto.mv.repository.ProdutoRepository;

/**
 * Camada de serviço do produto aonde é feito a implementação da lógica do produto
 * @author jose.raphael.s.bu
 *
 */
@Service
public class ProdutoServiceImpl implements ProdutoService{
	
	/**
	 * Repositório JPA do produto
	 */
	@Autowired
	private ProdutoRepository produtoRepository;
	
	/**
	 * Importação do JdbcTemplate para ser feito a manipulação do package e das procedures
	 */
	@Autowired
	private JdbcTemplate jdbcTemplateObject;

	/**
	 * Busca todos os produtos cadastrados
	 */
	@Override
	public List<Produto> findAll() {
		return produtoRepository.findAll();	
	}

	/**
	 * Busca somente um produto pelo código do produto
	 */
	@Override
	public Optional<Produto> findByCodigoProduto(long codigoProduto) {
		return produtoRepository.findById(codigoProduto);
	}

	/**
	 * Cria um produto novo e retorna o objeto criado
	 */
	@Override
	public Optional<Produto> saveProduto(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		
		produto.setCodigo(produtoDTO.getCodigo());
		produto.setNome(produtoDTO.getNome());
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setPreco(produtoDTO.getPreco());
		produtoRepository.save(produto);
		return produtoRepository.findById(produto.getCodigo());
	}
	
	/**
	 * Edita um produto já existente e retorna o objeto modificado
	 */
	@Override
	public Optional<Produto> editProduto(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		
		produto.setCodigo(produtoDTO.getCodigo());
		produto.setNome(produtoDTO.getNome());
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setPreco(produtoDTO.getPreco());
		produtoRepository.save(produto);
		return produtoRepository.findById(produto.getCodigo());
	}

	/**
	 * Deleta um produto da base pelo código do produto
	 */
	@Override
	public void deleteProduto(long codigoProduto) {
		produtoRepository.deleteById(codigoProduto);
	}
	
	/**
	 * Altera o preço de um produto, recebendo o código do produto com valor da porcentagem para ser feito o calculo
	 */
	@Override
	public void alterarPrecoPorcentPorProduto(long codigoProduto, double porcentagem) {
	
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplateObject).withCatalogName("pck_produto").withProcedureName("atualiza_especifico");
		
		SqlParameterSource in = new MapSqlParameterSource().addValue("id_produto", codigoProduto).addValue("valor", porcentagem);
		Map<String, Object> out = jdbcCall.execute(in);
	}
	
	/**
	 * Altera o preço de todos os produtos existentes, recebendo como parâmetro o valor da porcentagem
	 */
	@Override
	public void alterarPrecoPorcentGeral(double porcentagem) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplateObject).withCatalogName("pck_produto").withProcedureName("atualiza_todos");
		
		SqlParameterSource in = new MapSqlParameterSource().addValue("valor", porcentagem);
		Map<String, Object> out = jdbcCall.execute(in);
	}

}
