package kr.co.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController   
@RequestMapping(value = "/study") 
public class StudyController {
	
	@GetMapping(value = "")
	public String main() {
		return "HI"; 
	}
	
	@GetMapping(value = "/test")
	public String test() {
		return "TEST"; 
	}
	
}
