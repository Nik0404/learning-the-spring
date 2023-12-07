package com.leson.lesonSpring.service;

import java.util.List;

import com.leson.lesonSpring.exceptions.UserException;
import com.leson.lesonSpring.modal.User;

public interface UserService {

	public User registerUser(User user) throws UserException;
	public User findUserById(Integer userId) throws UserException;
	public User findUserProfile(String token) throws UserException;
	public User findUserByUserName(String username) throws UserException;
	public String followUser(Integer regUserId, Integer followUserId) throws UserException;
	
	public String unFollowUser(Integer regUserId, Integer followUserId) throws UserException;
	
	public List<User> findUserByIds(List<Integer> userIds) throws UserException;
	
	public List<User> searchUser(String query) throws UserException;
	
	public User updateUserDetails(User updatedUser, User existingUser) throws UserException;
	
	
}
