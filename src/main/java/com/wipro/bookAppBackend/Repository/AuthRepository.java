package com.wipro.bookAppBackend.Repository;

import com.wipro.bookAppBackend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User,Long> {

    public Optional<User> findByEmail(String email);

//    @Modifying
//    @Query("update User user set user.password =?1 where user.email=?2")
//    Boolean updatePassword(String newPassword,String email);
}
