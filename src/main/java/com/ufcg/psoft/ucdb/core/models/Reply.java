package com.ufcg.psoft.ucdb.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String author;
    private String msg;
    private String timestamp;
    private boolean deleted;

    public Reply(String author, String msg) {
        this.author = author;
        this.msg = msg;
        this.timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());
    }

    public Reply(){}

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

    @JsonIgnore
    public boolean isDeleted() {
        return this.deleted;
    }

    public void delete(){
        this.deleted = true;
    }
}
