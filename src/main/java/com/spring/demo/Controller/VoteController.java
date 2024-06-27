package com.spring.demo.Controller;

import com.spring.demo.Model.Comment;
import com.spring.demo.Model.Voter;
import com.spring.demo.Service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin("*")
@RequestMapping("/voters")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("/signup")
    public Voter SignUp(@RequestBody Voter voter){
        return voteService.saveVoter(voter);
    }

    @GetMapping("/all")
    public List<Voter> getVoters(){
        return voteService.getVoters();
    }

    @GetMapping("/L")
    public String getVoter(){
        return voteService.getVoter();
    }

    @GetMapping("/allPage{offset}/{pageSize}")
    public Page<Voter> findVoterByPagination(@PathVariable Integer offset, @PathVariable Integer pageSize){
        return voteService.findVoterByPagination(offset,pageSize);
    }

    @GetMapping("/getById/{id}")
    public Optional<Voter> getVoterById(@PathVariable Long id){
        return voteService.getVoterById(id);
    }

    @GetMapping("/getByName/{nme}")
    public List<Voter> findByName(@PathVariable String nme){
        return voteService.getVoterByName(nme);
    }
    @GetMapping("/getByEmail/{email}")
    public Optional<Voter> getVoterByEmail(@PathVariable String email){
        return voteService.getVoterByEmail(email);
    }

    @PutMapping("/update/{id}")
    Voter updateVoter(@RequestBody Voter newVoter,@PathVariable Long id){
        return voteService.updateVoter(newVoter,id);
    }

    @DeleteMapping("/delete/{id}")
    String deleteVoterById(@PathVariable Long id){
        return voteService.deleteVoterById(id);
    }

    @GetMapping("/admin")
    public Optional<Voter> getAdmin(){
        return voteService.getAdminById(54321L);
    }

    @PostMapping("/saveComment/{voter_email}")
    public Comment createComment(@PathVariable String voter_email,@RequestBody Comment comment){
        return voteService.createComment(voter_email,comment);
    }

    @GetMapping("/voter/{voter_id}")
    public List<Comment> getVoterComments(@PathVariable Long voter_id){
        return voteService.getVoterComments(voter_id);
    }

    @GetMapping("/v")
    public Stream<Voter> voters(){
        return voteService.voters();
    }


}

