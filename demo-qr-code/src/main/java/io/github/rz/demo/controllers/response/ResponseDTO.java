package io.github.rz.demo.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {

	private boolean sucessso;
	private String status;
	private String mensagem;
	private T resultado;
	
}
