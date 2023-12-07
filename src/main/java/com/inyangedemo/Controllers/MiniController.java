package com.inyangedemo.Controllers;

import java.util.Random;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.inyangedemo.Configuration.EmailSenderServiceConfig;
import com.inyangedemo.Model.Location;
import com.inyangedemo.Model.Ordering;
import com.inyangedemo.Model.School;
import com.inyangedemo.Model.User;
import com.inyangedemo.services.LocationServices;
import com.inyangedemo.services.MilkServices;
import com.inyangedemo.services.OrderServices;
import com.inyangedemo.services.SchoolServices;
import com.inyangedemo.services.UserServices;

import jakarta.mail.MessagingException;

@Controller
@SessionAttributes("minister")
@RequestMapping(value = "/minister")
public class MiniController {
     @Autowired private UserServices userServices;
    @Autowired private MilkServices milkServices;
    @Autowired private OrderServices orderServices;
    @Autowired private SchoolServices schoolServices;
    @Autowired private LocationServices locationServices;
    public static String message="";
    public static User user;
    @GetMapping()
    public String getPage(Model model)
    {   model.addAttribute("minister", user);
        model.addAttribute("orderList",orderServices.getAllOrders());
        model.addAttribute("appendingOrder", orderServices.getAllOrderingProfile(false));
        model.addAttribute("failureOrder", orderServices.getAllOrderingProfile(true));
         model.addAttribute("schoolList",schoolServices.getAllSchools());
        return "minister/index";
    }
     @GetMapping(value = "/order")
    public String getOrder(Model model)
    {   model.addAttribute("orderList",orderServices.getAllOrderingProfile(false));
        model.addAttribute("schoolList",schoolServices.getAllSchools());
        model.addAttribute("successOrderList",orderServices.getAllOrderingProfile(true));
        return "minister/order";
    }
     @PostMapping(value = "/order/add")
    public String getAddOrder(@ModelAttribute Ordering  order)
    {    Ordering saveOrder=orderServices.saveOrUpdateOrder(order);
        message=saveOrder.getSchool().getName()+" Order added successfully";
        return "redirect:/minister/school";
    }
    @GetMapping(value = "/school")
    public String getSchools(Model model)
    {   model.addAttribute("milkList",milkServices.getAllMilk());
        model.addAttribute("schoolList",schoolServices.getAllSchools());
        model.addAttribute("province", locationServices.findListOfLocationByType("PROVINCE"));
        model.addAttribute("orderList",orderServices.getAllOrders());
        return "minister/school";
    }
    @GetMapping(value = "/setting")
    public String getSetting(){
        return "minister/setting";
    }
    @PostMapping(value="/setting")
    public String createSetting(@ModelAttribute User user)
    { User usr=userServices.findOrderById(user.getId());
      usr.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
      User usr1=userServices.saveOrUpdateUser(usr);
      message="Dear "+usr1.getName()+" Your password has been changed";
        return "redirect:/minister";
    }
    @Autowired private EmailSenderServiceConfig sendEmail;
    @PostMapping(value = "/school")
    public String createSchool(@RequestParam String name, @RequestParam String email
    ,@RequestParam String phoneNumber,@RequestParam String schoolname,@RequestParam int location,
    @RequestParam int studentNumber) throws MessagingException
    {try{Random random = new Random();
        int a=random.nextInt(1000000);
        int b=random.nextInt(1000000);
        String randPassword=a+"-"+b;
         User user=new User(name, phoneNumber, email,BCrypt.hashpw(randPassword, BCrypt.gensalt()),"ROLE_SCHOOL");
        User saveUser=userServices.saveOrUpdateUser(user);
         Location locat =locationServices.findLocationById(location);
         School school = new School(b, schoolname, studentNumber, locat, saveUser, null);
        School scl=schoolServices.saveOrUpdateSchool(school);
        message=scl.getUser().getName()+"saved sucessfully";
        sendEmail.sendSignUpEmail(user.getEmail(), "Create Account",user.getName(), randPassword);
     userServices.saveOrUpdateUser(user);}
     catch(MessagingException e){
        message="Check your connection";
     }
        return "redirect:/minister/school";
    }
    @PostMapping(value = "/school/del")
    public String deleteSchool(@ModelAttribute School school)
    {  School shr=schoolServices.findSchoolById(school.getId());
        if(shr==null)
        message="User not found";
        // else {userServices.deleteUser(shr.getId());message=shr.getName()+" deleted sucessfull";}
        
        return "redirect:/admin/school";
    }
}
