package com.example.recipeProject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.recipeProject.domain.Login;
import com.example.recipeProject.domain.LoginRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

private final LoginRepository repository;
	
	@Autowired
	public UserDetailServiceImpl(LoginRepository userRepository) {
		this.repository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login curruser = repository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, 
				curruser.getPasswordHash(),
				AuthorityUtils.createAuthorityList(curruser.getRole()));
		
		return user;
	}
}
