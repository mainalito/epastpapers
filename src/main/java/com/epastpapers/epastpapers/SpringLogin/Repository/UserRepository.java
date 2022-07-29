package com.epastpapers.epastpapers.SpringLogin.Repository;

import java.util.Optional;

import com.epastpapers.epastpapers.SpringLogin.models.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long>{

    Optional<Users> findByUserName(String username);
    boolean existsByUserName(String username);
    
}
