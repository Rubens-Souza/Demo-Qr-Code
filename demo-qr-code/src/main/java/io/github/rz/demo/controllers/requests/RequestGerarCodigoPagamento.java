package io.github.rz.demo.controllers.requests;

import java.util.Date;

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
	public String numeroAgencia;
	public Date dataVencimento;
	public Integer valor;
	
}
