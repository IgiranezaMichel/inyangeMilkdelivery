package com.inyangedemo.Controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.inyangedemo.Model.User;
import com.inyangedemo.services.OrderServices;
import com.inyangedemo.services.UserServices;

@Controller
@RequestMapping(value = "/distributor")
@SessionAttributes("user")
public class DistributorController {
    @Autowired private OrderServices orderServices;
    @Autowired private UserServices userServices;
    public String message="";
    public static User user;
    @GetMapping()
    public String getHomePage(Model model)
    {    
        model.addAttribute("user",userServices.findOrderById(user.getId())); 
        model.addAttribute("orderList", orderServices.getAllOrders());
        return "distributor/distributor";
    }
     @GetMapping(value = "/setting")
    public String getSetting(){
        return "distributor/setting";
    }
    @PostMapping(value="/setting")
    public String createSetting(@ModelAttribute User user)
    { User usr=userServices.findOrderById(user.getId());
      usr.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
      User usr1=userServices.saveOrUpdateUser(usr);
      message="Dear "+usr1.getName()+" Your password has been changed";
        return "redirect:/distributor";
    }
}
