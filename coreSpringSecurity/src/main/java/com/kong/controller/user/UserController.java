package com.kong.controller.user;

import java.security.Principal;

import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kong.domain.Account;
import com.kong.domain.AccountDto;
import com.kong.service.UserService;

@Controller

public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

//	@GetMapping(value = "/mypage")
//	public String myPage() throws Exception {
//		return "user/mypage";
//	}

	@GetMapping("/users")
	public String createUser() {
		return "user/login/register";
	}

	@PostMapping("/users")
	public String createUser(AccountDto accountDto) {
		// 소스는 사용자 정보가 담긴 accountDto 객체가 담긴 정보를 entity에 옮겨야 한다.

		ModelMapper modelMapper = new ModelMapper();
		Account account = modelMapper.map(accountDto, Account.class);
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		userService.createUser(account);
		return "redirect:/";
	}
	
	
	@PostMapping("/update")
	public String updateUser(AccountDto accountDto, Model model) {
	if(accountDto != null) {
		
		model.addAttribute(model);
		System.out.println("===================update model: " + model);
		Session.update(model);
		System.out.println("===================accountDto: " + accountDto);
		userService.updateUser(account);
		
	}
	
		return "redirect:/";
	}
	
	@GetMapping("/mypage")
	public String mypage(@AuthenticationPrincipal Account account, Authentication authentication, Principal principal,Model model) throws Exception{
		System.out.println("Account============================"+account);
		System.out.println("authentication============================"+authentication);
		System.out.println("principal============================"+principal);
		
//		model.addAttribute("id",account.getId());
//		model.addAttribute("name", account.getName());
//		model.addAttribute("address", account.getAddress());
//		model.addAttribute("email", account.getEmail());
//		model.addAttribute("telephone", account.getTelephone());
//		model.addAttribute("birth", account.getBirth());
		
		model.addAttribute("account", account);
		
		System.out.println("Model getId ======================"+ model);
		
		
		return "user/mypage";
	}

}
