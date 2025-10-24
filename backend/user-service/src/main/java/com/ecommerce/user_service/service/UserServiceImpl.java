package com.ecommerce.user_service.service;

import com.ecommerce.user_service.entity.User;
import com.ecommerce.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public User fetchUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public List<User> fetchUserList() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        User userDB = userRepository.findById(id).get();
        if(Objects.nonNull(user.getName()) && !"".equalsIgnoreCase(user.getName())){
            userDB.setName(user.getName());
        }
        if(Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())){
            userDB.setEmail(user.getEmail());
        }
        if(Objects.nonNull(user.getPassword()) && !"".equalsIgnoreCase(user.getPassword())){
            userDB.setPassword(user.getPassword());
        }
        if(Objects.nonNull(user.getPhoneNumber()) && !"".equalsIgnoreCase(user.getPhoneNumber())){
            userDB.setPhoneNumber(user.getPhoneNumber());
        }
        if(Objects.nonNull(user.getAddress()) && !"".equalsIgnoreCase(user.getAddress())){
            userDB.setAddress(user.getAddress());
        }
        return userRepository.save(userDB);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


}
