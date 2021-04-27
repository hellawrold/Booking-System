package com.lzc.assessment.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lzc.assessment.dao.UserRepo;
import com.lzc.assessment.entity.User;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println(userName);
		User user = userRepo.findByUserName(userName);
		return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(), mapRolesToAuthorities(user.getRole()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<String> roles){
		return roles.stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList());
	}

}
