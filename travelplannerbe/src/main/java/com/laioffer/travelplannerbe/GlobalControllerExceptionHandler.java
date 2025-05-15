package com.laioffer.travelplannerbe;

import com.laioffer.travelplannerbe.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.laioffer.travelplannerbe.model.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException e) {
        return new ResponseEntity<>(
                new ErrorResponse("User not found", "user_not_found"),
                HttpStatus.NOT_FOUND);
    }
}
