package com.inyangedemo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inyangedemo.Model.User;

public interface UserRepository extends JpaRepository<User,Integer>{

    List<User> findAllByRole(String role);

    User findByEmail(String username);
    
}
