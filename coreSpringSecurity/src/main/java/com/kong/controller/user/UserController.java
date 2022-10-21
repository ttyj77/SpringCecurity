package com.kong.controller.user;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kong.domain.Account;
import com.kong.domain.AccountDto;
import com.kong.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller

public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping(value = "/mypage")
	public String myPage() throws Exception {
		return "user/mypage";
	}

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
	
//	@GetMapping("/mypage")
//	public String mypage(@AuthenticationPrincipal Account account, Authentication authentication, Principal principal) throws Exception{
//		return "user/mapage";
//	}

}
