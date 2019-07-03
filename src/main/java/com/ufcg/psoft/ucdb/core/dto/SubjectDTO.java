package com.ufcg.psoft.ucdb.core.dto;

import com.ufcg.psoft.ucdb.core.models.Comment;
import com.ufcg.psoft.ucdb.core.models.Subject;
import java.util.ArrayList;
import java.util.List;

public class SubjectDTO {

    private Integer id;
    private String name;
    private List<Comment> commentList;
    private Integer likes;

    public SubjectDTO(Subject subject){
        this.id = subject.getId();
        this.name = subject.getName();
        this.likes = subject.getLikes().size();
        this.commentList = filterComments(subject.getCommentList());
    }

    private List<Comment> filterComments(List<Comment> comments){
        List<Comment> filteredComments = new ArrayList<>();
        for(Comment c : comments){
            if(c.isDeleted()){
                c.setMessage("");
            }
            filteredComments.add(c.getWithoutDeleted());
        }
        return filteredComments;
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

    public Integer getLikes() {
        return likes;
    }
}