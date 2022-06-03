package com.wipro.bookAppBackend.Service.impl;

import com.wipro.bookAppBackend.Exception.InvalidUserNameOrPassword;
import com.wipro.bookAppBackend.Exception.UserAlreadyExist;
import com.wipro.bookAppBackend.Model.LoginData;
import com.wipro.bookAppBackend.Model.User;
import com.wipro.bookAppBackend.Repository.AuthRepository;
import com.wipro.bookAppBackend.Service.AuthService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {


    private AuthRepository authRepository;

    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }


    @Override
    public String register(User user) throws UserAlreadyExist {
        Optional<User> chk_user = authRepository.findByEmail(user.getEmail());
        if(chk_user.isPresent()){
            throw new UserAlreadyExist("user_already_exist");
        }
        authRepository.save(user);
        return "success";
    }

    @Override
    public String logIn(LoginData loginData) throws  InvalidUserNameOrPassword {
        Optional<User> chk_user = authRepository.findByEmail(loginData.getEmail());
        if(chk_user.isEmpty()){
            throw new InvalidUserNameOrPassword("invalid_username_or_password");
        }
        if(!(chk_user.get().getPswd().equals(loginData.getPswd()))){
            throw new InvalidUserNameOrPassword("invalid_username_or_password");
        }
        return "token";
    }
}
