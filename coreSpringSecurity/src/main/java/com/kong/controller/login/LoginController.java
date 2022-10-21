package com.kong.controller.login;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kong.domain.Account;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "user/login/login";
	}
	
	//인증을 받은 사용자가 로그아웃가능  로그아웃은 SecurityContextLogoutHandler이친구가 진행함
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		//authentication 이 널이 아니면 로그아웃진행
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/login";
	}
	
//	@GetMapping(value="/denied")
//	public String accessDenied(@RequestParam(value = "exception", required = false) String exception, Principal principal, Model model) throws Exception {
//
//		
//		model.addAttribute("username", account.getUsername());
//		model.addAttribute("exception", exception);
//
//		return "user/login/denied";
//	}
	
}
