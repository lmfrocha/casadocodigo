package br.com.casadocodigo.loja.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.model.Pedido;

@Controller
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class PedidoServicoController {

	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/pedidos")
	public ModelAndView listarPedidos() {
		List<Pedido> pedidos =  new ArrayList<Pedido>();
		ModelAndView modelAndView = new ModelAndView("/produtos/pedidos");
		String endpoint = "https://book-payment.herokuapp.com/orders";
		
		ResponseEntity<List<Pedido>> response = restTemplate
				.exchange(endpoint, HttpMethod.GET, null, new ParameterizedTypeReference<List<Pedido>>(){});
		pedidos = response.getBody();
		modelAndView.addObject("pedidos",pedidos);
		return modelAndView;
	}
	
}
