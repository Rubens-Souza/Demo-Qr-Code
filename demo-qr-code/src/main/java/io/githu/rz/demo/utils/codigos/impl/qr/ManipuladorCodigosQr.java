package io.githu.rz.demo.utils.codigos.impl.qr;

import io.githu.rz.demo.utils.codigos.IGeradorCodigo;
import io.githu.rz.demo.utils.codigos.ILeitorCodigo;
import io.githu.rz.demo.utils.codigos.IManipuladorCodigos;

public class ManipuladorCodigosQr<T> implements IManipuladorCodigos<T> {

	private IGeradorCodigo<T> gerador;
	private ILeitorCodigo<T> leitor;
	
	public ManipuladorCodigosQr() {
		gerador = new GeradorCodigoQr<T>();
		leitor = new LeitorCodigoQr<T>();
	}
	
	@Override
	public String gerarCodigo(T dados) {
		return gerador.criarCodigoBase64(dados);
	}

	@Override
	public T lerCodigo(String codigo,  Class<T> classeGerada) {
		return leitor.lerCodigoBase64(codigo, classeGerada);
	}
}
