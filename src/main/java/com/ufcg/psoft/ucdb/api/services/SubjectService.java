package com.ufcg.psoft.ucdb.api.services;

import com.ufcg.psoft.ucdb.api.repositories.SubjectRepository;
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

    public List<Subject> searchByName(String substring){
        List<Subject> foundSubjects = new ArrayList<>();
        List<Subject> subjects = subjectRepository.findAll();
        for(Subject s : subjects){
            if(s.getName().toLowerCase().contains(substring.trim().toLowerCase())){
                foundSubjects.add(s);
            }
        }
        return foundSubjects;
    }
}
