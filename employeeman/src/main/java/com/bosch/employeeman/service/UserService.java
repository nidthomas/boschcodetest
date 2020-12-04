package com.bosch.employeeman.service;

import com.bosch.employeeman.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> getUsers();
    public User addNewEmployee(User newUser);
    public void deleteUserById(int userId);
}
