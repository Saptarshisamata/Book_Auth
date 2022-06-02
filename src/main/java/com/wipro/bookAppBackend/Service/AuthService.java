package com.wipro.bookAppBackend.Service;

import com.wipro.bookAppBackend.Exception.InvalidUserNameOrPassword;
import com.wipro.bookAppBackend.Exception.UserAlreadyExist;
import com.wipro.bookAppBackend.Exception.UserNotFound;
import com.wipro.bookAppBackend.Model.LoginData;
import com.wipro.bookAppBackend.Model.User;

public interface AuthService {

    public String register(User user) throws UserAlreadyExist;

    public String logIn(LoginData loginData) throws  InvalidUserNameOrPassword;
}
