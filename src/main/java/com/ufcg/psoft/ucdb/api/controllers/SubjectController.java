package com.ufcg.psoft.ucdb.api.controllers;

import com.ufcg.psoft.ucdb.api.services.SubjectService;
import com.ufcg.psoft.ucdb.core.dto.CommentDTO;
import com.ufcg.psoft.ucdb.core.dto.ReplyDTO;
import com.ufcg.psoft.ucdb.core.dto.SubjectDTO;
import com.ufcg.psoft.ucdb.core.models.SimpleSubject;
import com.ufcg.psoft.ucdb.core.models.Subject;
import java.util.List;

import com.ufcg.psoft.ucdb.core.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subject")
    public ResponseEntity<List<SimpleSubject>> getSubject(@RequestParam("search") String subtring){
        List<SimpleSubject> subjectList = subjectService.searchByName(subtring);
        return new ResponseEntity<>(subjectList, HttpStatus.OK);
    }

    @GetMapping("/subject/{id}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable Integer id){
        Subject subject = subjectService.getSubject(id);
        SubjectDTO subjectDTO = new SubjectDTO(subject);
        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }

    @PostMapping("/subject/{subjectId}/comment")
    public ResponseEntity<Subject> addComment(@PathVariable Integer subjectId, @RequestBody CommentDTO commentDTO){
        Subject subject = subjectService.addComment(subjectId, commentDTO);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @DeleteMapping("/subject/{subjectId}/comment/{commentId}")
    public ResponseEntity<SubjectDTO> deleteComment(@PathVariable Integer subjectId, @PathVariable Integer commentId){
        Subject subject = subjectService.deleteComment(subjectId, commentId);
        SubjectDTO subjectDTO = new SubjectDTO(subject);
        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }

    @PostMapping("/subject/{subjectId}/comment/{commentId}/reply")
    public ResponseEntity<Subject> addReply(@PathVariable Integer subjectId, @PathVariable Integer commentId, @RequestBody ReplyDTO replyDTO){
        Subject subject = subjectService.replyComment(subjectId, commentId, replyDTO);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }


    @DeleteMapping("/subject/{subjectId}/comment/{commentId}/reply/{replyId}")
    public ResponseEntity<SubjectDTO> depleteReply(@PathVariable Integer subjectId, @PathVariable Integer commentId, @PathVariable Integer replyId){
        Subject subject = subjectService.deleteReply(subjectId, commentId, replyId);
        SubjectDTO subjectDTO = new SubjectDTO(subject);
        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }

    @PostMapping("/subject/{subjectId}/like")
    public ResponseEntity<SubjectDTO> like(@PathVariable Integer subjectId){
        String user = getCurrentUser();
        Subject subject = this.subjectService.like(subjectId, user);
        SubjectDTO subjectDTO = new SubjectDTO(subject);
        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }

    @GetMapping("/subject/ranking")
    public ResponseEntity<List<SubjectDTO>> getRanking(@RequestParam String method){
        List<SubjectDTO> subjectDTOS = this.subjectService.getRanking(method);
        return new ResponseEntity<>(subjectDTOS, HttpStatus.OK);
    }

    private String getCurrentUser(){
        String email = ((UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return email;
    }

}
