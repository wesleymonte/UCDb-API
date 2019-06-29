package com.ufcg.psoft.ucdb.api.repositories;

import com.ufcg.psoft.ucdb.core.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

}
