package com.spring.demo.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int task_id;
    private String title;
    private String description;
    private String owner;
    private String start_date;
    private String end_date;
    private String status;
    private String priority;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "commenter_id")
    private List<Commenter> commenter;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<User> user;

    public Task() {
    }

    public Task(int task_id, String title, String description, String owner, String start_date, String end_date, String status, String priority, List<Commenter> commenter, List<User> user) {
        this.task_id = task_id;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
        this.priority = priority;
        this.commenter = commenter;
        this.user = user;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public List<Commenter> getCommenter() {
        return commenter;
    }

    public void setCommenter(List<Commenter> commenter) {
        this.commenter = commenter;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
