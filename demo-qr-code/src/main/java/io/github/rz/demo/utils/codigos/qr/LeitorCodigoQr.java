package io.github.rz.demo.utils.codigos.qr;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import io.github.rz.demo.excecoes.LeituraCodigoException;
import io.github.rz.demo.utils.GsonUtils;

public class LeitorCodigoQr {

	private final String MSG_ERRO_LEITURA_CODIGO_QR = "Erro ao ler dados no base64 do código QR: %s";
	private final String MSG_ERRO_QR_NAO_IDENTIFICADO = "O código de barras no Qr code não pode ser corretamente identificado: %s";

	public String lerCodigoBase64(String codigo) {
		Result dados = lerCodigoQr(codigo);
		return dados.getText();
	}
	
	public <T> T lerCodigoBase64(String codigo, Class<T> classeGerada) {
		Result dados = lerCodigoQr(codigo);
		T dadosConvertidos = GsonUtils.parse(dados.getText(), classeGerada);
		
		return dadosConvertidos;
	}
	
	private Result lerCodigoQr(String base64) {
		String imagemFormatada = removerCabecalhoBase64(base64);
		BinaryBitmap bitmapCodigoQr = converterBase64(imagemFormatada);

		Result dados;
		try {
			dados = new MultiFormatReader().decode(bitmapCodigoQr);
		} catch (NotFoundException ex) {
			throw new LeituraCodigoException(String.format(MSG_ERRO_QR_NAO_IDENTIFICADO, ex.getMessage()));
		}
		
		return dados;
	}

	private String removerCabecalhoBase64(String base64) {
		final String regexPadraoCabecalhoBase64 = "^data:image/[^;]*;base64,?";
		return base64.replaceFirst(regexPadraoCabecalhoBase64, StringUtils.EMPTY);
	}

	private BinaryBitmap converterBase64(String base64) {
		byte[] imagemBytes = Base64.getDecoder().decode(base64);
		ByteArrayInputStream inputStreamImagem = new ByteArrayInputStream(imagemBytes);

		BinaryBitmap bitmap;
		try {
			BufferedImage imagemCodigoQr = ImageIO.read(inputStreamImagem);
			LuminanceSource sourceImagem = new BufferedImageLuminanceSource(imagemCodigoQr);
			bitmap = new BinaryBitmap(new HybridBinarizer(sourceImagem));
		} catch (Exception ex) {
			throw new LeituraCodigoException(
					String.format(MSG_ERRO_LEITURA_CODIGO_QR, StringUtils.defaultString(ex.getMessage())));
		}

		return bitmap;
	}
}
