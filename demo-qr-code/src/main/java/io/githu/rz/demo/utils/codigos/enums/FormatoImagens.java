package io.githu.rz.demo.utils.codigos.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormatoImagens {
	PNG("png"),
	JPEG("jpeg"),
	JPG("jpg"),
	SVG("svg");
	
	private String formato;
}
