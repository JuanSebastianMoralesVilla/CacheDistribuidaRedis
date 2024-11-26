package com.cache.CacheDistribuida.servicesImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.cache.CacheDistribuida.model.User;
import com.cache.CacheDistribuida.repository.UserRepository;
import com.cache.CacheDistribuida.services.UserService;


@Service
public class UserServiceImp implements UserService{

	
	private final UserRepository userRepository;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	  

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public User createUser(User user) {
    	
    	if (user == null || user.getName() == null) {
            throw new IllegalArgumentException("Datos invalidos.");
        }
    	
    	if (userRepository.findById(user.getId()) != null) {
            throw new IllegalStateException("El id ya existe" + user.getId());
        }
    	
        userRepository.save(user);
		return user;
    }
    @Override
	public List getUsers() {
        return userRepository.findAll();
    }
    @Override
    public User getUserById(String id) {
        return userRepository.findById(id);
    }
    @Override
    public void updateUser(User user) {
    	
    	if (userRepository.findById(user.getId()) == null) {
            throw new IllegalStateException("No existe el usuario, no se puede actualizar");
        }

        userRepository.update(user);
    }
    @Override
    public void deleteUser(String id) {
    	 if (userRepository.findById(id) == null) {
 	        throw new IllegalStateException("No existe el usuario no se puede borrar " + id);
 	    }
        userRepository.delete(id);
    }

	
    
}
