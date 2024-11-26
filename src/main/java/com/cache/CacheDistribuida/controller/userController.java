package com.cache.CacheDistribuida.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.cache.CacheDistribuida.model.User;
import com.cache.CacheDistribuida.repository.UserRepository;
import com.cache.CacheDistribuida.repositoryImp.UserRepositoryImp;
import com.cache.CacheDistribuida.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class userController {

	private final UserService userService;

	private final UserRepository userRepository;

	@Autowired
	public userController(UserService userService, UserRepository userRepository) {
		this.userService = userService;
		this.userRepository = userRepository;
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User createdUser = userService.createUser(user);
		return ResponseEntity.ok(createdUser);
	}

	@GetMapping
	public List<User> getUsers() {
		return userService.getUsers();

	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable String id) {
		User user = userService.getUserById(id);
		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User user) {
		try {
			user.setId(id);
			userService.updateUser(user);
			return ResponseEntity.ok(user);
		} catch (IllegalStateException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
	}

	@GetMapping("/keys")
	public ResponseEntity<List<String>> getKeys() {

		List<String> keys = userRepository.getKeys();
		return ResponseEntity.ok(keys);
	}

}
