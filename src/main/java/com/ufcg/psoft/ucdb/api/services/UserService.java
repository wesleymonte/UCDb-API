package com.ufcg.psoft.ucdb.api.services;

import com.ufcg.psoft.ucdb.core.exception.UserRegisteredException;
import com.ufcg.psoft.ucdb.core.models.User;
import com.ufcg.psoft.ucdb.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public void addUser(User user){
        if(valitadeUser(user)){
            repository.save(user);
        } else {
            throw new UserRegisteredException("User [" + user.getEmail() + "] already registered");
        }

    }

    public User getUser(String email){
        Optional<User> obj = repository.findById(email);
        return obj.orElse(null);
    }

    private boolean valitadeUser(User user){
        User finded = getUser(user.getEmail());
        if(finded == null){
            return true;
        }
        return false;
    }
}
