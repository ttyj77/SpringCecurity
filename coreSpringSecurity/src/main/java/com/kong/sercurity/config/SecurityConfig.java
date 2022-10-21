package com.kong.sercurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kong.sercurity.provider.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationDetailsSource authenticationDetailsSource;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/","/users")
				.permitAll()
				.antMatchers("/mypage").hasRole("USER")
				.antMatchers("/messages").hasRole("MANAGER")
				.antMatchers("/config").hasAnyRole("ADMIN","1")
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login_proc")
				.authenticationDetailsSource(authenticationDetailsSource)
				.defaultSuccessUrl("/")
				.permitAll();

	}

//	// 3명의 사용자를 만들었고 각각 권한을 부여했다.
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		String password = passwordEncoder().encode("1111");
//
//		auth.inMemoryAuthentication().withUser("user").password(password).roles("USER");
//		auth.inMemoryAuthentication().withUser("manager").password(password).roles("MANAGER", "USER");
//		auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN", "USER", "MANAGER");
//
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	
	}
	@Bean
	public AuthenticationProvider authenticationProvider() {
		return new CustomAuthenticationProvider();
	}

//	 보안필더를 거치지 않고 static에 있는 프론트 코드 넘김 근데 requestMathchers에 빨간줄뜸
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//	}

	
	
	
}
