package kr.co.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/study")
public class StudyController {
	
	@GetMapping(value = "/github-webhook")
	public String main() {
		return "HI"; 
	}
	
}
