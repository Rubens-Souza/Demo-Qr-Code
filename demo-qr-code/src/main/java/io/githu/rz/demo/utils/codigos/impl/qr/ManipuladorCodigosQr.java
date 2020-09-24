package io.githu.rz.demo.utils.codigos.impl.qr;

import io.githu.rz.demo.utils.codigos.IGeradorCodigo;
import io.githu.rz.demo.utils.codigos.ILeitorCodigo;
import io.githu.rz.demo.utils.codigos.IManipuladorCodigos;
import io.githu.rz.demo.utils.codigos.enums.FormatoImagens;

public class ManipuladorCodigosQr<T> implements IManipuladorCodigos<T> {

	private final FormatoImagens FORMATO_PADRAO_IMAGEM_QR_CODE = FormatoImagens.PNG;
		
	private final Integer ALTURA_PADRAO = 100;
	private final Integer LARGURA_PADRAO = 100;
	
	private IGeradorCodigo<T> gerador;
	private ILeitorCodigo<T> leitor;
	
	public ManipuladorCodigosQr() {
		gerador = new GeradorCodigoQr<T>();
		leitor = new LeitorCodigoQr<T>();
	}
	
	// TODO: Aterar gerar codigo para ser um builder pattern
	@Override
	public String gerarCodigo(T dados) {
		return gerador.criarCodigoBase64(dados, ALTURA_PADRAO, LARGURA_PADRAO, FORMATO_PADRAO_IMAGEM_QR_CODE.getFormato());
	}
	
	@Override
	public String gerarCodigo(T dados, Integer altura, Integer largura) {
		return gerador.criarCodigoBase64(dados, altura, largura, FORMATO_PADRAO_IMAGEM_QR_CODE.getFormato());
	}
	
	@Override
	public String gerarCodigo(T dados, Integer altura, Integer largura, FormatoImagens formato) {
		return gerador.criarCodigoBase64(dados, altura, largura, formato.getFormato());
	}

	@Override
	public T lerCodigo(String codigo,  Class<T> classeGerada) {
		return leitor.lerCodigoBase64(codigo, classeGerada);
	}
}
