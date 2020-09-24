package io.github.rz.demo.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestLerCodigoPagamento {

	private String codigoPagamentoBase64;
	
}
