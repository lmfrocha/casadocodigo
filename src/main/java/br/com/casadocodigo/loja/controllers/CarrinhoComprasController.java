package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDao;
import br.com.casadocodigo.loja.model.CarrinhoCompras;
import br.com.casadocodigo.loja.model.CarrinhoItem;
import br.com.casadocodigo.loja.model.Produto;
import br.com.casadocodigo.loja.model.TipoPreco;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoComprasController {

	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private CarrinhoCompras carrinho;
	
	@RequestMapping("/add")
	public ModelAndView add(Integer pId, TipoPreco tipoPreco) {
		ModelAndView modelAndView = new ModelAndView("redirect:/produtos");
		CarrinhoItem carrinhoItem = criaItem(pId,tipoPreco);
		carrinho.add(carrinhoItem);
		return modelAndView;
	}


	private CarrinhoItem criaItem(Integer pId, TipoPreco tipoPreco) {
		Produto produto = produtoDao.find(pId);
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);
		return carrinhoItem;
	}
}
