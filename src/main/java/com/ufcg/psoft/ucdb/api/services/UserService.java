package com.ufcg.psoft.ucdb.api.services;

import com.ufcg.psoft.ucdb.core.UcdbController;
import com.ufcg.psoft.ucdb.core.exception.UserRegisteredException;
import com.ufcg.psoft.ucdb.core.models.User;
import com.ufcg.psoft.ucdb.api.repositories.UserRepository;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private UcdbController ucdbController;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void addUser(User user){
        if(valitadeUser(user)){
            String psEncoded = bCryptPasswordEncoder.encode(user.getPassword());
            User userEncoded = new User(user.getEmail(), user.getFirstName(), user.getLastName(), psEncoded);
            repository.save(userEncoded);
            sendEmail(user.getEmail());
            LOGGER.info("Added user [" + user.getEmail() + "]");
        } else {
            LOGGER.error("User [" + user.getEmail() + "] already registered");
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

    private void sendEmail(String email){
        this.ucdbController.sendRegistrationEmail(email);
    }
}
