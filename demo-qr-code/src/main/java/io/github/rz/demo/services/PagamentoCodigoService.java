package io.github.rz.demo.services;

import org.springframework.stereotype.Service;

import io.github.rz.demo.dto.CodigoQrDTO;
import io.github.rz.demo.dto.PagamentoDTO;
import io.github.rz.demo.utils.codigos.qr.CodigoQr;
import io.github.rz.demo.utils.enums.FormatoImagens;

@Service
public class PagamentoCodigoService {
	
	private final Integer ALTURA_QR_CODE = 400;
	private final Integer LARGURA_QR_CODE = 400;
	
	private final FormatoImagens FORMATO_QR_CODE = FormatoImagens.PNG;

	public CodigoQrDTO gerarCodigoPagamento(PagamentoDTO pagamento) {
		String codigoGerado = CodigoQr.gerarCodigo(pagamento).comDimensoes(LARGURA_QR_CODE, ALTURA_QR_CODE).noFormato(FORMATO_QR_CODE).emBase64();
		return CodigoQrDTO.builder().formatoImagem(FORMATO_QR_CODE.getFormato()).altura(ALTURA_QR_CODE).largura(LARGURA_QR_CODE).imagemBase64(codigoGerado).build();
	}
	
	public PagamentoDTO lerCodigoPagamento(String codigoPagamentoBase64) {
		PagamentoDTO dadosPagamento = CodigoQr.lerCodigo(codigoPagamentoBase64, PagamentoDTO.class);
		return dadosPagamento;
	}
}
