package com.ufcg.psoft.ucdb.api.http.repositories;

import com.ufcg.psoft.ucdb.core.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
