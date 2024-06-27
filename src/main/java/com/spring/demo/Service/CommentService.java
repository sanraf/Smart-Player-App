package com.spring.demo.Service;

import com.spring.demo.Exception.VoterException;
import com.spring.demo.Model.Candidate;
import com.spring.demo.Model.Comment;
import com.spring.demo.Model.Voter;
import com.spring.demo.Repository.CommentRepository;
import com.spring.demo.Repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CommentService {


    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final VoterRepository voterRepository;

    public CommentService(CommentRepository commentRepository, VoterRepository voterRepository) {
        this.commentRepository = commentRepository;
        this.voterRepository = voterRepository;
    }


    public Comment createComment(Long voter_id,Comment comment){
        Voter voter = voterRepository.findById(voter_id).orElseThrow(()-> new VoterException(voter_id));


        comment.setVoter(voter);
        return commentRepository.save(comment);
    }

    public List<Comment> getAll(){
       return commentRepository.findAll();
    }

    public Page<Comment> findAllCommentsByPagination(Integer offset,Integer pageSize){
       return commentRepository.findAll(PageRequest.of(offset,pageSize));
    }

    public void deleteCommentById(Long comment_id){
        commentRepository.deleteById(comment_id);
    }

    public List<Comment> getComments(){
        return commentRepository.findComments();
    }
}



