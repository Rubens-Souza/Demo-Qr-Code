package io.github.rz.demo.utils;

import com.google.gson.Gson;

public class GsonUtils {

	public static <T> String stringfy(T dados) {
		Gson conversorJson = new Gson();
		return conversorJson.toJson(dados);
	}
	
	public static <T> T parse(String dados, Class<T> classeGerada) {
		Gson conversorJson = new Gson();
		return conversorJson.fromJson(dados, classeGerada);
	}
}
