package com.kong.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


//Account 가 jpa가 관리하는 객체가 됨
@Entity
@Data

public class Account {
	
	@Id
	@GeneratedValue
	private Integer code;
	@Column(unique = true)
	private String id;
	private String password;
	private String role;
	private String name;
	private String email;
	private String telephone;
	private String address;
	private Integer companycode;
	private String companyname;
	private Integer frigeidid;
	
	@CreatedDate
	private LocalDate birth;
	
}
