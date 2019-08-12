package br.com.casadocodigo.loja.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.casadocodigo.loja.model.Produto;

public class produtoDTO {

	private Calendar dataGeracao;
	
	private Integer quantidade;
	
	private List<Produto> produtos = new ArrayList<>();

	@Deprecated
	private void produtoDto() {

	}
	
	public produtoDTO(Calendar dataGeracao, Integer quantidade, List<Produto> produtos) {
		this.dataGeracao = dataGeracao;
		this.quantidade = quantidade;
		this.produtos = produtos;
	}

	public Calendar getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Calendar dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
	
}
