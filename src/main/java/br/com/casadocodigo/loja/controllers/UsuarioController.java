package br.com.casadocodigo.loja.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.model.Role;
import br.com.casadocodigo.loja.model.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping("/usuarios")
@Transactional
public class UsuarioController {
	@Autowired
	UsuarioDAO usuarioDao;
	
	@Autowired
	RoleDAO roleDAO;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidation());
	}

	@RequestMapping("/form")
	public ModelAndView form(Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("usuarios/form");
		return modelAndView;
	}
	
	@RequestMapping(method= RequestMethod.POST)
	public ModelAndView gravar(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return form(usuario);
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = encoder.encode(usuario.getPassword());
		usuario.setSenha(password);
		usuarioDao.gravar(usuario);
		
		redirectAttributes.addFlashAttribute("message", "Usuario Cadastrado com sucesso!");
		return new ModelAndView("redirect:/usuarios");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Usuario> usuarios = usuarioDao.listar();
		List<Role> roles = roleDAO.listar();
		ModelAndView modelAndView = new ModelAndView("usuarios/lista");
		modelAndView.addObject("usuarios", usuarios);
		modelAndView.addObject("roles", roles);
		return modelAndView;
	}

	@RequestMapping(value = "/usuarioRoles", method = RequestMethod.POST)
	public ModelAndView alteraRole(String id) {
		Usuario usr = usuarioDao.find(Integer.parseInt(id));
		List<Role> roles = roleDAO.listar();
		ModelAndView modelAndView = new ModelAndView("usuarios/detalhes");
		modelAndView.addObject("usuario", usr);
		modelAndView.addObject("roles", roles);
		return modelAndView;
	}
	
	@RequestMapping(value = "/atualizaRoles")
	public ModelAndView atualizaRules (String id, @RequestParam("roles") List<String> roles, RedirectAttributes redirectAttributes ) {
		
		Usuario usr = usuarioDao.find(Integer.parseInt(id));
		List<Role> rolesModificadas = new ArrayList<Role>();
		
		roles.forEach( r -> {
			if(!r.isEmpty()) {
				rolesModificadas.add(new Role (r));
			}
		});
		
		usr.setRoles(rolesModificadas);
		redirectAttributes.addFlashAttribute("message", "Roles atualizadas com sucesso!");
		
		ModelAndView modelAndView = new ModelAndView("redirect/usuarios");
		return modelAndView;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
