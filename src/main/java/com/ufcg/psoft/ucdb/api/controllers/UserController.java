package com.ufcg.psoft.ucdb.api.controllers;

import com.ufcg.psoft.ucdb.core.exception.UserRegisteredException;
import com.ufcg.psoft.ucdb.core.models.User;
import com.ufcg.psoft.ucdb.api.services.UserService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/v1")
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping("/user")
    @ResponseBody
    public ResponseEntity<?> addUser(@RequestBody User user){
        LOGGER.info("Adding new user [" + user.getEmail() + "]");
        try {
            userService.addUser(user);
            return new ResponseEntity<>(new AddUserResponse(user.getEmail(), "User added successfully"), HttpStatus.OK);
        } catch (UserRegisteredException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{email}")
    @ResponseBody
    public ResponseEntity<?> addUser(@PathVariable String email){
        LOGGER.info("Getting user with email [" + email + "].");
        User user = userService.getUser(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/version")
    @ResponseBody
    public ResponseEntity<?> getVersion(){
        return new ResponseEntity<>("0.0.1", HttpStatus.OK);
    }

    private class AddUserResponse {
        private String email;
        private String msg;

        public AddUserResponse(String email, String msg) {
            this.email = email;
            this.msg = msg;
        }

        public String getEmail() {
            return email;
        }

        public String getMsg() {
            return msg;
        }
    }
}
