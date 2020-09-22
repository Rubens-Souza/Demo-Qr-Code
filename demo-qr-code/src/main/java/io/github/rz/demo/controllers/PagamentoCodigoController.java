package io.github.rz.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.rz.demo.controllers.request.RequestGerarCodigoPagamento;
import io.github.rz.demo.controllers.response.ResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/pagamentos/codigo")
@Api(tags = { "Pagamentos por códigos" })
public class PagamentoCodigoController {

	@PostMapping("/gerarCodigoPagamento")
	@ApiOperation(value = "Gera um código para realizar o pagamento ao ser escaneado")
	public ResponseEntity<ResponseDTO<String>> gerarCodigoPagamento(RequestGerarCodigoPagamento request) {
		
		
		
		return null;
	}
	
}
