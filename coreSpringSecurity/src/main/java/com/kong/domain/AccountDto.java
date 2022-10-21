package com.kong.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

//클라이언트 쪽으로 부터 회원가입할 때 값을 받아오는게 Dto
@Data
public class AccountDto {
	
	private String id;
	private String password;
	private String role;
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birth;
	private String email;
	private String telephone;
	private String address;
	
	
	

	

}
