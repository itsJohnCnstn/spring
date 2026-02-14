package com.johncnstn.spring.caching;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CachingController {

    public record User(Long id, String name) {
    }

    Map<Long, User> db = Map.of(
            1L, new User(1L, "Pawa"),
            2L, new User(2L, "Miha"),
            3L, new User(3L, "Ola")
    );

    @GetMapping("/{id}")
    @Cacheable("users")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        System.out.println("Get not yet cached user with id: " + id);
        User user = db.get(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    @CacheEvict("users")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        db.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @CachePut(value = "users", key = "#id")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        System.out.println(user);
        return ResponseEntity.ok(user);
    }

}
