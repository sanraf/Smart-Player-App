package com.spring.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 1000)
    private String comment;

    private String voterName;

    private String voterEmail;


    @Column(name = "commented_time", nullable = true, updatable = false)
    private String commentedTime;

    public Comment() { }
    public Comment(String comment, String voterName) {
        this.comment = comment;
        this.voterName = voterName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getVoterName() {
        return voterName;
    }

    public String getVoterEmail() {
        return voterEmail;
    }

    public void setVoterEmail(String voterEmail) {
        this.voterEmail = voterEmail;
    }

    public String getCommentedTime() {
        return commentedTime;
    }

    public void setCommentedTime(String commentedTime) {
        this.commentedTime = commentedTime;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "voter_id")
    private Voter voter;
}
