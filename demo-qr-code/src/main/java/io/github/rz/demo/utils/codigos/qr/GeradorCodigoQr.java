package io.github.rz.demo.utils.codigos.qr;

public class GeradorCodigoQr {
	
	public <T> GeradorCodigoQrBuilder<T> gerarCodigo(T dados) {
		return new GeradorCodigoQrBuilder<T>(dados);
	}
}
