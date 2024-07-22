package com.pickify.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/test")
public class TempController {
	
	@ResponseBody
	@PostMapping("/one")
	public String test() {
		System.out.println("test");
		return "test";
	}
	public TempController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/")
	@ResponseBody
	public String home() {
		System.out.println("home");
		return "home";
	}
	
}
