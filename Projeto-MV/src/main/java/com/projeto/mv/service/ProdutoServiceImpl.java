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

@Service
public class ProdutoServiceImpl implements ProdutoService{
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplateObject;

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
	public Optional<Produto> editProduto(Produto produto) {
		produtoRepository.save(produto);
		return produtoRepository.findById(produto.getCodigoProduto());
	}

	@Override
	public void deleteProduto(long codigoProduto) {
		produtoRepository.deleteById(codigoProduto);
	}
	
	@Override
	public void alterarPrecoPorcentPorProduto(long codigoProduto, int porcentagem) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplateObject).withCatalogName("pck_produto").withProcedureName("atualiza_especifico");
		
		SqlParameterSource in = new MapSqlParameterSource().addValue("id_produto", codigoProduto).addValue("valor", porcentagem);
		Map<String, Object> out = jdbcCall.execute(in);
	}
	
	@Override
	public void alterarPrecoPorcentGeral(long codigoProduto) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplateObject).withCatalogName("pck_produto").withProcedureName("atualiza_todos");
		
		SqlParameterSource in = new MapSqlParameterSource().addValue("id_produto", codigoProduto);
		Map<String, Object> out = jdbcCall.execute(in);
	}

}
