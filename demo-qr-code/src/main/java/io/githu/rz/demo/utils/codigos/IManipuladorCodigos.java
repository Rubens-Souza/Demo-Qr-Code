package io.githu.rz.demo.utils.codigos;

public interface IManipuladorCodigos<T> {

	public String gerarCodigo(T dados);
	public T lerCodigo(String codigo);
	
}
