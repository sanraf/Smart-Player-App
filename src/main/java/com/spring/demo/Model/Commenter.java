package com.spring.demo.Model;

import jakarta.persistence.*;

@Entity
public class Commenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_id;
    private String username;
    private String date_created;

    @Column(length = 1000)
    private String comment;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public Commenter() {
    }

    public Commenter(int comment_id, String username, String date_created, String comment, User user) {
        this.comment_id = comment_id;
        this.username = username;
        this.date_created = date_created;
        this.comment = comment;
        this.user = user;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
