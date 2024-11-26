package com.cache.CacheDistribuida.repository;

import java.util.List;
import java.util.Optional;

import com.cache.CacheDistribuida.model.User;

public interface UserRepository {
	
	
	    void save(User user);
	    List<User> findAll();
	    User findById(String id);
	    void update(User user);
	    void delete(String id);
		List<String> getKeys();


}
