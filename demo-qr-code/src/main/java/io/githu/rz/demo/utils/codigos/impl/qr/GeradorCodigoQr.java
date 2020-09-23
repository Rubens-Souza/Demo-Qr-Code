package io.githu.rz.demo.utils.codigos.impl.qr;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Hashtable;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import io.githu.rz.demo.utils.codigos.IGeradorCodigo;
import io.github.rz.demo.excecoes.GeracaoCodigoException;

public class GeradorCodigoQr<T> implements IGeradorCodigo<T> {

	private final String FORMATACAO_PADRAO_QR_CODE = "ISO-8859-1";
	// TODO: Pegar dinamicamente por parâmetro no builder
	private final String FORMATO_IMAGEM_QR_CODE = "png";
	
	// TODO: Pegar dinamicamente por parâmetro no builder
	private final Integer ALTURA_PADRAO = 100;
	private final Integer LARGURA_PADRAO = 100;
	
	private final String MSG_ERRO_GERACAO_CODIGO_QR = "Erro ao gerar o código Qr: %s";
	private final String MSG_ERRO_CONVERSAO_CODIGO_QR = "Erro ao converter o código Qr para Base64: %s";
	
	@Override
	public String criarCodigoBase64(T dados) {
		Hashtable<EncodeHintType, String> parametrosWriter = montarConfiguracaoWriter();
		String conteudo = converterDados(dados);
		
		BitMatrix matrizCodigoGerada;
		try {
			QRCodeWriter writer = new QRCodeWriter();
			matrizCodigoGerada = writer.encode(conteudo, BarcodeFormat.QR_CODE, LARGURA_PADRAO, ALTURA_PADRAO, parametrosWriter);
		} catch (WriterException ex) {
			throw new GeracaoCodigoException(String.format(MSG_ERRO_GERACAO_CODIGO_QR, ex.getMessage()));
		}
		
		String codigoQrBase64 = converterMatrizBitsImagem(matrizCodigoGerada);
		return codigoQrBase64;
	}
	
	private Hashtable<EncodeHintType, String> montarConfiguracaoWriter() {
		Hashtable<EncodeHintType, String> parametrosExtras = new Hashtable<>();
		parametrosExtras.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H.toString());
		parametrosExtras.put(EncodeHintType.CHARACTER_SET, FORMATACAO_PADRAO_QR_CODE);
		
		return parametrosExtras;
	}
	
	private String converterDados(T dados) {
		Gson conversorJson = new Gson();
		return conversorJson.toJson(dados);
	}
	
	private String converterMatrizBitsImagem(BitMatrix matriz) {
		ByteArrayOutputStream streamImagemBytes = new ByteArrayOutputStream();   
		
		try {
			MatrixToImageWriter.writeToStream(matriz, FORMATO_IMAGEM_QR_CODE, streamImagemBytes);
			streamImagemBytes.close();
		} catch (IOException ex) {
			throw new GeracaoCodigoException(String.format(MSG_ERRO_CONVERSAO_CODIGO_QR, ex.getMessage()));
		}
		
		String imagem = Base64.getEncoder().encodeToString(streamImagemBytes.toByteArray());
		return imagem;
	}
}
