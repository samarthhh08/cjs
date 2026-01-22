package com.cjs.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjs.auth_service.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User getUserByUsername(String username);

    public User getUserByEmail(String email);

}
