package io.github.rz.demo.utils.codigos;

public interface ILeitorCodigo<T> {

	public T lerCodigoBase64(String codigo, Class<T> classeGerada);
	
}
