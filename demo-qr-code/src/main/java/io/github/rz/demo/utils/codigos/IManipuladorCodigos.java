package io.github.rz.demo.utils.codigos;

import io.github.rz.demo.utils.enums.FormatoImagens;

public interface IManipuladorCodigos<T> {

	public String gerarCodigo(T dados);
	public String gerarCodigo(T dados, Integer altura, Integer largura);
	public String gerarCodigo(T dados, Integer altura, Integer largura, FormatoImagens formato);
	
	public T lerCodigo(String codigo, Class<T> classeGerada);
	
}
