package com.alura.ForoHubAPI.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alura.ForoHubAPI.domain.model.Profile;
import com.alura.ForoHubAPI.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

    @Query("SELECT u.profiles FROM User u WHERE u.userId = :userId")
    List<Profile> findProfilesByUserId(@Param("userId") Long userId);

    User findByName(String name);

    User findByEmail(String username);
    
}
