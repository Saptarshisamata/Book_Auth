package com.wipro.bookAppBackend.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogInResponse {
    //only token
    private HttpStatus status;
    private String message;
    private String token;
}
