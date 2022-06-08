package com.wipro.bookAppBackend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsResponse {
    private HttpStatus status;
    private String userName;
    private String email;
    private String phoneNo;
}
