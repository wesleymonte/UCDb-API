package com.ufcg.psoft.ucdb.api.services;

import com.ufcg.psoft.ucdb.api.repositories.SubjectRepository;
import com.ufcg.psoft.ucdb.core.models.Subject;
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
}
