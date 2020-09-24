package io.github.rz.demo.utils.codigos;

public interface IGeradorCodigo<T> {

	public String criarCodigoBase64(T dados, Integer altura, Integer largura, String formato);
	
}
