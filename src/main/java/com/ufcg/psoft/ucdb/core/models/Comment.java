package com.ufcg.psoft.ucdb.core.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String author;
    private String msg;
    private String timestamp;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> replies;

    public Comment(String author, String msg) {
        this.author = author;
        this.msg = msg;
        this.timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());
        this.replies = new ArrayList<>();
    }

    public Comment(){}

    public Integer getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getMsg() {
        return msg;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void addReply(Comment comment){
        this.replies.add(comment);
    }
}
