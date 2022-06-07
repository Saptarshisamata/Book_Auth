package com.wipro.bookAppBackend.Controller;


import com.wipro.bookAppBackend.Exception.InvalidUserNameOrPassword;
import com.wipro.bookAppBackend.Exception.UserAlreadyExist;
import com.wipro.bookAppBackend.Model.LoginData;
import com.wipro.bookAppBackend.Model.ErrorMessage;
import com.wipro.bookAppBackend.Model.User;
import com.wipro.bookAppBackend.Service.AuthService;
import com.wipro.bookAppBackend.utils.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public ResponseEntity<ErrorMessage> f1(@RequestBody User user) throws UserAlreadyExist{
        this.authService.register(user);
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.CREATED,"created"), HttpStatus.CREATED);
    }

    @PostMapping("/log_in")
    public ResponseEntity<ErrorMessage> f2(@RequestBody LoginData loginData) throws InvalidUserNameOrPassword{
        String token= this.authService.logIn(loginData);
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.OK,token),HttpStatus.OK);
    }

    @GetMapping("/t")
    public String test(){
        return "success";
    }
}
