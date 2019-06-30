package com.ufcg.psoft.ucdb.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String author;
    private String msg;
    private long timestamp;
    private boolean deleted;

    public Reply(String author, String msg) {
        this.author = author;
        this.msg = msg;
        this.timestamp = Instant.now().getEpochSecond();
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

    @JsonIgnore
    public long getTimestamp() {
        return timestamp;
    }

    public String getDate(){
        Date date = new java.util.Date(this.timestamp*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-3"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    @JsonIgnore
    public boolean isDeleted() {
        return this.deleted;
    }

    public void delete(){
        this.deleted = true;
    }
}
