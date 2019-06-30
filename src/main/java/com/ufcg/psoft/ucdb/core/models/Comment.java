package com.ufcg.psoft.ucdb.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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
    private long timestamp;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies;
    private boolean deleted;

    public Comment(String author, String msg) {
        this.author = author;
        this.msg = msg;
        this.timestamp = Instant.now().getEpochSecond();
        this.replies = new ArrayList<>();
        this.deleted = false;
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

    public List<Reply> getReplies() {
        return replies;
    }

    public void addReply(Reply reply){
        this.replies.add(reply);
    }

    public void delete(){
        this.deleted = true;
    }

    @JsonIgnore
    public boolean isDeleted(){
        return this.deleted;
    }

    private void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    private void setId(Integer id){
        this.id = id;
    }

    private void setReplies(List<Reply> replies){
        this.replies = replies;
    }

    @JsonIgnore
    public List<Reply> getNotDeletedReplies(){
        List<Reply> r = new ArrayList<>();
        for(Reply reply : this.getReplies()){
            if(!reply.isDeleted()){
                r.add(reply);
            }
        }
        return r;
    }

    @JsonIgnore
    public Comment getWithoutDeleted(){
        Comment c = new Comment(this.author, this.msg);
        c.setId(this.getId());
        c.setReplies(this.getNotDeletedReplies());
        c.setTimestamp(this.getTimestamp());
        return c;
    }

    public void deleteReply(Integer replyId) {
        for(Reply r : this.getReplies()){
            if(r.getId().equals(replyId)){
                r.delete();
                break;
            }
        }
    }
}
