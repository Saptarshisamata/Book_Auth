package com.wipro.bookAppBackend.Controller;

import com.wipro.bookAppBackend.Model.Role;
import com.wipro.bookAppBackend.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    RoleService roleService;

    @PostMapping("/create_new_role")
    public String createRole(@RequestBody Role role){
        return roleService.SaveRole(role);
    }

}
