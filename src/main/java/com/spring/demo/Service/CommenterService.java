package com.spring.demo.Service;

import com.spring.demo.Model.Commenter;
import com.spring.demo.Model.User;
import com.spring.demo.Repository.CommenterRepo;
import com.spring.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommenterService {

    @Autowired
    private CommenterRepo commenterRepo;

    @Autowired
    private UserRepo userRepo;
    public Commenter assignCommenter(int userId,Commenter commenter){
        User user = userRepo.findById(userId).get();
        commenter.setUser(user);
        return commenterRepo.save(commenter);
    }
}
