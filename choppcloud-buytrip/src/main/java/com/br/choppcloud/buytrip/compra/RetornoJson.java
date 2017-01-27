package com.br.choppcloud.buytrip.compra;

public class RetornoJson {
	
	private String mensagem;
	private String chavePesquisa;

	public RetornoJson() {
	}
	
	public RetornoJson(String message) {
		mensagem = message;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getChavePesquisa() {
		return chavePesquisa;
	}

	public void setChavePesquisa(String chavePesquisa) {
		this.chavePesquisa = chavePesquisa;
	}

}
