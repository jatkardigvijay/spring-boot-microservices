package com.jbd.dao;

import com.jbd.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> findAll();
    Optional<User> findById(Integer id);
    User save(User user);
    void deleteById(Integer id);
    boolean existsById(Integer id);
}
