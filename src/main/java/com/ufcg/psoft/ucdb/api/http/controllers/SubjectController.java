package com.ufcg.psoft.ucdb.api.http.controllers;

import com.ufcg.psoft.ucdb.api.constans.ApiDocumentation;
import com.ufcg.psoft.ucdb.api.http.services.SubjectService;
import com.ufcg.psoft.ucdb.core.dto.CommentDTO;
import com.ufcg.psoft.ucdb.core.dto.SubjectDTO;
import com.ufcg.psoft.ucdb.core.models.SimpleSubject;
import com.ufcg.psoft.ucdb.core.models.Subject;
import java.util.List;

import com.ufcg.psoft.ucdb.core.security.UserSS;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@Api(ApiDocumentation.Subject.API_DESCRIPTION)
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subject")
    @ApiOperation(value = ApiDocumentation.Subject.SEARCH_OPERATION)
    public ResponseEntity<List<SimpleSubject>> getSubject(
            @ApiParam(ApiDocumentation.Subject.SUBSTRING_TO_SEARCH)
            @RequestParam("search") String subtring){
        List<SimpleSubject> subjectList = subjectService.searchByName(subtring);
        return new ResponseEntity<>(subjectList, HttpStatus.OK);
    }

    @GetMapping("/subject/{subjectId}")
    @ApiOperation(ApiDocumentation.Subject.GET_BY_ID_OPERATION)
    public ResponseEntity<SubjectDTO> getSubjectById(
            @ApiParam(ApiDocumentation.Subject.SUBJECT_ID)
            @PathVariable Integer subjectId){
        String author = this.getCurrentUser();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Author", author);

        Subject subject = subjectService.getSubject(subjectId);
        SubjectDTO subjectDTO = new SubjectDTO(subject);
        return new ResponseEntity<>(subjectDTO, headers, HttpStatus.OK);
    }

    @PostMapping("/subject/{subjectId}/comment")
    @ApiOperation(ApiDocumentation.Subject.ADD_COMMENT_OPERATION)
    public ResponseEntity<SubjectDTO> addComment(
            @ApiParam(ApiDocumentation.Subject.SUBJECT_ID)
            @PathVariable Integer subjectId,
            @ApiParam(ApiDocumentation.Subject.COMMENT_ENTITY)
            @RequestBody CommentDTO commentDTO){
        String author = this.getCurrentUser();
        Subject subject = subjectService.addComment(subjectId, author, commentDTO);
        SubjectDTO subjectDTO = new SubjectDTO(subject);
        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }

    @PostMapping("/subject/{subjectId}/comment/{commentId}")
    @ApiOperation(ApiDocumentation.Subject.ADD_REPLY_OPERATION)
    public ResponseEntity<SubjectDTO> addReply(
            @ApiParam(ApiDocumentation.Subject.SUBJECT_ID)
            @PathVariable Integer subjectId,
            @ApiParam(ApiDocumentation.Subject.COMMENT_ID)
            @PathVariable Integer commentId,
            @ApiParam(ApiDocumentation.Subject.COMMENT_ENTITY)
            @RequestBody CommentDTO commentDTO){
        String author = this.getCurrentUser();
        Subject subject = subjectService.addReply(subjectId, commentId, author, commentDTO);
        SubjectDTO subjectDTO = new SubjectDTO(subject);
        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }

    @DeleteMapping("/subject/{subjectId}/comment/{commentId}")
    @ApiOperation(ApiDocumentation.Subject.DELETE_COMMENT_OPERATION)
    public ResponseEntity<SubjectDTO> deleteComment(
            @ApiParam(ApiDocumentation.Subject.SUBJECT_ID)
            @PathVariable Integer subjectId,
            @ApiParam(ApiDocumentation.Subject.COMMENT_ID)
            @PathVariable Integer commentId){
        String author = this.getCurrentUser();
        Subject subject = subjectService.deleteComment(subjectId, author, commentId);
        SubjectDTO subjectDTO = new SubjectDTO(subject);
        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }

    @PostMapping("/subject/{subjectId}/like")
    @ApiOperation(ApiDocumentation.Subject.ADD_LIKE_OPERATION)
    public ResponseEntity<SubjectDTO> addLike(
            @ApiParam(ApiDocumentation.Subject.SUBJECT_ID)
            @PathVariable Integer subjectId){
        String user = getCurrentUser();
        Subject subject = this.subjectService.addLike(subjectId, user);
        SubjectDTO subjectDTO = new SubjectDTO(subject);
        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }

    @DeleteMapping("/subject/{subjectId}/like")
    @ApiOperation(ApiDocumentation.Subject.DELETE_LIKE_OPERATION)
    public ResponseEntity<SubjectDTO> removeLike(
            @ApiParam(ApiDocumentation.Subject.SUBJECT_ID)
            @PathVariable Integer subjectId){
        String user = getCurrentUser();
        Subject subject = this.subjectService.removeLike(subjectId, user);
        SubjectDTO subjectDTO = new SubjectDTO(subject);
        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }

    @GetMapping("/subject/ranking")
    @ApiOperation(ApiDocumentation.Subject.GET_RANKING_OPERATION)
    public ResponseEntity<List<SubjectDTO>> getRanking(
            @ApiParam(ApiDocumentation.Subject.RANKING_METHOD)
            @RequestParam String method){
        List<SubjectDTO> subjectDTOS = this.subjectService.getRanking(method);
        return new ResponseEntity<>(subjectDTOS, HttpStatus.OK);
    }

    private String getCurrentUser(){
        String email = ((UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return email;
    }

}
