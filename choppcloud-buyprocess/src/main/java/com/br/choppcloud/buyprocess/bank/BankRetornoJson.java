package com.br.choppcloud.buyprocess.bank;

public class BankRetornoJson {
	
	private String mensagem;

	public BankRetornoJson() {
	}
	
	public BankRetornoJson(String message) {
		mensagem = message;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
