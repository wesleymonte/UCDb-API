package com.ufcg.psoft.ucdb.api.http.controllers;

import com.ufcg.psoft.ucdb.api.constans.ApiDocumentation;
import com.ufcg.psoft.ucdb.core.exception.UserRegisteredException;
import com.ufcg.psoft.ucdb.core.models.User;
import com.ufcg.psoft.ucdb.api.http.services.UserService;
import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/v1")
@Api(ApiDocumentation.Signup.API_DESCRIPTION)
public class RegisterController {

    private static final Logger LOGGER = LogManager.getLogger(RegisterController.class);

    private final static String SUCCESSFULLY_MSG = "User added successfully. A message was sent to your email.";

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    @ResponseBody
    @ApiOperation(value = ApiDocumentation.Signup.REGISTER_USER)
    public ResponseEntity<?> registerUser(
            @ApiParam(value = ApiDocumentation.Signup.USER_BODY)
            @Valid @RequestBody User user){
        LOGGER.info("Adding new user [" + user.getEmail() + "]");
        try {
            userService.addUser(user);
            return new ResponseEntity<>(new AddUserResponse(user.getEmail(), SUCCESSFULLY_MSG), HttpStatus.OK);
        } catch (UserRegisteredException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
