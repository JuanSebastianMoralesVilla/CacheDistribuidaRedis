package com.cache.CacheDistribuida;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.cache.CacheDistribuida.model.User;
import com.cache.CacheDistribuida.repositoryImp.UserRepositoryImp;

import java.util.List;
import java.util.Set;

public class UserRepositoryTest {

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private HashOperations<String, Object, Object> hashOperations; // Tipo de HashOperations actualizado

    private UserRepositoryImp userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
        userRepository = new UserRepositoryImp(redisTemplate);
    }

    @Test
    void testUserSave() {
        User user = new User("1", "Juan", "Morales");
        userRepository.save(user);
        verify(hashOperations).put("USER" + "_" + user.getId(), user.getId(), user);
    }
    
    @Test
    void testFindAll() {
    	 // Crear usuarios de prueba
        User user1 = new User("1", "Juan", "Morales");
        User user2 = new User("2", "Ana", "Pérez");
        User user3 = new User("3", "Martin", "Lopez");

        // Simulamos las claves de Redis
        Set<String> mockKeys = Set.of("USER_1", "USER_2", "USER_3");

        // Simulamos los valores asociados a las claves
        when(redisTemplate.keys("USER_*")).thenReturn(mockKeys);
        when(hashOperations.get("USER_1", "1")).thenReturn(user1);
        when(hashOperations.get("USER_2", "2")).thenReturn(user2);
        when(hashOperations.get("USER_3", "3")).thenReturn(user3);

        // Llamar al método a probar
        List<User> users = userRepository.findAll();

        // Verificar que se obtienen los usuarios correctamente
        assertEquals(3, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
        assertTrue(users.contains(user3));

        // Verificar que las llamadas a Redis fueron hechas
        verify(redisTemplate).keys("USER_*");
        verify(hashOperations).get("USER_1", "1");
        verify(hashOperations).get("USER_2", "2");
        verify(hashOperations).get("USER_3", "3");
    }

    @Test
    void testUserFindById() {
       
        

        User user = new User("1", "Juan", "Perez");

     
        when(hashOperations.get("USER_1", "1")).thenReturn(user);

  
        User result = userRepository.findById("1");

  
        assertNotNull(result);
        assertEquals("Juan", result.getName());
        assertEquals("Perez", result.getLastname());
    }

    @Test
    void testUserDelete() {
        userRepository.delete("1");
        verify(hashOperations).delete("USER_1","1");
    }
    
    @Test
    void testUserUpdate() {
        User user = new User("1", "Sebastian", "Morales");
        userRepository.update(user);
        verify(hashOperations).put("USER" + "_" + user.getId(), user.getId(), user);
    }

}
