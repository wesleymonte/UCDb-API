package com.ufcg.psoft.ucdb.core.models;

import com.ufcg.psoft.ucdb.core.dto.CommentDTO;
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
    private List<String> likes;

    public Subject(String name) {
        this.name = name;
    }

    public Subject(){}

    public void like(String userId){
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

    public List<String> getLikes() {
        return likes;
    }

    public void addComment(Comment comment){
        this.commentList.add(comment);
    }

    public boolean deleteComment(String author, Integer commentId) {
        for(Comment c : this.getCommentList()){
            if(c.getId().equals(commentId)){
                if(c.getAuthor().equals(author)){
                    c.delete();
                    return true;
                }
            }
            if(c.deleteReply(author, commentId)) return true;
        }
        return false;
    }

    public boolean addReply(Integer commentId, Comment reply){
        for(Comment c : this.commentList){
            if(c.getId().equals(commentId)){
                c.addReply(reply);
                return true;
            }
        }
        return false;
    }

    public boolean removeLike(String user) {
        List<String> likes = this.getLikes();
        for(int i = 0; i < likes.size(); i++){
            if(likes.get(i).equals(user)){
                this.likes.remove(i);
                return true;
            }
        }
        return false;
    }
}
