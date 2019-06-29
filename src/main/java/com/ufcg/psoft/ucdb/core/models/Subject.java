package com.ufcg.psoft.ucdb.core.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList;
    @ElementCollection
    private List<Integer> likes;

    public Subject(String name) {
        this.name = name;
    }

    public Subject(){}

    public void addLike(Integer userId){
        likes.add(userId);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public List<Integer> getLikes() {
        return likes;
    }
}
