package com.projeto.mv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.projeto.mv.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
