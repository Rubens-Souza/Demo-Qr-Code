package io.github.rz.demo.utils.codigos.qr;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import io.github.rz.demo.excecoes.GeracaoCodigoException;
import io.github.rz.demo.utils.GsonUtils;
import io.github.rz.demo.utils.enums.FormatoImagens;

public class GeradorCodigoQrBuilder<T> {

	private T dados;
	private Integer altura;
	private Integer largura;
	private Integer margem;
	private String tipoEncode;
	private FormatoImagens formatoImagem;
	private ErrorCorrectionLevel nivelCorrecao;
	private Hashtable<EncodeHintType, String> parametrosExtras;

	private final Integer ALTURA_PADRAO = 400;
	private final Integer LARGURA_PADRAO = 400;
	private final Integer MARGEM_PADRAO = 3;
	private final String TIPO_ENCODE_PADRAO = "ISO-8859-1";
	private final FormatoImagens FORMATO_PADRAO_IMAGEM = FormatoImagens.PNG;
	private final ErrorCorrectionLevel NIVEL_CORRECAO_PADRAO = ErrorCorrectionLevel.M;

	private final String MSG_ERRO_GERACAO_CODIGO_QR = "Erro ao gerar o código Qr: %s";
	private final String MSG_ERRO_CONVERSAO_CODIGO_QR = "Erro ao converter o código Qr para Base64: %s";

	GeradorCodigoQrBuilder(T dados) {
		inicializarValoresPadroes();
		this.dados = dados;
	}

	private void inicializarValoresPadroes() {
		this.altura = ALTURA_PADRAO;
		this.largura = LARGURA_PADRAO;
		this.margem = MARGEM_PADRAO;
		this.tipoEncode = TIPO_ENCODE_PADRAO;
		this.nivelCorrecao = NIVEL_CORRECAO_PADRAO;
		this.formatoImagem = FORMATO_PADRAO_IMAGEM;
		this.parametrosExtras = montarParametrosExtrasPadroes();
	}

	private Hashtable<EncodeHintType, String> montarParametrosExtrasPadroes() {
		Hashtable<EncodeHintType, String> parametrosExtrasPadroes = new Hashtable<EncodeHintType, String>();
		parametrosExtrasPadroes.put(EncodeHintType.ERROR_CORRECTION, nivelCorrecao.toString());
		parametrosExtrasPadroes.put(EncodeHintType.CHARACTER_SET, tipoEncode);
		parametrosExtrasPadroes.put(EncodeHintType.MARGIN, margem.toString());

		return parametrosExtrasPadroes;
	}

	public GeradorCodigoQrBuilder<T> comDimensoes(Integer largura, Integer altura) {
		this.largura = largura;
		this.altura = altura;
		return this;
	}

	public GeradorCodigoQrBuilder<T> comMargem(Integer margem) {
		this.margem = margem;
		return this;
	}

	public GeradorCodigoQrBuilder<T> comNivelCorrecao(ErrorCorrectionLevel nivelCorrecao) {
		this.nivelCorrecao = nivelCorrecao;
		return this;
	}

	public GeradorCodigoQrBuilder<T> noFormato(FormatoImagens formato) {
		this.formatoImagem = formato;
		return this;
	}

	public String emBase64() throws GeracaoCodigoException {
		String conteudo = GsonUtils.stringfy(dados);

		BitMatrix matrizCodigoGerada;
		try {
			QRCodeWriter writer = new QRCodeWriter();
			matrizCodigoGerada = writer.encode(conteudo, BarcodeFormat.QR_CODE, largura, altura, parametrosExtras);
		} catch (WriterException ex) {
			throw new GeracaoCodigoException(String.format(MSG_ERRO_GERACAO_CODIGO_QR, ex.getMessage()));
		}

		String codigoQrBase64 = converterMatrizBitsImagem(matrizCodigoGerada, formatoImagem.getFormato());
		return codigoQrBase64;
	}

	private String converterMatrizBitsImagem(BitMatrix matriz, String formatoImagem) {
		ByteArrayOutputStream streamImagemBytes = new ByteArrayOutputStream();

		try {
			MatrixToImageWriter.writeToStream(matriz, formatoImagem, streamImagemBytes);
			streamImagemBytes.close();
		} catch (IOException ex) {
			throw new GeracaoCodigoException(String.format(MSG_ERRO_CONVERSAO_CODIGO_QR, ex.getMessage()));
		}

		String imagem = Base64.getEncoder().encodeToString(streamImagemBytes.toByteArray());
		return imagem;
	}
}
