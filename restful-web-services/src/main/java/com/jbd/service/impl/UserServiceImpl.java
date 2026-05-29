package com.jbd.service.impl;

import com.jbd.dao.UserDao;
import com.jbd.entity.User;
import com.jbd.exception.UserNotFoundException;
import com.jbd.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public User createUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User updateUser(Integer id, User user) {
        User existing = getUserById(id);
        existing.setName(user.getName());
        existing.setBirthDate(user.getBirthDate());
        return userDao.save(existing);
    }

    @Override
    public void deleteUser(Integer id) {
        if (!userDao.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userDao.deleteById(id);
    }
}
