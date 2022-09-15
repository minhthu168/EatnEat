package fpt.aptech.EatnEat.controller;

import fpt.aptech.EatnEat.entities.Orders;
import fpt.aptech.EatnEat.entities.models.SalesEmp;
import fpt.aptech.EatnEat.service.EmployeeService;
import fpt.aptech.EatnEat.service.OrderService;
import fpt.aptech.EatnEat.service.SalesService;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SalesController {

    @Autowired
    SalesService salesService;
    @Autowired
    EmployeeService empService;
    @Autowired
    OrderService orderService;
    @Autowired
    HttpSession session;

    @RequestMapping("/admin/Sales")
    public String Sales(Model model) {
        if (session.getAttribute("ADMIN") != null) {
            HashMap<String, SalesEmp> list = salesService.SalesEmp_findAll();
            model.addAttribute("list", list.values());
            model.addAttribute("title", "Sales of Employee in this mounth");
            return "admin/Sales";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("Sales/{empid}")
    public String Sales_detail(Model model, @PathVariable("empid") String Empid) {
        if (session.getAttribute("ADMIN") != null) {
            List<Orders> list = orderService.orders_emp_mounth(empService.findOne(Empid));
            model.addAttribute("LIST", list);
            model.addAttribute("TotalOrder", list.size());
            int amount = orderService.TotalAmount(list);
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            model.addAttribute("TotalAmount", formatter.format(amount));
            model.addAttribute("title", empService.findOne(Empid).getEmpname());
            return "admin/SalesEmp";
        } else {
            return "redirect:/login";
        }
    }

}
