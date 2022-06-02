package com.wipro.bookAppBackend.Repository;

import com.wipro.bookAppBackend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthRepository extends JpaRepository<User,Long> {

    public Optional<User> findByEmail(String email);
}
