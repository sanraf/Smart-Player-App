package com.spring.demo.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Entity
@Table(name = "candidate",uniqueConstraints = { @UniqueConstraint(name = "id_unique",columnNames = "id" )})
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(nullable = false)
    private String party;

    @Column(nullable = false)
    private String post;


    @ColumnDefault("0")
    private Integer voteCount;

    @Lob
    @Column(name = "photo",columnDefinition = "MEDIUMBLOB")
    private byte[] photo;


    public Candidate() {}
    public Candidate(Long id,Integer voteCount) {
        this.id = id;
        this.voteCount = voteCount;
    }
    public Candidate(String fullName, String party, String post, Integer voteCount, byte[] photo) {
        this.fullName = fullName;
        this.party = party;
        this.post = post;
        this.voteCount = voteCount;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }


    public List<Voter> getVoter() {
        return voter;
    }

    public void setVoter(List<Voter> voter) {
        this.voter = voter;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_voter_fk")
    private List<Voter> voter ;
}
