package com.pickify.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pickify.model.User;
import com.pickify.repository.UserRepository;

public class UserDetailServiceImpl implements UserDetailsService{

	
	@Autowired
	private  UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//fetch user from database
		User user= userRepository.findByEmail(username);

		if(user==null) {
			throw new UsernameNotFoundException("Cound not found user !!");
		}
		CustomUserDetails customUserDetails=new CustomUserDetails(user);
		return customUserDetails;
	}

}
