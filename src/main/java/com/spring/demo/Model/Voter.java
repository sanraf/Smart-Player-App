package com.spring.demo.Model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name = "voters",uniqueConstraints = {
        @UniqueConstraint( name = "email_unique",columnNames = "email"),
        @UniqueConstraint(name = "id_unique",columnNames = "id" )
})
public class Voter {

    @Id
    @Column(unique = true)
    private Long id;
    private String name;

    @Column(nullable = false)
    private String email;
    private String password;

//    @ColumnDefault("none")
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany(mappedBy = "voter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;




}

