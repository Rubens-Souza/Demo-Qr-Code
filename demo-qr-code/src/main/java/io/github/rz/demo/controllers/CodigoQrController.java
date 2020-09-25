package io.github.rz.demo.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.rz.demo.controllers.requests.RequestGerarCodigoQr;
import io.github.rz.demo.controllers.responses.ResponseDTO;
import io.github.rz.demo.dto.CodigoQrDTO;
import io.github.rz.demo.utils.codigos.qr.CodigoQr;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/codigos/qr")
@Api(tags = { "Gerar códigos Qr genéricos" })
public class CodigoQrController {

	@PostMapping("/gerarCodigoQrBasico")
	@ApiOperation(value = "Gera um código a partir de uma string")
	public ResponseEntity<ResponseDTO<Object>> gerarCodigoPagamento(RequestGerarCodigoQr request) {
		ResponseDTO<Object> response;
		try {
			String codigoQr = CodigoQr.gerarCodigo(request.getDados())
					.comDimensoes(request.getLargura(), request.getAltura()).comMargem(request.getMargem())
					.comNivelCorrecao(request.getNivelCorrecao()).noFormato(request.getFormato()).emBase64();

			CodigoQrDTO dadosCodigoQr = CodigoQrDTO.builder().altura(request.getAltura()).largura(request.getLargura())
					.formatoImagem(request.getFormato().toString()).imagemBase64(codigoQr).build();

			response = ResponseDTO.builder().sucessso(true).status(HttpStatus.OK.toString()).mensagem(StringUtils.EMPTY)
					.resultado(dadosCodigoQr).build();
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response = ResponseDTO.builder().sucessso(false).status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
					.mensagem(ex.getMessage()).resultado(null).build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/lerCodigoQr")
	@ApiOperation(value = "Busca os dados de um código Qr")
	public ResponseEntity<ResponseDTO<Object>> lerCodigoPagamento(String base64) {
		ResponseDTO<Object> response;
		try {
			String dadosCodigoQr = CodigoQr.lerCodigo(base64);

			response = ResponseDTO.builder().sucessso(true).status(HttpStatus.OK.toString()).mensagem(StringUtils.EMPTY)
					.resultado(dadosCodigoQr).build();
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response = ResponseDTO.builder().sucessso(false).status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
					.mensagem(ex.getMessage()).resultado(null).build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
