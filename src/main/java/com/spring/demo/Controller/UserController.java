package com.spring.demo.Controller;

import com.spring.demo.Model.User;
import com.spring.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
@Autowired
private UserService userService;
    @PostMapping("/saveUser")
    public User saveUser(@RequestBody User user){
        return userService.create(user);
    }
}
