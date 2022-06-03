package com.wipro.bookAppBackend.Controller;


import com.wipro.bookAppBackend.Exception.InvalidUserNameOrPassword;
import com.wipro.bookAppBackend.Exception.UserAlreadyExist;
import com.wipro.bookAppBackend.Model.LoginData;
import com.wipro.bookAppBackend.Model.ErrorMessage;
import com.wipro.bookAppBackend.Model.User;
import com.wipro.bookAppBackend.Service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign_up")
    public ResponseEntity<ErrorMessage> f1(@RequestBody User user) throws UserAlreadyExist{
        authService.register(user);
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.CREATED,"success"), HttpStatus.CREATED);
    }

    @PostMapping("/log_in")
    public ResponseEntity<String> f2(@RequestBody LoginData loginData) throws InvalidUserNameOrPassword{
        authService.logIn(loginData);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }
}
