package com.cache.CacheDistribuida.services;

import java.util.List;

import com.cache.CacheDistribuida.model.User;

public interface UserService {
	
	User createUser(User user);

    List<User> getUsers();

    User getUserById(String id);

    void updateUser(User user);

    void deleteUser(String id);

}
