package com.inyangedemo.Controllers;

import java.util.Collection;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.inyangedemo.Model.User;
import com.inyangedemo.Repo.UserRepository;
import com.inyangedemo.services.UserServices;
@Controller

public class IndexController {
   @Autowired private UserServices userServices;
@GetMapping(value="/")
 public String getPage(Model model)
 {User user=new User();
   user.setEmail("admin@gmail.com");
   user.setName("admin");
   user.setPassword(BCrypt.hashpw("admin", BCrypt.gensalt()));
   user.setId(1);
   user.setRole("ROLE_ADMIN");
   userServices.saveOrUpdateUser(user);
    return "index";
 }
 @RequestMapping(value="/login")
 public String login(Model model)
 {
   return "login";
 }
 @Autowired private UserRepository userRepository;
 @GetMapping(value="/defaultsuccesslogin")
    public String getDefaultLoginPage() {
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       String username=auth.getName();
        User authuser=userRepository.findByEmail(username);
    if(auth!=null && auth.isAuthenticated())
    {
      Collection<? extends GrantedAuthority> authority=auth.getAuthorities();
      if(authority.contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
      { AdminController.user=authuser;
        return "redirect:/admin";
      }else if(authority.contains(new SimpleGrantedAuthority("ROLE_SCHOOL"))){
         SchoolController.users=authuser;
        return "redirect:/school";
      }
      else if(authority.contains(new SimpleGrantedAuthority("ROLE_MINISTRY"))){
         MiniController.user=authuser;
        return "redirect:/minister";
      }
      else if(authority.contains(new SimpleGrantedAuthority("ROLE_DISTRIBUTOR"))){
         DistributorController.user=authuser;
        return "redirect:/distributor";
      }
    }
        return "/login";
    }
}
