package io.github.rz.demo.utils.codigos.qr;

public class CodigoQr {
	
	public static <T> GeradorCodigoQrBuilder<T> gerarCodigo(T dados) {
		GeradorCodigoQr gerador = new GeradorCodigoQr();
		return gerador.gerarCodigo(dados);
	}
	
	public static <T> T lerCodigo(String codigo, Class<T> classeAlvo) {
		LeitorCodigoQr leitor = new LeitorCodigoQr();
		return leitor.lerCodigoBase64(codigo, classeAlvo);
	}
	
	public static String lerCodigo(String codigo) {
		LeitorCodigoQr leitor = new LeitorCodigoQr();
		return leitor.lerCodigoBase64(codigo);
	}
}
