package com.inyangedemo.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inyangedemo.Model.User;
import com.inyangedemo.Repo.UserRepository;
@Service
public class UserDetailService implements UserDetailsService{
@Autowired private UserRepository userrepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
        User  User =userrepo.findByEmail(username);
        if(User == null)throw new UsernameNotFoundException("Unimplemented method  loadUserByUsername");
    return new UserDetailPrinciple(User);
    }
}
