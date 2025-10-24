package com.ecommerce.user_service.service;

import com.ecommerce.user_service.entity.User;


import java.util.List;


public interface UserService {
    public User register(User user);

    public User fetchUserById(Long id);

    public List<User> fetchUserList();

    public User updateUser(Long id, User user);

    public void deleteUserById(Long id);
}
