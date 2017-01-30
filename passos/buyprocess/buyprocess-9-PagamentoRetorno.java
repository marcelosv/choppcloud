package com.br.choppcloud.buyprocess.bank;

public class PagamentoRetorno {
    private String mensagem;
    private boolean pagamentoOK;

    public PagamentoRetorno(String mensagem, boolean pagamentoOK) {
        this.mensagem = mensagem;
        this.pagamentoOK = pagamentoOK;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isPagamentoOK() {
        return pagamentoOK;
    }

    public void setPagamentoOK(boolean pagamentoOK) {
        this.pagamentoOK = pagamentoOK;
    }
}
