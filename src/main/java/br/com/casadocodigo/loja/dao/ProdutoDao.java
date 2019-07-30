package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.model.Produto;

@Repository
@Transactional
public class ProdutoDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void gravar(Produto produto) {
		entityManager.persist(produto);
	}

	public List<Produto> listar() {
		return entityManager.createQuery("select p from Produto p", Produto.class).getResultList();
	}

	public Produto find(Integer id) {
		return entityManager.createQuery("select distinct(p) from Produto p"
				+ " join fetch p.precos preco where p.id = :id ",Produto.class)
				.setParameter("id", id)
				.getSingleResult();
	}

}
