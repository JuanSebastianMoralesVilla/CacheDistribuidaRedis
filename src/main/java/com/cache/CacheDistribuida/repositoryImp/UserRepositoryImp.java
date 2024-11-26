package com.cache.CacheDistribuida.repositoryImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.cache.CacheDistribuida.model.User;
import com.cache.CacheDistribuida.repository.UserRepository;

@Repository
public class UserRepositoryImp implements UserRepository {

	public static final String USER_KEY = "USER_";

	private final HashOperations<String, Object, Object> hashOperations;
	private final RedisTemplate<String, Object> redisTemplate;

	public UserRepositoryImp(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = this.redisTemplate.opsForHash();
	}

	public void save(User user) {
		String key = USER_KEY + user.getId();
		hashOperations.put(key, user.getId(), user);
	}

	/**
	 * Método que obtiene todos los usuarios almacenados en Redis.
	 * 
	 * Este método utiliza el prefijo de clave definido por USER_KEY para buscar
	 * todas las claves que correspondan a usuarios en Redis. Para cada clave encontrada,
	 * intenta obtener el usuario asociado desde la estructura Hash y lo agrega a la lista
	 * de usuarios si no es nulo.
	 *
	 * @return una lista de todos los usuarios encontrados en Redis. Si no se encuentran claves,
	 *         devuelve una lista vacía.
	 */
	public List<User> findAll() {
		Set<String> keysSet = redisTemplate.keys(USER_KEY + "*");
		List<User> users = new ArrayList<>();
		if (keysSet != null) {
			for (String key : keysSet) {
				User user = (User) hashOperations.get(key, key.replace(USER_KEY, ""));
				if (user != null) {
					users.add(user);
				}
			}
		}
		return users;
	}

	public User findById(String id) {
		String key = USER_KEY + id;
		return (User) hashOperations.get(key, id);
	}

	public void update(User user) {
		save(user);
	}

	public void delete(String id) {
		String key = USER_KEY + id;
		hashOperations.delete(key, id);
	}

	/**
	 * Este método recupera todas las claves almacenadas en Redis que comienzan con
	 * el prefijo definido por 'USER_KEY'.
	 * 
	 * @return Una lista de cadenas que contiene las claves encontradas en Redis. Si
	 *         no hay claves almacenadas, devuelve una lista vacía.
	 * 
	 *         Ejemplo: Si en Redis existen claves como "USER_1", "USER_2", este
	 *         método devolverá ["USER_1", "USER_2"].
	 */
	public List<String> getKeys() {
		Set<String> keysSet = redisTemplate.keys(USER_KEY + "*");
		return keysSet != null ? new ArrayList<>(keysSet) : new ArrayList<>();
	}

}
