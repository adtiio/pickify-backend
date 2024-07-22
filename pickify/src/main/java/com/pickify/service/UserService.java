package com.pickify.service;

import com.pickify.exception.UserException;
import com.pickify.model.User;

public interface UserService {
	
	public User findUserById(Long userId)throws  UserException;
	
	public User findUserProfileByJwt(String jwt)throws UserException;
}
