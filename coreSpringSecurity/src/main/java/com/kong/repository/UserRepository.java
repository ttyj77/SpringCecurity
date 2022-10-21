package com.kong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kong.domain.Account;

public interface UserRepository extends JpaRepository<Account, Integer>{

	public Account findByName(String name);
	public Account findById(String id);
	
	
}
