package io.github.rz.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodigoQrDTO {

	private Integer altura;
	private Integer largura;
	private String imagemBase64;
	private String formatoImagem;
	
	public CodigoQrDTO(String formatoImagem) {
		this.formatoImagem = formatoImagem;
	}
	
	private String getImagemComCabecalhoBase64() {
		String cabecalhoBase64 = getCabecalhoBase64();
		return cabecalhoBase64 + imagemBase64;
	}
	
	private String getCabecalhoBase64() {
		return  "data:image/" + formatoImagem + ";base64,";
	}
}
