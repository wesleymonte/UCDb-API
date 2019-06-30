package com.ufcg.psoft.ucdb.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies;
    private boolean deleted;

    public Comment(String author, String msg) {
        this.author = author;
        this.msg = msg;
        this.timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());
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

    public String getTimestamp() {
        return timestamp;
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

    public boolean isDeleted(){
        return this.deleted;
    }

    private void setTimestamp(String timestamp){
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
}
