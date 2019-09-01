package br.com.casadocodigo.loja.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.model.Produto;
import br.com.casadocodigo.loja.model.TipoPreco;

@Repository
@Transactional
public class ProdutoDAO{

	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Produto produto) {
		manager.persist(produto);
	}

	public List<Produto> listar() {
		TypedQuery<Produto> query = manager.createQuery(" select distinct(p) from Produto p "
				+ " join fetch p.precos ", Produto.class);
		return query.getResultList();
	}

	public Produto find(Integer id) {
       TypedQuery<Produto> query = manager.createQuery(" select distinct(p) from Produto p "
       		+ " join fetch p.precos precos where p.id = :id ", Produto.class)
        		.setParameter("id", id);
       return query.getSingleResult();
	}

	public BigDecimal somaPrecoPorTipo(TipoPreco tipoPreco) {
		TypedQuery<BigDecimal> query = manager.createQuery(" select sum(preco.valor) from Produto p "
				+ " join p.precos preco where preco.tipo =:tipoPreco ", BigDecimal.class)
				.setParameter("tipoPreco", tipoPreco);
		return query.getSingleResult();
	}
	
	public Long getQuantidadeDeProdutosCadastrados() {
		TypedQuery<Long> query = manager.createQuery(" select count(*) from Produto ", Long.class); 
		return query.getSingleResult();
	}

	public List<Produto> listarPelaDataLancamento(Calendar data) {
		TypedQuery<Produto> query = manager.createQuery(" select distinct(p) from Produto p "
				+ " join fetch p.precos precos "
				+ " where p.dataLancamento > :data ", Produto.class)
				.setParameter("data", data);
		return query.getResultList();
	}
}