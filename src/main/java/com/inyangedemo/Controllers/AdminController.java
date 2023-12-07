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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.inyangedemo.Configuration.EmailSenderServiceConfig;
import com.inyangedemo.Model.Location;
import com.inyangedemo.Model.Milk;
import com.inyangedemo.Model.Ordering;
import com.inyangedemo.Model.School;
import com.inyangedemo.Model.User;
import com.inyangedemo.services.DistributionServices;
import com.inyangedemo.services.LocationServices;
import com.inyangedemo.services.MilkServices;
import com.inyangedemo.services.OrderServices;
import com.inyangedemo.services.SchoolServices;
import com.inyangedemo.services.UserServices;

import jakarta.mail.MessagingException;

@Controller
@SessionAttributes("admin")
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired private UserServices userServices;
    @Autowired private DistributionServices distributionServices;
    @Autowired private MilkServices milkServices;
    @Autowired private OrderServices orderServices;
    @Autowired private SchoolServices schoolServices;
    @Autowired private LocationServices locationServices;
    public static User user;
    public static String message="";
    @GetMapping()
    public String getAdminHomePage(Model model)
    {
        model.addAttribute("admin",user);
        model.addAttribute("province", locationServices.findListOfLocationByType("PROVINCE"));
        model.addAttribute("districtList", locationServices.findListOfLocationByType("DISTRICT"));
        model.addAttribute("sectorList", locationServices.findListOfLocationByType("SECTOR"));
        model.addAttribute("locationList", locationServices.getAllLocation());
        model.addAttribute("distributorList",userServices.findAllByRole("ROLE_DISTRIBUTOR"));
        model.addAttribute("successOrderList",orderServices.getAllOrderingProfile(true));
        model.addAttribute("failureOrderList",orderServices.getAllOrderingProfile(false));
        model.addAttribute("schoolList",schoolServices.getAllSchools());
        model.addAttribute("message", message);
        message="";
        return "admin/index";
    }
   
    @PostMapping()
    public String getPage(@ModelAttribute Location locaction)
    {   Location lct=locationServices.saveOrUpdateLocation(locaction);
        message=lct.getName()+"Saved successfully";
        return "redirect:/admin";
    }
     @GetMapping(value="/distributor")
    public String getAdminDistributorPage(Model model)
    {
        model.addAttribute("distributorList",distributionServices.getAllDistributor());
        model.addAttribute("distributorRoleList",userServices.findAllByRole("ROLE_DISTRIBUTOR"));
        model.addAttribute("successOrderList",orderServices.getAllOrderingProfile(true));
        model.addAttribute("failureOrderList",orderServices.getAllOrderingProfile(false));
        model.addAttribute("schoolList",schoolServices.getAllSchools());
        model.addAttribute("message", message);
        message="";
        return "admin/distributor";
    }
    @GetMapping(value = "/setting")
    public String getSetting(){
        return "admin/setting";
    }
    // milk
     @GetMapping(value="/milk")
    public String getAdminMilkPage(Model model)
    {
        model.addAttribute("milkList",milkServices.getAllMilk());
        model.addAttribute("message", message);
        message="";
        return "admin/milk";
    }
     @PostMapping(value = "/milk")
    public String createUser(@ModelAttribute Milk milk)
    {
       Milk milk2 =milkServices.saveOrUpdateMilk(milk);
       message=milk2.getPackageType()+" Saved Successfully";
        return "redirect:/admin/milk";
    }
    @PostMapping(value = "/milk/del")
    public String deleteMilk(@ModelAttribute Milk milk)
    {  Milk mlk=milkServices.findMilkById(milk.getId());
        if(mlk==null)
        message="Milk not found";
        else {milkServices.deleteMilk(milk);message=mlk.getPackageType()+" deleted sucessfull";}
        
        return "redirect:/admin/milk";
    }
    @GetMapping(value="/order")
    public String getAdminOrderPage(Model model)
    {
        model.addAttribute("orderList",orderServices.getAllOrderingProfile(false));
        model.addAttribute("distributorList",userServices.findAllByRole("ROLE_DISTRIBUTOR"));
        model.addAttribute("successOrderList",orderServices.getAllOrderingProfile(true));
        model.addAttribute("failureOrderList",orderServices.getAllOrderingProfile(false));
        model.addAttribute("distributorRoleList",userServices.findAllByRole("ROLE_DISTRIBUTOR"));
        model.addAttribute("schoolList",schoolServices.getAllSchools());
        model.addAttribute("message", message);
        message="";
        return "admin/order";
    }
    @PostMapping(value="/order")
    public String createAdminOrderPage(@ModelAttribute Ordering order)
    {
       Ordering odr=orderServices.findOrderById(order.getId());
       odr.setDistributor(order.getDistributor());
       odr.setApprovedByAdmin(true);
       Ordering odr2=orderServices.saveOrUpdateOrder(odr);
        message=odr2.getDistributor().getName();
        return "redirect:/admin/order";
    }
    @GetMapping(value="/school")
    public String getAdminSchoolPage(Model model)
    {
        model.addAttribute("shoolList",schoolServices.getAllSchools());
        model.addAttribute("message", message);
        message="";
        return "admin/school";
    }
     @PostMapping(value = "/school")
    public String createSchool(@ModelAttribute School school)
    {  School schoolDb =schoolServices.saveOrUpdateSchool(school);
       message=schoolDb.getName()+" Saved Successfully";
        return "redirect:/admin/school";
    }
    @PostMapping(value = "/school/del")
    public String deleteSchool(@ModelAttribute School school)
    {  School shr=schoolServices.findSchoolById(school.getId());
        if(shr==null)
        message="User not found";
        else {userServices.deleteUser(user);message=shr.getName()+" deleted sucessfull";}
        
        return "redirect:/admin/school";
    }
    // user
    @GetMapping(value="/user")
    public String getAdminUserPage(Model model)
    {   
        model.addAttribute("userList",userServices.getAllUsers());
        model.addAttribute("message", message);
        message="";
        return "admin/user";
    }
    @Autowired EmailSenderServiceConfig sendEmail;
    @PostMapping(value = "/user")
    public String createUser(@ModelAttribute User user)  
    {  try{Random random = new Random();
        int a=random.nextInt(1000000);
        int b=random.nextInt(1000000);
        String randPassword=a+"-"+b;
        System.out.println("Password: " + randPassword);
       user.setPassword(BCrypt.hashpw(randPassword, BCrypt.gensalt()));
       User userDb =userServices.saveOrUpdateUser(user);
       sendEmail.sendSignUpEmail(userDb.getEmail(), "Create Account",userDb.getName(), randPassword);
      System.out.println(randPassword);
       message=userDb.getName()+" Saved Successfully";
    }catch(MessagingException e){
        message="Check your connection";
     }
       
        return "redirect:/admin/user";
    }
    @PostMapping(value = "/user/del")
    public String deleteUser(@ModelAttribute User user)
    {  User usr=userServices.findOrderById(user.getId());
        if(usr==null)
        message="User not found";
        else {userServices.deleteUser(user);message=usr.getName()+" deleted sucessfull";}
        
        return "redirect:/admin/user";
    }
}
