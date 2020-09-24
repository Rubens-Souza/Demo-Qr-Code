package io.github.rz.demo.services;

import org.springframework.stereotype.Service;

import io.githu.rz.demo.utils.codigos.IManipuladorCodigos;
import io.githu.rz.demo.utils.codigos.enums.FormatoImagens;
import io.githu.rz.demo.utils.codigos.impl.qr.ManipuladorCodigosQr;
import io.github.rz.demo.dto.CodigoQrDTO;
import io.github.rz.demo.dto.PagamentoDTO;

@Service
public class PagamentoCodigoService {
	
	private final Integer ALTURA_QR_CODE = 400;
	private final Integer LARGURA_QR_CODE = 400;
	
	private final FormatoImagens FORMATO_QR_CODE = FormatoImagens.PNG;

	private IManipuladorCodigos<PagamentoDTO> manipuladorCodigos = new ManipuladorCodigosQr<PagamentoDTO>();

	public CodigoQrDTO gerarCodigoPagamento(PagamentoDTO pagamento) {
		String codigoGerado = manipuladorCodigos.gerarCodigo(pagamento, ALTURA_QR_CODE, LARGURA_QR_CODE, FORMATO_QR_CODE);
		return CodigoQrDTO.builder().formatoImagem(FORMATO_QR_CODE.getFormato()).altura(ALTURA_QR_CODE).largura(LARGURA_QR_CODE).imagemBase64(codigoGerado).build();
	}
	
	public PagamentoDTO lerCodigoPagamento(String codigoPagamentoBase64) {
		PagamentoDTO dadosPagamento = manipuladorCodigos.lerCodigo(codigoPagamentoBase64, PagamentoDTO.class);
		return dadosPagamento;
	}
}
