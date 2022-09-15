
package fpt.aptech.EatnEat.controller;

import fpt.aptech.EatnEat.entities.*;
import fpt.aptech.EatnEat.entities.models.ReportItem;
import fpt.aptech.EatnEat.service.ReportService;
import fpt.aptech.EatnEat.service.*;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lenovo
 */
@Controller
public class DashboardController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService detailService;
    @Autowired
    ViewService viewService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    IngredientService ingredientService;
    @Autowired
    FoodService foodService;
    @Autowired
    MenutypeService menutypeService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    SalesService salesService;
    @Autowired
    ReportService reportService;
    @RequestMapping("/admin")
    public String dashboard(Model model, HttpSession session,ModelMap mm) {
//        if (session.getAttribute("ADMIN") != null) {
            model.addAttribute("TotalOrder", orderService.OrdersOfDay(new Date()).size());
            model.addAttribute("TotalEmp", employeeService.findAll().size());            
            model.addAttribute("TotalFoodOfDay", detailService.FoodsOfDay(new Date()).size());
           
         
             model.addAttribute("TotalFood", foodService.findAllFood().size());
             int amount=detailService.TotalAmountforDetail(new Date());
             DecimalFormat formatter = new DecimalFormat("###,###,###");
            model.addAttribute("TotalAmount", formatter.format(amount));           
            
              Date date = new Date();
        List<ReportItem> listItem = reportService.reportReceipt(date, 15);
            Map<String, Integer> graphData = new TreeMap<>();
            for (ReportItem item : listItem) {
            graphData.put(item.getTime(), item.getValue());
          }
        model.addAttribute("chartData", graphData);
        
        List<ReportItem> listItem2 = reportService.reportReceiptMonth(date, 12);
            Map<String, Integer> graphData2 = new TreeMap<>();
            for (ReportItem item : listItem2) {
            graphData2.put(item.getTime(), item.getValue());
          }
        model.addAttribute("chartData2", graphData2);
     
         
            
            
            return "admin/index";
//        } else {
//            return "redirect:/login";
//        }
    }
    
     @GetMapping("admin/account")
    public String account(Model model,HttpSession session) { 
        Employee employee=(Employee)session.getAttribute("ADMIN");
        if(employee!=null){
            model.addAttribute("acc", employee);
            return "admin/account";
        }
            return "error";
        
        
    }

}
