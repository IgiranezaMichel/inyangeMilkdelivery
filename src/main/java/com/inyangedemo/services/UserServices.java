package com.inyangedemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inyangedemo.Model.User;
import com.inyangedemo.Repo.UserRepository;

@Service
public class UserServices {
    @Autowired private UserRepository userRepository;
    public User saveOrUpdateUser(User User){return userRepository.save(User); } 
    public User findOrderById(int id){return userRepository.findById(id).orElse(null); }
    public void deleteUser(User User){userRepository.delete(User);}
    public List<User>getAllUsers(){return userRepository.findAll();}
    public List<User>findAllByRole(String role){return userRepository.findAllByRole(role);}
}
