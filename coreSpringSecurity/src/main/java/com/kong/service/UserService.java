package com.kong.service;

import org.springframework.ui.Model;

import com.kong.domain.Account;

public interface UserService {
	
	void createUser(Account account);
	void updateUser(Account account);
}
