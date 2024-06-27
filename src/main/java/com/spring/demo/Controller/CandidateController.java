package com.spring.demo.Controller;

import com.spring.demo.DTO.APIResponse;
import com.spring.demo.Model.Candidate;
import com.spring.demo.Model.Comment;
import com.spring.demo.Repository.CandidateRepository;
import com.spring.demo.Service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin("*")
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    CandidateService candidateService;
    @Autowired
    CandidateRepository candidateRepository;

    @PostMapping("/save")
    public ResponseEntity<Candidate> save(
            @RequestParam("fullName") String fullName,
            @RequestParam("party") String party,
            @RequestParam("post") String post,
            @RequestParam(value = "voteCount",required = false,defaultValue = "0") Integer voteCount,
            @RequestParam("photo") MultipartFile photo
    ){
        try {
            System.out.println("Received parameters: " + fullName + ", " + party + ", " + post + ", " + voteCount);

            Candidate savedCandidate = candidateService.createCandidate(fullName, party, post, voteCount, photo).getBody();

            if (savedCandidate != null) {
                return new ResponseEntity<>(savedCandidate, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public List<Candidate> getAllCandidate() {
        return candidateService.getAllCandidate();
    }

    @GetMapping("/allll")
    public List<Candidate> getAllUsers(){
        return candidateService.getAllUsers();
    }

    @GetMapping("/getById/{candidateId}")
    public Optional<Candidate> getCandidateById(@PathVariable Long candidateId) {
        return candidateService.getCandidateById(candidateId);
    }

    @DeleteMapping("/delete/{candidateId}")
    public void deleteUser(@PathVariable Long candidateId) {
        candidateService.deleteCandidate(candidateId);
    }


    @PutMapping("/assignVoteToCandidate/{voter_email}/{candidate_id}")
    public Candidate assignVoteToCandidate(@PathVariable String voter_email,@PathVariable Long candidate_id){
            return candidateService.assignVoteToCandidateAndVote(voter_email,candidate_id);
    }

    @PutMapping("/setVoteCount/{candidate_id}")
    public Optional<Candidate> setVoteCount(@PathVariable Long candidate_id){
        return candidateService.setVoteCount(candidate_id);

    }

    @GetMapping("/getVoters/{i}")
    public List<Candidate> getVoters(@PathVariable Long i){
        List<Candidate> candidateList = candidateRepository.findAll();
        List<Candidate> candidates = candidateList.stream()
                .filter(n->n.getFullName() !=null).limit(i).collect(Collectors.toList());
        return candidates;
    }

//    @GetMapping("/getNames")
//    public List<Voter> flatMapping(){
//        return candidateService.flatMapping();
//    }

    @GetMapping("/testmap")
    public List<String> testMap(){
    return candidateService.testMap();
    }

    @GetMapping("/totalVotes")
    public Float sumVotes(){
        return candidateService.sumVotes();
    }


    @GetMapping("/getVotersFromCandidate")
    public Stream<Stream<String>> getVotersFromCandidate(){
        return candidateService.getVotersFromCandidate() ;
    }

    @GetMapping("/sort/{field}")
    public APIResponse<List<Candidate>> findCandidateBySorting(@PathVariable String field){
        List<Candidate> candidateBySorting = candidateService.findCandidateBySorting(field);
        return new APIResponse<>(candidateBySorting.size(),candidateBySorting);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public Page<Candidate> findCandidateByPagination(@PathVariable Integer offset,@PathVariable Integer pageSize){
        return candidateService.findCandidateByPagination(offset,pageSize);
    }


//    @GetMapping("getfew")
//    public List<Candidate> getFewCan(){
//        return candidateService.getFewCan();
//    }


}
