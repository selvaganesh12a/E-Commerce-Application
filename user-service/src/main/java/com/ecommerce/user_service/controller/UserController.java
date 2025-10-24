package com.ecommerce.user_service.controller;

import com.ecommerce.user_service.entity.User;
import com.ecommerce.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.json.JSONObject;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userService.register(user);
    }

    @GetMapping("/profiles")
    public List<User> fetchUserList(){
        return userService.fetchUserList();
    }

    @GetMapping("/profile/{id}")
    public String fetchUserById(@PathVariable("id") Long id){
        User user = userService.fetchUserById(id);
        if(user == null) return "User is Not Available";
        else{
            JSONObject obj = new JSONObject(user);
            return obj.toString();
        }
    }

    @PutMapping("/profile/{id}")
    public String updateUser(@PathVariable("id") Long id, @RequestBody User user){
        userService.updateUser(id,user);
        return "User Updated Successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return "User Deleted Successfully";
    }
}
