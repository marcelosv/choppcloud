package com.br.choppcloud.bank.pagamento;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.br.choppcloud.bank.cartao.Cartao;

@Entity
@Table(name="pagamento")
public class Pagamento {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Cartao cartao;

	private BigDecimal valorCompra;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

}
