package com.spring.demo.Service;

import com.spring.demo.Exception.VoterException;
import com.spring.demo.Model.Candidate;
import com.spring.demo.Model.Voter;
import com.spring.demo.Repository.VoterRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.spring.demo.Repository.CandidateRepository;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.zip.Deflater;

@Service
public class CandidateService  {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    VoterRepository voterRepository;


        public List<Candidate> getAllCandidate() {
            return candidateRepository.findAll();
        }

        public Optional<Candidate> getCandidateById(Long userId) {
            return candidateRepository.findById(userId);
        }

        public void deleteCandidate(Long candidateId) {
            candidateRepository.deleteById(candidateId);
        }

        public Candidate assignVoteToCandidateAndVote(String voter_email, Long candidate_id){

            Candidate candidate = candidateRepository.findById(candidate_id).orElseThrow(()->new VoterException(candidate_id));
            Voter voter = voterRepository.findByEmail(voter_email).orElseThrow(()-> new VoterException(voter_email));
            List<Voter> voters = candidate.getVoter();
            voters.add(voter);
            candidate.setVoter(voters);
            candidate.setVoteCount(candidate.getVoteCount()+1);
            return candidateRepository.save(candidate);

        }

        public Optional<Candidate> setVoteCount(Long candidate_id){

            return candidateRepository.findById(candidate_id)
                    .map(new_count ->{
                                new_count.setVoteCount(new_count.getVoteCount()+1);
                                return candidateRepository.save(new_count);

                            });

        }


        public Stream<Stream<String>> getVotersFromCandidate(){
            return candidateRepository.findAll()
            .stream().map(voter ->voter.getVoter()
                            .stream().map(Voter::getEmail));

        }


            public Float sumVotes(){
                    List<Candidate> candidates = candidateRepository.findAll();

                     return ((float) 100 / candidates.stream()
                                     .map(Candidate::getVoteCount)
                                     .reduce(Integer::sum)
                                     .orElse(1));
}


        public List<String> testMap(){
            List<Candidate>candidateList = candidateRepository.findAll();
            Function<Candidate,String> function = Candidate::getFullName;
            Stream<String> stringStream = candidateList.stream().map(function);
            return stringStream.collect(Collectors.toList());
        };


    public ResponseEntity<Candidate> createCandidate(
             String fullName,
             String party,
             String post,
             Integer voteCount,
             MultipartFile photo
    ) {
        try {
            Candidate getCandidate = new Candidate();
            getCandidate.setFullName(fullName);
            getCandidate.setParty(party);
            getCandidate.setPost(post);
            getCandidate.setVoteCount(voteCount);
            getCandidate.setPhoto(photo.getBytes());



            Candidate savedCandidate = candidateRepository.save(getCandidate);

            return new ResponseEntity<>(savedCandidate, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


        public List<Candidate> getAllUsers() {
            return candidateRepository.findAll();
        }

    private void decompressUserImage(Candidate user) {
        if (user.getPhoto() != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(user.getPhoto()));
                if (bufferedImage != null) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImage, "jpg", baos);

                } else {
                    // Log an error or handle the case where the image data is not valid
                    System.err.println("Invalid image data for user: " + user.getId());
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Log an error or handle the IOException
            }
        }
    }


    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    public List<Candidate> findCandidateBySorting(String field){
            return candidateRepository.findAll(Sort.by(field));
    }
    public Page<Candidate> findCandidateByPagination(Integer offset, Integer pageSize){
        return candidateRepository.findAll(PageRequest.of(offset, pageSize));

    }

}
