package com.wipro.bookAppBackend.Controller;


import com.wipro.bookAppBackend.Exception.InvalidUserNameOrPassword;
import com.wipro.bookAppBackend.Exception.UserAlreadyExist;
import com.wipro.bookAppBackend.Model.*;
import com.wipro.bookAppBackend.Service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;

    }

    @PostMapping("/sign_up")
    public ResponseEntity<RegisterResponse> f1(@RequestBody User user) throws UserAlreadyExist{
        RegisterResponse response = this.authService.register(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/log_in")
    public ResponseEntity<LogInResponse> f2(@RequestBody LoginData loginData) throws InvalidUserNameOrPassword{
        LogInResponse response = this.authService.logIn(loginData);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/user_detail_jwt")
    public ResponseEntity<UserDetailsResponse> f3(@RequestHeader("Authorization") String token){
        UserDetailsResponse userDetailsResponse = this.authService.getUserDetailsByJWT(token);
        return new ResponseEntity<>(userDetailsResponse,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<LogOutResponse> f4(){
        return null;
    }
    @GetMapping("/t")
    public String test(){
        return "success";
    }
}
