package com.spring.demo.Repository;

import com.spring.demo.Model.Commenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommenterRepo extends JpaRepository<Commenter,Integer> {
}
