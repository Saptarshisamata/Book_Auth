package com.wipro.bookAppBackend.Service;

import com.wipro.bookAppBackend.Exception.InvalidUserNameOrPassword;
import com.wipro.bookAppBackend.Exception.UserAlreadyExist;
import com.wipro.bookAppBackend.Model.*;

public interface AuthService {

    RegisterResponse register(User user) throws UserAlreadyExist;

    LogInResponse logIn(LoginData loginData) throws  InvalidUserNameOrPassword;

    UserDetailsResponse getUserDetailsByJWT(String token);

    UpdatePasswordResponse updatePassword(UpdatePasswordRequest updatePasswordRequest,String token) throws InvalidUserNameOrPassword;

    AuthenticatedResponse authenticate(String token);

}
