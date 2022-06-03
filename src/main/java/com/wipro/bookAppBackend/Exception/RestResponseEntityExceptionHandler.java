package com.wipro.bookAppBackend.Exception;

import com.wipro.bookAppBackend.Model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidUserNameOrPassword.class)
    public ResponseEntity<ErrorMessage> invalidUserNameOrPassword(InvalidUserNameOrPassword invalidUserNameOrPassword,
                                                                  WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST,invalidUserNameOrPassword.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorMessage);
    }


    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<ErrorMessage> userAlreadyExist(UserAlreadyExist userAlreadyExist,
                                                         WebRequest webRequest){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.CONFLICT,userAlreadyExist.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(errorMessage);
    }
}
