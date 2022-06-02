package com.wipro.bookAppBackend.Controller;


import com.wipro.bookAppBackend.Exception.InvalidUserNameOrPassword;
import com.wipro.bookAppBackend.Exception.UserAlreadyExist;
import com.wipro.bookAppBackend.Model.LoginData;
import com.wipro.bookAppBackend.Model.ResponseType;
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
    public ResponseEntity<ResponseType> f1(@RequestBody User user){
        try {
            authService.register(user);
            return new ResponseEntity<>(new ResponseType("success"), HttpStatus.CREATED);
        }catch(UserAlreadyExist userAlreadyFound){
            return new ResponseEntity<>(new ResponseType("already_exist"), HttpStatus.CONFLICT);

        }
    }

    @PostMapping("/log_in")
    public ResponseEntity<String> f2(@RequestBody LoginData loginData){
        try {
            authService.logIn(loginData);
            return new ResponseEntity<>("success",HttpStatus.OK);
        }catch (InvalidUserNameOrPassword invalidUserNameOrPassword){
            return new ResponseEntity<>("invalid_username_or_password",HttpStatus.FORBIDDEN);
        }
    }
}
