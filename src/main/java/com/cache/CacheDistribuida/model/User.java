package com.cache.CacheDistribuida.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
@RedisHash("User")
public class User implements Serializable{ 
	
	private static final long serialVersionUID=1L;
	
	@Id
	private String id;
	private String name;
	private String lastname;
	  
	
	public User() {
	    }
	
	
	public User(String id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
	
		
		

}
