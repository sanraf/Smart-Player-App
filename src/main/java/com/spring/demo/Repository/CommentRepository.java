package com.spring.demo.Repository;

import com.spring.demo.Model.Candidate;
import com.spring.demo.Model.Comment;
import com.spring.demo.Model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

   @Query(value = "SELECT L FROM Comment L")
   List<Comment> findComments();
}
