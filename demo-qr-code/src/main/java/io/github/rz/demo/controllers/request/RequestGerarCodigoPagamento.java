package io.github.rz.demo.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestGerarCodigoPagamento {

	public String numeroConta;
	public String dataVencimento;
	public Integer valor;
	
}
