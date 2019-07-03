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
    private List<Comment> replies;
    private boolean deleted;

    public Comment(String author, String msg) {
        this.author = author;
        this.msg = msg;
        this.timestamp = Instant.now().getEpochSecond();
        this.replies = new ArrayList<>();
        this.deleted = false;
    }

    private Comment(Integer id, String author, String msg, long timestamp, List<Comment> replies){
        this.id = id;
        this.author = author;
        this.msg = msg;
        this.timestamp = timestamp;
        this.replies = replies;
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

    public List<Comment> getReplies() {
        return replies;
    }

    public void addReply(Comment comment){
        this.replies.add(comment);
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

    private void setReplies(List<Comment> replies){
        this.replies = replies;
    }

    public void setMessage(String message){
        this.msg = message;
    }

    @JsonIgnore
    public List<Comment> getNotDeletedReplies(){
        List<Comment> r = new ArrayList<>();
        for(Comment comment : this.getReplies()){
            if(!comment.isDeleted()){
                r.add(comment);
            } else {
                comment.setMessage("");
                r.add(comment);
            }
        }
        return r;
    }

    @JsonIgnore
    public Comment getWithoutDeleted(){
        List<Comment> replies = this.getNotDeletedReplies();
        Comment c = new Comment(getId(), getAuthor(), getMsg(), getTimestamp(), replies);
        return c;
    }

    public boolean deleteReply(String author, Integer replyId) {
        for(Comment c : this.getReplies()){
            if(c.getId().equals(replyId)){
                if(c.getAuthor().equals(author)){
                    c.delete();
                    return true;
                }
            }
        }
        return false;
    }
}
