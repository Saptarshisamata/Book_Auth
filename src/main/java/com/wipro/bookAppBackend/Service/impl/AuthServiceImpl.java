package com.wipro.bookAppBackend.Service.impl;

import com.sun.istack.NotNull;
import com.wipro.bookAppBackend.Exception.InvalidUserNameOrPassword;
import com.wipro.bookAppBackend.Exception.UserAlreadyExist;
import com.wipro.bookAppBackend.Model.*;
import com.wipro.bookAppBackend.Repository.AuthRepository;
import com.wipro.bookAppBackend.Repository.RoleRepository;
import com.wipro.bookAppBackend.Service.AuthService;
import com.wipro.bookAppBackend.utils.BookUserDetails;
import com.wipro.bookAppBackend.utils.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {


    private AuthRepository authRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JWTUtility jwtUtility;
    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;
    public AuthServiceImpl(AuthRepository authRepository,@Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authRepository = authRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public RegisterResponse register(User user) throws UserAlreadyExist {
        Optional<User> chk_user = authRepository.findByEmail(user.getEmail());
        if(chk_user.isPresent()){
            throw new UserAlreadyExist("user_already_exist");
        }
        Role role = roleRepository.findById(Integer.toUnsignedLong(3)).orElseThrow();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setPswd(bCryptPasswordEncoder.encode(user.getPswd()));
        authRepository.save(user);
        return new RegisterResponse(HttpStatus.CREATED,"user_created");
    }

    @Override
    public LogInResponse logIn(LoginData loginData) throws  InvalidUserNameOrPassword {
        Optional<User> chk_user = authRepository.findByEmail(loginData.getEmail());
        if(chk_user.isEmpty()){
            throw new InvalidUserNameOrPassword("invalid_username_or_password");
        }
        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginData.getEmail(),loginData.getPswd()));
        }catch (AuthenticationException e){
//            System.out.println("test");
            throw  new InvalidUserNameOrPassword("invalid_username_or_password");
        }
//        if(!(chk_user.get().getPswd().equals(loginData.getPswd()))){
//            throw new InvalidUserNameOrPassword("invalid_username_or_password");
//        }
        final UserDetails userDetails = loadUserByUsername(loginData.getEmail());
        final String token = jwtUtility.generateToken(userDetails);
        return new LogInResponse(HttpStatus.OK,"success",token);
    }

    public UserDetailsResponse getUserDetailsByJWT(String token){
        token = token.substring(7);
        String email = jwtUtility.getUserNameFromToken(token);
        User user = authRepository.findByEmail(email).get();
        String userName = user.getName();
        String phoneNo = user.getPhone();
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse(HttpStatus.OK,userName,email,phoneNo);
        return userDetailsResponse;
    }

    @Override
    public UpdatePasswordResponse updatePassword(UpdatePasswordRequest updatePasswordRequest,String token) throws InvalidUserNameOrPassword {
        token = token.substring(7);
        String email = jwtUtility.getUserNameFromToken(token);
        User user = authRepository.findByEmail(email).get();
        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,updatePasswordRequest.getOldPassword()));
        }catch (AuthenticationException e){
//            System.out.println("test");
            throw  new InvalidUserNameOrPassword("invalid_username_or_password");
        }
        user.setPswd(bCryptPasswordEncoder.encode(updatePasswordRequest.getNewPassword()));
        authRepository.save(user);
 //       authRepository.updatePassword(updatePasswordRequest.getNewPassword(),email);
        return new UpdatePasswordResponse(HttpStatus.OK,"updated");
    }

    public AuthenticatedResponse authenticate(String token){
        token = token.substring(7);
        String email = jwtUtility.getUserNameFromToken(token);
        return new AuthenticatedResponse(HttpStatus.OK,"authenticated",email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = authRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("username not found");
        }
        return new BookUserDetails(user.get());
    }
}
