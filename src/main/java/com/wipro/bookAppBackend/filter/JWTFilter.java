package com.wipro.bookAppBackend.filter;

import com.wipro.bookAppBackend.Service.AuthService;
import com.wipro.bookAppBackend.Service.impl.AuthServiceImpl;
import com.wipro.bookAppBackend.utils.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthServiceImpl authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String  authorization = request.getHeader("Authorization");
        String token = null;
        String  email = null;

        if(null != authorization && authorization.startsWith("Bearer ")){
            token = authorization.substring(7);
            email = jwtUtility.getUserNameFromToken(token);
//            System.out.println("tt2");

        }
//        System.out.println(email);
//        System.out.println(token);

//        System.out.println(SecurityContextHolder.getContext().getAuthentication());


        if(null != email && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = authService.loadUserByUsername(email);
//            System.out.println("tt3");

            if(jwtUtility.validateToken(token,userDetails)){
//                System.out.println("tt1");

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//                System.out.println("tt");
            }
        }

        filterChain.doFilter(request,response);
    }
}
