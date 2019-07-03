package com.ufcg.psoft.ucdb.core.dto;

import com.ufcg.psoft.ucdb.core.models.Comment;
import com.ufcg.psoft.ucdb.core.models.Subject;
import com.ufcg.psoft.ucdb.core.security.UserSS;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;

public class SubjectDTO {

    private Integer id;
    private String name;
    private List<Comment> commentList;
    private Integer likes;
    private boolean userLike;

    public SubjectDTO(Subject subject){
        this.id = subject.getId();
        this.name = subject.getName();
        this.likes = subject.getLikes().size();
        this.commentList = filterComments(subject.getCommentList());
        this.userLike = checkUserLike(subject.getLikes());
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

    private boolean checkUserLike(List<String> likes){
        String user = ((UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return likes.contains(user);
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

    public boolean isUserLike() {
        return userLike;
    }
}