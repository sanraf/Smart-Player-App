package com.spring.demo.Controller;


import com.spring.demo.Model.Comment;
import com.spring.demo.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
        @PostMapping("/save/{voter_id}")
    public Comment createComment(@PathVariable Long voter_id,@RequestBody Comment comment){
        return commentService.createComment(voter_id,comment);
    }

    @GetMapping("/all")
    public List<Comment> getAll(){
        return commentService.getAll();
    }

    @GetMapping("/cc")
    public List<Comment> getComments(){
        return commentService.getComments();
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public Page<Comment> findAllCommentsByPagination(@PathVariable Integer offset,@PathVariable Integer pageSize){
        return commentService.findAllCommentsByPagination(offset,pageSize);
    }

    @DeleteMapping("/delete/{comment_id}")
    public void deleteCommentById(@PathVariable Long comment_id){
         commentService.deleteCommentById(comment_id);
    }

}
