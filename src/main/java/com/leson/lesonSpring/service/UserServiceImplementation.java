package com.leson.lesonSpring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leson.lesonSpring.dto.UserDto;
import com.leson.lesonSpring.exceptions.UserException;
import com.leson.lesonSpring.modal.User;
import com.leson.lesonSpring.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerUser(User user) throws UserException {

		Optional<User> isEmailExist = userRepository.findByEmail(user.getEmail());

		if (isEmailExist.isPresent()) {
			throw new UserException("Email is Alerady Exist");
		}

		Optional<User> isUsernameExist = userRepository.findByUsername(user.getUsername());

		if (isUsernameExist.isPresent()) {
			throw new UserException("Username is Alerady Token...");
		}

		if (user.getEmail() == null || user.getPassword() == null || user.getName() == null
				|| user.getUsername() == null) {
			throw new UserException("All filds are required");
		}

		User newUser = new User();

		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setUsername(user.getUsername());
		newUser.setName(user.getName());

		return userRepository.save(newUser);
	}

	@Override
	public User findUserById(Integer userId) throws UserException {
		Optional<User> opt = userRepository.findById(userId);

		if (opt.isPresent()) {
			return opt.get();
		}

		throw new UserException("user not exist with id: " + userId);
	}

	@Override
	public User findUserProfile(String token) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByUserName(String username) throws UserException {
		Optional<User> opt = userRepository.findByUsername(username);

		if (opt.isPresent()) {
			return opt.get();
		}

		throw new UserException("user not exist with username: " + username);
	}

	@Override
	public String followUser(Integer regUserId, Integer followUserId) throws UserException {

		User regUser = findUserById(regUserId);
		User followUser = findUserById(followUserId);

		UserDto follower = new UserDto();

		follower.setEmail(regUser.getEmail());
		follower.setId(regUser.getId());
		follower.setName(regUser.getName());
		follower.setUserImage(regUser.getUsername());
		follower.setUsername(regUser.getUsername());

		UserDto following = new UserDto();
		following.setEmail(follower.getEmail());
		following.setId(follower.getId());
		following.setUserImage(follower.getUserImage());
		following.setName(follower.getName());
		follower.setUsername(follower.getUsername());

		regUser.getFollowing().add(following);
		followUser.getFollower().add(follower);

		userRepository.save(followUser);
		userRepository.save(regUser);

		return "you are following " + followUser.getUsername();
	}

	@Override
	public String unFollowUser(Integer regUserId, Integer followUserId) throws UserException {
		User regUser = findUserById(regUserId);
		User followUser = findUserById(followUserId);

		UserDto follower = new UserDto();

		follower.setEmail(regUser.getEmail());
		follower.setId(regUser.getId());
		follower.setName(regUser.getName());
		follower.setUserImage(regUser.getUsername());
		follower.setUsername(regUser.getUsername());

		UserDto following = new UserDto();
		following.setEmail(follower.getEmail());
		following.setId(follower.getId());
		following.setUserImage(follower.getUserImage());
		following.setName(follower.getName());
		follower.setUsername(follower.getUsername());

		regUser.getFollowing().remove(following);
		followUser.getFollower().remove(follower);

		userRepository.save(followUser);
		userRepository.save(regUser);

		return "you have unfollowed " + followUser.getUsername();
	}

	@Override
	public List<User> findUserByIds(List<Integer> userIds) throws UserException {
		List<User> users = userRepository.findAllUsersByUserIds(userIds);

		return users;
	}

	@Override
	public List<User> searchUser(String query) throws UserException {
		List<User> users = userRepository.findByQuery(query);

		if (users.size() == 0) {
			throw new UserException("user not found");
		}

		return users;
	}

	@Override
	public User updateUserDetails(User updatedUser, User existingUser) throws UserException {

		if (updatedUser.getEmail() != null) {
			existingUser.setEmail(updatedUser.getEmail());
		}
		if (updatedUser.getBio() != null) {
			existingUser.setBio(updatedUser.getBio());
		}
		if (updatedUser.getName() != null) {
			existingUser.setName(updatedUser.getName());
		}
		if (updatedUser.getUsername() != null) {
			existingUser.setUsername(updatedUser.getUsername());
		}
		if (updatedUser.getMobile() != null) {
			existingUser.setMobile(updatedUser.getMobile());
		}
		if (updatedUser.getGender() != null) {
			existingUser.setGender(updatedUser.getGender());
		}
		if (updatedUser.getWebsite() != null) {
			existingUser.setWebsite(updatedUser.getWebsite());
		}
		if (updatedUser.getImage() != null) {
			existingUser.setImage(updatedUser.getImage());
		}
		if (updatedUser.getId().equals(existingUser.getId())) {
			return userRepository.save(existingUser);
		}

		throw new UserException("you cant update this user");
	}

}
