package com.spring.demo.Service;

import com.spring.demo.Exception.VoterException;
import com.spring.demo.Model.Comment;
import com.spring.demo.Model.Voter;
import com.spring.demo.Repository.CandidateRepository;
import com.spring.demo.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.spring.demo.Repository.VoterRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class VoteService {
    @Autowired
    VoterRepository voterRepository;
    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    CommentRepository commentRepository;

    public Voter saveVoter(Voter voter) {
        return voterRepository.save(voter);
    }

    public List<Voter> getVoters() {
        return voterRepository.findAll();
    }

    public Page<Voter> findVoterByPagination(Integer offset, Integer pageSize){
        return voterRepository.findAll(PageRequest.of(offset, pageSize));
    }

    public Optional<Voter> getVoterById(@PathVariable Long id) {
        return voterRepository.findById(id);
    }

    public List<Voter> getVoterByName(@PathVariable String name) {
        return voterRepository.findByName(name);
    }

    public Optional<Voter> getVoterByEmail(@PathVariable String email) {
        return voterRepository.findByEmail(email);
    }

    public String deleteVoterById(Long id){
        voterRepository.deleteById(id);
        return "voter with " +id +" is deleted successful";
    }

    public Voter updateVoter(@RequestBody Voter newVoter,@PathVariable Long id){
        return voterRepository.findById(id)
                .map(voter ->{
                    voter.setName(newVoter.getName());
                    voter.setName(newVoter.getName());
                    voter.setEmail(newVoter.getEmail());
                    voter.setPhone(newVoter.getPhone());
                    return voterRepository.save(voter);
                } ).orElseThrow(()->new IllegalArgumentException("user with id "+id+" not found"));
    }

    public Optional<Voter> getAdminById(Long id){
        return voterRepository.findById(id);
    }

    public Comment createComment(String voterEmail, Comment comment) {
        Voter voter = voterRepository.findByEmail(voterEmail).orElseThrow(()-> new VoterException(voterEmail));


        comment.setVoter(voter);
        comment.setVoterName(voter.getName());
        comment.setVoterEmail(voter.getEmail());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        String formattedCreationTime = LocalDateTime.now().format(formatter);
        comment.setCommentedTime(formattedCreationTime);
        return commentRepository.save(comment);
    }

    public List<Comment> getVoterComments(Long voter_id){
        Voter voter = voterRepository.findById(voter_id)
                .orElseThrow(()->new VoterException( voter_id));
        return voter.getComments();
    }


    public Stream<Voter> voters(){
        List<Voter> voterList = voterRepository.findAll();
      return   voterList.stream().filter(v->v.getComments() !=null);
    }

    public String getVoter(){
        return voterRepository.findVoter(12365);
    }

}
