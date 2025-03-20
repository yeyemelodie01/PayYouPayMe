package com.example.payyoupayme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/user")
	public String getUser() {
		return "User";
	}

	@GetMapping("/admin")
	public String getAdmin() {
		return "Admin";
	}
}