package com.spring.demo.Repository;

import com.spring.demo.Model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoterRepository extends JpaRepository<Voter,Long> {
    Optional<Voter> findByEmail(String email);
    List<Voter> findByName(String nam);
    @Query(value = "SELECT email FROM voters WHERE id =?1",nativeQuery = true)
    String findVoter(int c);


}