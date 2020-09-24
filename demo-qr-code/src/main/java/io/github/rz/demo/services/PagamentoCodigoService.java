package io.github.rz.demo.services;

import org.springframework.stereotype.Service;

import io.githu.rz.demo.utils.codigos.IManipuladorCodigos;
import io.githu.rz.demo.utils.codigos.impl.qr.ManipuladorCodigosQr;
import io.github.rz.demo.dto.CodigoQrDTO;
import io.github.rz.demo.dto.PagamentoDTO;

@Service
public class PagamentoCodigoService {

	private IManipuladorCodigos<PagamentoDTO> manipuladorCodigos = new ManipuladorCodigosQr<PagamentoDTO>();

	public CodigoQrDTO gerarCodigoPagamento(PagamentoDTO pagamento) {
		String codigoGerado = manipuladorCodigos.gerarCodigo(pagamento);
		return CodigoQrDTO.builder().formatoImagem("png").altura(100).largura(100).imagemBase64(codigoGerado).build();
	}
	
	public PagamentoDTO lerCodigoPagamento(String codigoPagamentoBase64) {
		PagamentoDTO dadosPagamento = manipuladorCodigos.lerCodigo(codigoPagamentoBase64, PagamentoDTO.class);
		return dadosPagamento;
	}
}
