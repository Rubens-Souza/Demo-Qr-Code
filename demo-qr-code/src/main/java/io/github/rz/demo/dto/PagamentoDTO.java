package io.github.rz.demo.dto;

import java.util.Date;

import io.github.rz.demo.controllers.requests.RequestGerarCodigoPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDTO {

	public String numeroConta;
	public String numeroAgencia;
	public Date dataVencimento;
	public Integer valor;
	
	public PagamentoDTO(RequestGerarCodigoPagamento request) {
		this.numeroConta = request.numeroConta;
		this.numeroAgencia = request.numeroAgencia;
		this.dataVencimento = request.dataVencimento;
		this.valor = request.valor;
	}
}
