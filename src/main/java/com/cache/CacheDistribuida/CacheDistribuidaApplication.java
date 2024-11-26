package com.cache.CacheDistribuida;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cache.CacheDistribuida.model.User;
import com.cache.CacheDistribuida.services.UserService;

@SpringBootApplication
public class CacheDistribuidaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheDistribuidaApplication.class, args);
		System.out.println("Sistema de cache");
		
	
		
		
	}
	
	 @Autowired
	    public void loadInitialData(UserService userService) {
	        System.out.println("Cargando datos iniciales en Redis...");

	        User user1 = new User("1", "Sebastian", "morales");
	        User user2 = new User("2", "Jane", "campo");

	        userService.createUser(user1);
	        userService.createUser(user2);

	        System.out.println("Datos iniciales cargados en Redis.");
	        
	        List<User> users = userService.getUsers();
	        System.out.println("Usuarios recuperados: " + users);
	    }

}
