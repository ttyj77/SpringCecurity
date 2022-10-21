package com.kong.sercurity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kong.domain.Account;
import com.kong.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userDetailsService")
public class CustomUserDetailsSercive implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		
		Account account = userRepository.findById(id);
		
		if(account == null) {
			throw new UsernameNotFoundException("UsernameNotFoundException");
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(account.getRole()));
		
		AccountContext accountContext = new  AccountContext(account, roles);
		
		return accountContext;
	}

}
