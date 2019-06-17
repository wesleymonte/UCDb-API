package com.ufcg.psoft.ucdb.api.controllers;

import com.ufcg.psoft.ucdb.core.exception.UserRegisteredException;
import com.ufcg.psoft.ucdb.core.models.User;
import com.ufcg.psoft.ucdb.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/v1")
public class UCDBController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    @ResponseBody
    public ResponseEntity<?> addUser(@RequestBody User user){
        try {
            userService.addUser(user);
            return new ResponseEntity<>("Hello World!", HttpStatus.OK);
        } catch (UserRegisteredException e){
            return new ResponseEntity<>("Hello World!", HttpStatus.BAD_REQUEST);
        }
    }
}
