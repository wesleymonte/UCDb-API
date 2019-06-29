package com.ufcg.psoft.ucdb.core.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private User author;
    private String msg;
    private String timestamp;

    public Comment(User author, String msg) {
        this.author = author;
        this.msg = msg;
        this.timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());
    }

    public Comment(){}

    public Integer getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getMsg() {
        return msg;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
