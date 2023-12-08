package com.leson.lesonSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leson.lesonSpring.exceptions.UserException;
import com.leson.lesonSpring.modal.User;
import com.leson.lesonSpring.response.MessageResponse;
import com.leson.lesonSpring.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/id/{id}")
	public ResponseEntity<User> findUserByIdHandler(@PathVariable Integer id) throws UserException {
		User user = userService.findUserById(id);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("/username/{username}") 
	public ResponseEntity<User> findUserByUsernameHendler(@PathVariable String username) throws UserException {
		User user = userService.findUserByUserName(username);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PutMapping("/follow/{followUserId}")
	public ResponseEntity<MessageResponse> followUserHandleer(@PathVariable Integer followUserId) {

//		MessageResponse res = userService.followUser(followUserId, followUserId);

		return null;
	}

	@PutMapping("/unfollow/{userId}")
	public ResponseEntity<MessageResponse> unFollowUserHandleer(@PathVariable Integer userId) {

//		MessageResponse res = userService.followUser(followUserId, followUserId);

		return null;
	}

	@PutMapping("/reg/{userId}")
	public ResponseEntity<MessageResponse> findUserProfileHandler(@RequestHeader("Authorization") String token) {

		return null;
	}

	@GetMapping("/m/{usersIds}")
	public ResponseEntity<List<User>> findUserByUsersIdsHandler(@PathVariable List<Integer> userIds)
			throws UserException {
		List<User> users = userService.findUserByIds(userIds);

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<List<User>> searchUserNandler(@RequestParam("q") String query) throws UserException {

		List<User> users = userService.searchUser(query);

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	public ResponseEntity<User> updateUserHandler(@RequestHeader("Authorization") String token,
			@RequestBody UserController user) {

//		User updateUser = userService.updateUserDetails(user, user);

		return null;

	}
}
