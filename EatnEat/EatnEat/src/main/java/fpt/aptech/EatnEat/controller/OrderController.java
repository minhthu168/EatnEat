/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.controller;

import fpt.aptech.EatnEat.entities.*;
import fpt.aptech.EatnEat.service.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    @Autowired
    ViewService viewService;
    @Autowired
    OrderDetailService detailService;
    @Autowired
    FoodService foodService;
    @Autowired
    IngredientService ingredientService;
    @Autowired
    OrderService orderService;

    @Autowired
    ViewService service;

    @Autowired
    EmployeeService empService;
    @Autowired
    HttpSession session;

    @GetMapping("/admin/orderList")
    public String listOrder(Model model) {
        if (session.getAttribute("ADMIN") != null) {
            List<Orders> list = orderService.findAll();
            model.addAttribute("LIST", list);
            model.addAttribute("TotalOrder", list.size());
            int amount = orderService.TotalAmount(list);
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            model.addAttribute("TotalAmount", formatter.format(amount));
            model.addAttribute("title", "List of Orders");
            return "admin/orders/OrderList";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/admin/orderListToDay")
    public String listToDay(Model model) {
        if (session.getAttribute("ADMIN") != null) {

            List<Orders> list = orderService.OrdersOfDay(new Date());
            model.addAttribute("LIST", list);
            model.addAttribute("TotalOrder", list.size());
            int amount = orderService.TotalAmount(list);
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            model.addAttribute("TotalAmount", formatter.format(amount));
            model.addAttribute("title", "Orders of ToDay");
            return "admin/orders/indexOrder";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/admin/seachOrder")
    public String searchDate(Model model, @RequestParam("time") String time) throws ParseException {
        if (session.getAttribute("ADMIN") != null) {
            List<Orders> list;
            SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
            if (!time.isEmpty()) {
                Date searchday = new SimpleDateFormat("yyyy-MM-dd").parse(time);
                list = orderService.OrdersOfDay(searchday);
                String strDate = formatter1.format(searchday);
                model.addAttribute("title", "Orders of " + strDate);
            } else {
                list = orderService.OrdersOfDay(new Date());

                String strDate = formatter1.format(new Date());
                model.addAttribute("title", "Orders of " + strDate);
            }

            model.addAttribute("LIST", list);
            model.addAttribute("TotalOrder", list.size());
            int amount = orderService.TotalAmount(list);
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            model.addAttribute("TotalAmount", formatter.format(amount));

            return "admin/orders/indexOrder";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/admin/seachOrderMonth")
    public String searchmMonth(Model model, @RequestParam("month") String month) throws ParseException {
        if (session.getAttribute("ADMIN") != null) {
            List<Orders> list;
            if (!month.isEmpty()) {
                String tmonth = month + "-01";
                Date searchmonth = new SimpleDateFormat("yyyy-MM-dd").parse(tmonth);
                list = orderService.OrdersOfMonth(searchmonth);
            } else {
                list = orderService.OrdersOfMonth(new Date());
            }
            model.addAttribute("LIST", list);
            model.addAttribute("TotalOrder", list.size());
            int amount = orderService.TotalAmount(list);
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            model.addAttribute("TotalAmount", formatter.format(amount));
            model.addAttribute("title", "Orders of " + month);
            return "admin/orders/OrderList";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/admin/searchOrderByTime", method = RequestMethod.GET)
    public String searchFromTo(Model model, @RequestParam("timeFrom") String timeFrom, @RequestParam("timeTo") String timeTo) throws ParseException {
        if (session.getAttribute("ADMIN") != null) {
            if (!timeFrom.isEmpty() && !timeTo.isEmpty()) {
                Date from = new SimpleDateFormat("yyyy-MM-dd").parse(timeFrom);
                Date to = new SimpleDateFormat("yyyy-MM-dd").parse(timeTo);
                List<Orders> list = orderService.SearchFromTo(from, to);

                model.addAttribute("TotalOrder", list.size());
                int amount = orderService.TotalAmount(list);
                DecimalFormat formatter = new DecimalFormat("###,###,###");
                model.addAttribute("TotalAmount", formatter.format(amount));
                model.addAttribute("title", "Orders from " + timeFrom + " to " + timeTo);
            }

            return "admin/orders/OrderList";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("admin-order/{orderid}")
    public String detail(Model model, @PathVariable("orderid") Integer orderid) {
        if (session.getAttribute("ADMIN") != null) {
            Orders order = orderService.findOne(orderid);

            model.addAttribute("LIST", detailService.detail(order));
            model.addAttribute("order", order);
            return "admin/orders/OrderDetail";
        } else {
            return "redirect:/login";
        }
    }

}
