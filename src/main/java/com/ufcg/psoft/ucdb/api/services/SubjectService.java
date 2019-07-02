package com.ufcg.psoft.ucdb.api.services;

import com.ufcg.psoft.ucdb.api.repositories.SubjectRepository;
import com.ufcg.psoft.ucdb.core.dto.CommentDTO;
import com.ufcg.psoft.ucdb.core.dto.SubjectDTO;
import com.ufcg.psoft.ucdb.core.models.Comment;
import com.ufcg.psoft.ucdb.core.models.SimpleSubject;
import com.ufcg.psoft.ucdb.core.models.Subject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.ucdb.core.models.comparators.CommentsCompare;
import com.ufcg.psoft.ucdb.core.models.comparators.LikesCompare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Collections.sort;

@Service
public class SubjectService {

    private static final String COMPARE_BY_COMMENTS = "comments";
    private static final String COMPARE_BY_LIKES = "likes";


    @Autowired
    private SubjectRepository subjectRepository;

    public Subject getSubject(Integer id){
        Optional<Subject> subject = subjectRepository.findById(id);
        return subject.orElse(null);
    }

    public List<SimpleSubject> searchByName(String substring){
        List<SimpleSubject> foundSubjects = new ArrayList<>();
        List<Subject> subjects = subjectRepository.findAll();
        for(Subject s : subjects){
            if(s.getName().toLowerCase().contains(substring.trim().toLowerCase())){
                SimpleSubject simpleSubject = new SimpleSubject(s);
                foundSubjects.add(simpleSubject);
            }
        }
        return foundSubjects;
    }

    public Subject addComment(Integer subjectId, String author, CommentDTO commentDTO){
        Comment comment = commentFromDTO(author, commentDTO);
        Subject subject = this.getSubject(subjectId);
        subject.addComment(comment);
        subject = subjectRepository.save(subject);
        return subject;
    }

    public Subject deleteComment(Integer subjectId, String author, Integer commentId){
        Subject subject = this.getSubject(subjectId);
        subject.deleteComment(author, commentId);
        subject = this.subjectRepository.save(subject);
        return subject;
    }

    public Subject addReply(Integer subjectId, Integer commentId, String author, CommentDTO commentDTO) {
        Subject subject = this.getSubject(subjectId);
        Comment reply = this.commentFromDTO(author, commentDTO);
        subject.addReply(commentId, reply);
        subject = this.subjectRepository.save(subject);
        return subject;
    }

    public Subject addLike(Integer subjectId, String user){
        Subject subject = getSubject(subjectId);
        subject.like(user);
        subject = this.subjectRepository.save(subject);
        return subject;
    }

    public Subject removeLike(Integer subjectId, String user) {
        Subject subject = getSubject(subjectId);
        subject.removeLike(user);
        subject = this.subjectRepository.save(subject);
        return subject;
    }

    public List<SubjectDTO> getRanking(String method){
        Comparator<Subject> comparator = getComparator(method);
        List<Subject> subjects = this.subjectRepository.findAll();
        sort(subjects, comparator.reversed());
        List<SubjectDTO> subjectDTOS = this.subjectsToDTO(subjects);
        return subjectDTOS;
    }

    private Comparator<Subject> getComparator(String method){
        Comparator<Subject> comparator = new CommentsCompare();
        switch (method){
            case COMPARE_BY_COMMENTS:
                comparator = new CommentsCompare();
                break;
            case COMPARE_BY_LIKES:
                comparator = new LikesCompare();
        }
        return comparator;
    }

    private List<SubjectDTO> subjectsToDTO(List<Subject> subjects){
        List<SubjectDTO> subjectDTOS = new ArrayList<>();
        for(Subject s : subjects){
            subjectDTOS.add(subjectToDTO(s));
        }
        return subjectDTOS;
    }

    private SubjectDTO subjectToDTO(Subject subject){
        return new SubjectDTO(subject);
    }

    private Comment commentFromDTO(String author, CommentDTO commentDTO){
        Comment comment = new Comment(author, commentDTO.getMsg());
        return comment;
    }
}
