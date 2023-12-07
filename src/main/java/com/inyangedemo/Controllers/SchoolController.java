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

import com.inyangedemo.Model.Ordering;
import com.inyangedemo.Model.User;
import com.inyangedemo.services.OrderServices;
import com.inyangedemo.services.UserServices;

@Controller
@RequestMapping(value = "/school")
@SessionAttributes("user")
public class SchoolController {
    @Autowired private OrderServices orderServices;
    @Autowired private UserServices userServices;
    public static User users;
    private static String message="";
      @GetMapping()
    public String getPage(Model model)
    {    
         model.addAttribute("user", userServices.findOrderById(users.getId())); 
         model.addAttribute("message", message); 
         model.addAttribute("orderList",orderServices.getAllOrders());
         message="";
        return "school/schoolPage";
    }
    @PostMapping()
    public String createOrder(@ModelAttribute Ordering order)
    {   Ordering or=orderServices.findOrderById(order.getId());
      or.setApprovedBySchool(true);
      or.setDone(true);
      or.setMessage(order.getMessage());
      Ordering ord2=orderServices.saveOrUpdateOrder(or);
      message=ord2.getSchool().getName()+" Has been approve order";
        return "redirect:/school";
    } 
     @GetMapping(value = "/setting")
    public String getSetting(){
        return "school/setting";
    }
    @PostMapping(value="/setting")
    public String createSetting(@ModelAttribute User user)
    { User usr=userServices.findOrderById(user.getId());
      usr.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
      User usr1=userServices.saveOrUpdateUser(usr);
      message="Dear "+usr1.getName()+" Your password has been changed";
        return "redirect:/school";
    }
}
