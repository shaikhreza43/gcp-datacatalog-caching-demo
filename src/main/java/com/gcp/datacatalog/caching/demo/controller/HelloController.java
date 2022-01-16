package com.gcp.datacatalog.caching.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String welcome() {
		return "Welcome to Spring Boot";
	}
}
