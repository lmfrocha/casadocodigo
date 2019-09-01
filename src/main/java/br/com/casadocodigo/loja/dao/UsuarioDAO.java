package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.model.Usuario;

@Repository
public class UsuarioDAO implements UserDetailsService{

	@PersistenceContext
	private EntityManager entityManager;
	
	public Usuario loadUserByUsername(String email) {
		List<Usuario> usuario =  entityManager.createQuery(" select u from Usuario u where u.email =:email ", Usuario.class)
			.setParameter("email", email)
			.getResultList();
		
		if(usuario.isEmpty()) {
			throw new UsernameNotFoundException(" Usuario " + email + " Não foi encontrado ");
		}
		return usuario.get(0);
	}

	public Usuario find(Integer id) {
		return entityManager.find(Usuario.class, id);
	}
	
	public void gravar(Usuario usr) {
		entityManager.persist(usr);
	}
	
	public void atualizarRoles(Usuario usr) {
		entityManager.getTransaction().begin();
		Usuario temp = entityManager.find(Usuario.class, usr.getEmail());
		temp.setRoles(usr.getRoles());
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public List<Usuario> listar(){
		TypedQuery<Usuario> query = entityManager.createQuery(" select u from Usuario u ", Usuario.class);
		return query.getResultList();
	}
	
}
