package io.githu.rz.demo.utils.codigos;

public interface IGeradorCodigo<T> {

	public String criarCodigoBase64(T dados);
	
}
