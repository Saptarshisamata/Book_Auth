package com.wipro.bookAppBackend.Service.impl;

import com.wipro.bookAppBackend.Model.Role;
import com.wipro.bookAppBackend.Repository.RoleRepository;
import com.wipro.bookAppBackend.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public String SaveRole(Role role) {
        roleRepository.save(role);
        return "success";
    }

    @Override
    public String deleteRoll(Long id) {
        return null;
    }
}
