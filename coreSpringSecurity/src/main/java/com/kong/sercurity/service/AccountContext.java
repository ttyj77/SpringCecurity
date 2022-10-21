package com.kong.sercurity.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.kong.domain.Account;

import lombok.Data;


@Data
public class AccountContext extends User{

	  private Account account;

	  public AccountContext(Account account, List<GrantedAuthority> roles) {
	    super(account.getId(), account.getPassword(), roles);
	    this.account = account;
	  }
	public Account getAccount() {
		return account;
	}

}
