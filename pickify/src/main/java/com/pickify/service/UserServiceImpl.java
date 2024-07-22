package com.pickify.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pickify.config.JwtProvider;
import com.pickify.exception.UserException;
import com.pickify.model.User;
import com.pickify.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	
	public UserServiceImpl( UserRepository userRepository, JwtProvider jwtProvider) {
		this.userRepository=userRepository;
		this.jwtProvider=jwtProvider;
	}
	

	@Override
	public User findUserById(Long userId) throws UserException {
		
		Optional<User> user=userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		throw new UserException("User not found with id: - "+userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {

		String email=jwtProvider.getEmailFromToken(jwt);
		
		User user=userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("User now found with email" + email);
		}
		
		return user;
	}

}
