package br.com.casadocodigo.loja.controllers;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.dto.ProdutoDTO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.model.Produto;
import br.com.casadocodigo.loja.model.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/relatorio-produtos")
public class RelatorioProdutosController {
		
	@Autowired
	private ProdutoDAO dao;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());
	}

	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ProdutoDTO listar(@RequestParam(value = "data", required = false) String dataLancamento) {
		ProdutoDTO relatorio = new ProdutoDTO();
		
		relatorio.setDataGeracao(Calendar.getInstance());
		relatorio.setQuantidade(dao.getQuantidadeDeProdutosCadastrados().intValue());
		relatorio.setProdutos(dao.listar());
		
		return relatorio;
	}
}
