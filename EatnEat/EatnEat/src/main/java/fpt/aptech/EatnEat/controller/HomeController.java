/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.controller;

import fpt.aptech.EatnEat.entities.Employee;
import fpt.aptech.EatnEat.entities.Food;
import fpt.aptech.EatnEat.entities.models.SmsRequest;
import fpt.aptech.EatnEat.service.EmployeeService;
import fpt.aptech.EatnEat.service.FavoriteService;
import fpt.aptech.EatnEat.service.FoodService;
import fpt.aptech.EatnEat.service.MenutypeService;
import fpt.aptech.EatnEat.service.SMSService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author lenovo
 */
@Controller
public class HomeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    MenutypeService menutypeService;
    @Autowired
    FoodService foodService;
    @Autowired
    SMSService smsService;
     @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

   @PostMapping("/sendOTP")
    public String sendOTP(Model model, @RequestParam("phone") String phone, HttpSession session, RedirectAttributes redirectAttributes) {
        SmsRequest smsRequest = new SmsRequest();  
        if(phone.charAt(0)=='0'){
           smsRequest.setPhoneNumber("+84" + phone.substring(1));
        }else{
            smsRequest.setPhoneNumber("+84" + phone);
        }         
        Employee employee = employeeService.checkPhone(smsRequest.getPhoneNumber());
        if (employee != null) {
            String otp = smsService.sendSMS(smsRequest);
            session.setAttribute("phone", employee);
            session.setAttribute("OTP", otp);
            // session.setMaxInactiveInterval(60);

            return "OTPCode";
        }
        redirectAttributes.addFlashAttribute("message", "Phone Number is incorrect!");
        return "redirect:/login";
    }
    @PostMapping("/doLogin")
    public String doLogin(Model model, @RequestParam("OTP") String otp, HttpSession session, RedirectAttributes redirectAttributes) {
        Employee emp = (Employee) session.getAttribute("phone");
        String otpsession = (String) session.getAttribute("OTP");
        if (otp.equals(otpsession)) {
            session.setAttribute("EMPLOY", emp);
            session.removeAttribute("phone");
            session.removeAttribute("OTP");
            if (emp.getRole().equals("admin")) {
                session.setAttribute("ADMIN", emp);
                return "redirect:/admin";
            } else {
                return "redirect:/menuSet";
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "OTP Code is incorrect !");
            return "OTPCode";
        }

    }
//    @PostMapping("/doLogin")
//    public String doLogin(Model model, @RequestParam("phone") String phone, HttpSession session) {
//        Employee employee=employeeService.checkPhone(phone);
//        if (employee!=null) {
//            session.setAttribute("EMPLOY", employee);            
//            if (employee.getRole().equals("admin")) {
//                session.setAttribute("ADMIN", employee);
//                return "redirect:/admin";
//            } else {
//                return "redirect:/menuSet";
//            }
//        }
//        return "login";
//    }

    @RequestMapping("/")
    public String home(Model model) {
        List<Food> list = foodService.categoryFood("set");
               
        model.addAttribute("LIST", foodService.RandomFood(list).values());
        model.addAttribute("type", menutypeService.findAllMenu());
        return "index";
    }

    @RequestMapping("/menuSet")
    public String menuSet(Model model,HttpSession session) {
        List<Food> list = foodService.categoryFood("set");
        model.addAttribute("LIST", foodService.RandomFood(list).values());
        model.addAttribute("type", menutypeService.findAllMenu());
         model.addAttribute("cat", "Set");
        Employee emp = (Employee) session.getAttribute("EMPLOY");
       // Favorite favorite=favoriteService.findOne(emp.getEmpid(), foodid);
        
        return "food_menu";
    }

    @RequestMapping("/menuOption")
    public String menuOption(Model model) {
        List<Food> list = foodService.categoryFood("option");
        model.addAttribute("LIST", foodService.RandomFood(list).values());
        model.addAttribute("type", menutypeService.findAllMenu());
        model.addAttribute("cat", "Option");
        return "food_menu";
    }

    @RequestMapping("/menu{category}/{typeid}")
    public String searchType(Model model, @PathVariable("typeid") Integer menuid, @PathVariable("category") String cat) {
        List<Food> list = foodService.categoryFood(cat);
        List<Food> setList = new ArrayList<>();
        for (Food food : list) {
            if (food.getMenutypeid().getMenutypeid().equals(menuid)) {
                setList.add(food);
            }
        }
        model.addAttribute("LIST", setList);
        model.addAttribute("type", menutypeService.findAllMenu());
        model.addAttribute("cat", cat);
        return "food_menu";
    }

    @RequestMapping("/contact")
    public String contact(Model model) {
        return "contactus";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        return "aboutus";
    }

    @RequestMapping("/logout")
    public String logout(Model model, HttpSession session) {
        session.removeAttribute("EMPLOY");
        session.removeAttribute("ADMIN");
        return "redirect:/";
    }
//    @RequestMapping("/changePasword")
//    public String changePass(Model model,HttpSession session) {         
//        return "changepass";
//    }
//    @PostMapping("/doChangePass")
//    public String DoChangePass(Model model,HttpSession session,@RequestParam("oldpass")String oldpass,@RequestParam("newpass")String newpass, RedirectAttributes redirectAttributes) {         
//        Employee employee=(Employee) session.getAttribute("EMPLOY");
//        if (employeeService.checkPass(oldpass, employee.getPassword())==true) {
//            if (!oldpass.equals(newpass)) {
//                employeeService.changePassword(employee, oldpass, newpass);
//            }else{
//               redirectAttributes.addFlashAttribute("message", "The new password must not be the same as the old password !"); 
//               return "redirect:/changePasword";
//            }
//        }else{
//            redirectAttributes.addFlashAttribute("message", "Old password is incorrect !"); 
//            return "redirect:/changePasword";
//        }              
//        redirectAttributes.addFlashAttribute("message", "Change password successfully .Please login again!"); 
//        return "redirect:/login";
//    }
//    @RequestMapping("/forgotPassword")
//    public String forgotPass(Model model,HttpSession session) {         
//        return "forgotPass";
//    }
//    @PostMapping("/doForgotPass")
//    public String DoForgotPass(Model model,HttpSession session,@RequestParam("email")String email, RedirectAttributes redirectAttributes) {         
//        
//        
//            //employeeService.forgotPassword(email);
//              return "redirect:/login";
//           
//          
//    }

}
