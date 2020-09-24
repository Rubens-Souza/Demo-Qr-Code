package io.github.rz.demo.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.rz.demo.controllers.requests.RequestGerarCodigoPagamento;
import io.github.rz.demo.controllers.requests.RequestLerCodigoPagamento;
import io.github.rz.demo.controllers.responses.ResponseDTO;
import io.github.rz.demo.dto.CodigoQrDTO;
import io.github.rz.demo.dto.PagamentoDTO;
import io.github.rz.demo.services.PagamentoCodigoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/pagamentos/codigo")
@Api(tags = { "Pagamentos por códigos" })
public class PagamentoCodigoController {

	@Autowired
	private PagamentoCodigoService service;

	@PostMapping("/gerarCodigoPagamento")
	@ApiOperation(value = "Gera um código para realizar o pagamento ao ser escaneado")
	public ResponseEntity<ResponseDTO<Object>> gerarCodigoPagamento(RequestGerarCodigoPagamento request) {
		ResponseDTO<Object> response;
		try {
			PagamentoDTO dadosPagamento = new PagamentoDTO(request);
			CodigoQrDTO codigoQrPagamento = service.gerarCodigoPagamento(dadosPagamento);
			response = ResponseDTO.builder().sucessso(true).status(HttpStatus.OK.toString()).mensagem(StringUtils.EMPTY)
					.resultado(codigoQrPagamento).build();

			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response = ResponseDTO.builder().sucessso(false).status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
					.mensagem(ex.getMessage()).resultado(null).build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PostMapping("/lerCodigoPagamento")
	@ApiOperation(value = "Busca as informações presentes em um código de pagamento")
	public ResponseEntity<ResponseDTO<Object>> lerCodigoPagamento(RequestLerCodigoPagamento request) {
		return null;	
	}

}
