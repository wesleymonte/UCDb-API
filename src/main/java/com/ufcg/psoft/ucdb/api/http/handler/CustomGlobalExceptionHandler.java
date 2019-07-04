package com.ufcg.psoft.ucdb.api.http.handler;

import com.ufcg.psoft.ucdb.api.exception.CommentNotFoundException;
import com.ufcg.psoft.ucdb.api.exception.LikeNotFoundException;
import com.ufcg.psoft.ucdb.api.exception.SubjectNotFoundException;
import com.ufcg.psoft.ucdb.api.exception.UserAlreadyLikedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ SubjectNotFoundException.class, CommentNotFoundException.class ,
        UserAlreadyLikedException.class, LikeNotFoundException.class})
    public ResponseEntity<CustomErrorResponse> customHandleNotFound(Exception ex, WebRequest request) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

    }
}
