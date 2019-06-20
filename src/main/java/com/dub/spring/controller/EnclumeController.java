package com.dub.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnclumeController {
	
	@RequestMapping("/enclume")
	public String enclume() {
		
		return "ENCLUME";
	}

}
