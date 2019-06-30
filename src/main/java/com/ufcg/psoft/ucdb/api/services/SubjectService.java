package com.ufcg.psoft.ucdb.api.services;

import com.ufcg.psoft.ucdb.api.repositories.SubjectRepository;
import com.ufcg.psoft.ucdb.core.dto.CommentDTO;
import com.ufcg.psoft.ucdb.core.dto.ReplyDTO;
import com.ufcg.psoft.ucdb.core.models.Comment;
import com.ufcg.psoft.ucdb.core.models.Reply;
import com.ufcg.psoft.ucdb.core.models.SimpleSubject;
import com.ufcg.psoft.ucdb.core.models.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

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

    public Subject addComment(Integer subjectId, CommentDTO commentDTO){
        Comment comment = commentFromDTO(commentDTO);
        Subject subject = this.getSubject(subjectId);
        subject.addComment(comment);
        subject = subjectRepository.save(subject);
        return subject;
    }

    public Subject replyComment(Integer subjectId, Integer commentId, ReplyDTO replyDTO){
        Reply reply = replyFromDTO(replyDTO);
        Subject subject = this.getSubject(subjectId);
        subject.replyComment(commentId, reply);
        subject = subjectRepository.save(subject);
        return subject;
    }

    public Subject deleteComment(Integer subjectId, Integer commentId){
        Subject subject = this.getSubject(subjectId);
        for(Comment c : subject.getCommentList()){
            if(c.getId().equals(commentId)){
                c.delete();
                break;
            }
        }
        this.subjectRepository.save(subject);
        return subject;
    }

    private Comment commentFromDTO(CommentDTO commentDTO){
        Comment comment = new Comment(commentDTO.getAuthor(), commentDTO.getMsg());
        return comment;
    }

    private Reply replyFromDTO(ReplyDTO replyDTO){
        Reply reply = new Reply(replyDTO.getAuthor(), replyDTO.getMsg());
        return reply;
    }
}
