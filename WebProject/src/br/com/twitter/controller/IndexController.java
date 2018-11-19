package br.com.twitter.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class IndexController {
	
	// Mapeamento de JSP
	@RequestMapping(value = { "/", "home" })
	public String index() {
		return "index";
	}
	
	
}