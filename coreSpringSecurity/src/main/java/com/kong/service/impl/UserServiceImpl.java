package com.kong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kong.domain.Account;
import com.kong.repository.UserRepository;
import com.kong.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	@Override
	public void createUser(Account account) {
		System.out.println(account);
		userRepository.save(account);
	}
}
