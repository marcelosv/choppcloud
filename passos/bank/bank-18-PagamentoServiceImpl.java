package com.br.choppcloud.bank.pagamento;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.choppcloud.bank.cartao.CartaoService;

@Service
public class PagamentoServiceImpl implements PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private CartaoService cartaoService;
	
	@Transactional
	@Override
	public void pagamento(PagamentoJson pagamentoJson){
	
		if( !cartaoService.isValido(pagamentoJson.getCodigoSegurancaCartao(), pagamentoJson.getNroCartao()) ){
			throw new PagamentoException("Cartão inválido.");
		}
		
		if( !cartaoService.isSaldoSuficiente(pagamentoJson.getCodigoSegurancaCartao(), pagamentoJson.getNroCartao(), pagamentoJson.getValorCompra()) ){
			throw new PagamentoException("Cartão não possui saldo suficiente.");
		}

		Pagamento pagamento = new Pagamento();
		pagamento.setValorCompra(pagamentoJson.getValorCompra());
		pagamento.setCartao(cartaoService.getCartao(pagamentoJson.getCodigoSegurancaCartao(), pagamentoJson.getNroCartao()));
		
		pagamentoRepository.save(pagamento);
		
		cartaoService.atualizarSaldo(pagamentoJson.getCodigoSegurancaCartao(), pagamentoJson.getNroCartao(), pagamentoJson.getValorCompra());
	}
	
}
