package com.test.service.interfaces;

import com.test.model.User;
import javassist.NotFoundException;

import java.util.List;

public interface UserService {

    User findById(int id) throws NotFoundException;

    List<User> findAll();

    void saveUser(User user);

    void deleteById(int id);

}
