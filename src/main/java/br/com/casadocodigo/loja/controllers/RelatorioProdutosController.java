package br.com.casadocodigo.loja.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.dto.ProdutoDTO;
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
	public ProdutoDTO listar(@RequestParam(value = "data", required = false, defaultValue = "0000-00-00") String data)
			throws ParseException {
		ProdutoDTO relatorio = new ProdutoDTO();
		
		relatorio.setDataGeracao(Calendar.getInstance());

		if (!data.isEmpty()) {
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sd.parse(data);
			Calendar dataNow = Calendar.getInstance();
			dataNow.setTime(date);
			relatorio.setProdutos(dao.listarPelaDataLancamento(dataNow));
			relatorio.setQuantidade(relatorio.getProdutos().size());
		}else if (data.equals("0000-00-00")){
			 relatorio.setProdutos(dao.listar());
			 relatorio.setQuantidade(relatorio.getProdutos().size());
		}

		return relatorio;
	}
}
