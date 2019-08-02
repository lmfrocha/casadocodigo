package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.model.Usuario;

@Repository
public class UsuarioDao implements UserDetailsService{

	@PersistenceContext
	private EntityManager entityManager;
	
	public Usuario loadUserByUsername(String email) {
		List<Usuario> usuario =  entityManager.createQuery("select u from Usuario u where u.email =:email ", Usuario.class)
			.setParameter("email", email)
			.getResultList();
		
		
		if(usuario.isEmpty()) {
			throw new UsernameNotFoundException("Usuario " + email + " Não foi encontrado");
		}
		return usuario.get(0);
	}

}
