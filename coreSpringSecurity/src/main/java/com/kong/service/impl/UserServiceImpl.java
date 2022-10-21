package com.kong.service.impl;

import java.util.Optional;

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

	@Override
	public void updateUser(Account account) {
		// TODO Auto-generated method stub
//		Optional<Store> storeOptional = storeRepository.findById(store.getId); 
		Optional<Account> updateUser = userRepository.findById(account.getId());
		System.err.println("update MOdel Service: ==========" + account);
		userRepository.up
		
	}
}
