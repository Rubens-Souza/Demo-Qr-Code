package io.github.rz.demo.controllers.requests;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import io.github.rz.demo.utils.enums.FormatoImagens;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestGerarCodigoQr {

	private String dados;
	private Integer altura;
	private Integer largura;
	private Integer margem;
	private FormatoImagens formato;
	private ErrorCorrectionLevel nivelCorrecao;
	
}
