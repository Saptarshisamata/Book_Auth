package com.wipro.bookAppBackend.Service;

import com.wipro.bookAppBackend.Exception.InvalidUserNameOrPassword;
import com.wipro.bookAppBackend.Exception.UserAlreadyExist;
import com.wipro.bookAppBackend.Model.*;

public interface AuthService {

    public RegisterResponse register(User user) throws UserAlreadyExist;

    public LogInResponse logIn(LoginData loginData) throws  InvalidUserNameOrPassword;

    UserDetailsResponse getUserDetailsByJWT(String token);

    UpdatePasswordResponse updatePassword(UpdatePasswordRequest updatePasswordRequest,String token) throws InvalidUserNameOrPassword;

    public AuthenticatedResponse authenticate(String token);

}
