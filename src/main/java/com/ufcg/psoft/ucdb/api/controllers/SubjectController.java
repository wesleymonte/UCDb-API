package com.ufcg.psoft.ucdb.api.controllers;

import com.ufcg.psoft.ucdb.api.services.SubjectService;
import com.ufcg.psoft.ucdb.core.models.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subject/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable Integer id){
        Subject subject = subjectService.getSubject(id);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }
}
