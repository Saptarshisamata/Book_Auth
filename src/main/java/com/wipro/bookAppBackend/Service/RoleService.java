package com.wipro.bookAppBackend.Service;

import com.wipro.bookAppBackend.Model.Role;

public interface RoleService {
    String SaveRole(Role role);

    String deleteRoll(Long id);
}
