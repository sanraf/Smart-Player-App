package com.spring.demo.Service;

import com.spring.demo.Model.User;
import com.spring.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;
    public List<User> getUsers(){
        return userRepository.findAll();
    }

//    public List<Comment> userComments(int userId){
//        User findUser = userRepository.findById(userId).orElseThrow();
//        return findUser.getComments();
//    }

    public List<User> deleteUser(int userId){
        userRepository.deleteById(userId);
        return userRepository.findAll();
    }

    public User create(User user){
        return userRepository.save(user);
    }
}
