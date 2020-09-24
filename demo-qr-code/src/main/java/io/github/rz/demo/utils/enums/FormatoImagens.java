package io.github.rz.demo.utils.enums;

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
