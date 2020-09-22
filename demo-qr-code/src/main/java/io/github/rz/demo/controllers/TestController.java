package io.github.rz.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Teste" })
public class TestController {

	@GetMapping
	@ApiOperation(value = "Testando o swagger")
	@RequestMapping(method = RequestMethod.GET, path = "teste")
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("Topzera");
	}
}
