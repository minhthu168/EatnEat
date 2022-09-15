/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.controller;

import fpt.aptech.EatnEat.entities.Employee;
import fpt.aptech.EatnEat.entities.Favorite;
import fpt.aptech.EatnEat.entities.Food;
import fpt.aptech.EatnEat.service.FavoriteService;
import fpt.aptech.EatnEat.service.FoodService;
import fpt.aptech.EatnEat.service.OrderDetailService;
import fpt.aptech.EatnEat.service.OrderService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("favor")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("list")
    public String list(HttpSession session, Model model) {
        Employee employee = (Employee) session.getAttribute("EMPLOY");
        if (employee != null) {
            model.addAttribute("list", favoriteService.getFavoriteListByEmp(employee.getEmpid()));

        } else {
            return "redirect:/login";
        }
        return "favorite";
    }

    @GetMapping("add/{foodid}")
    public String add(@PathVariable("foodid") int foodid, HttpSession session, RedirectAttributes redirectAttributes) {
        Food food = foodService.findOneFood(foodid);
        Employee emp = (Employee) session.getAttribute("EMPLOY");
        Favorite favorite = favoriteService.findOne(emp.getEmpid(), foodid);
        if (emp != null) {
            if (favorite == null) {
                redirectAttributes.addFlashAttribute("message", "You have liked " + food.getFoodname() + "and added to Your Favorites");
                //them moi item(=food) vao gio               
                Favorite fa = new Favorite();
                fa.setFoodid(food.getFoodid());
                fa.setEmpid(emp.getEmpid());
                fa.setName(food.getFoodname());
                fa.setPrice(food.getPrice());
                fa.setQuantity(1);
                fa.setImage(food.getImage());
                favoriteService.create(fa);
            } else {

                redirectAttributes.addFlashAttribute("message", "You have removed " + food.getFoodname() + " from Your Favorites");
                favoriteService.delete(favorite);
            }
        } else {
            return "redirect:/login";
        }

        return "redirect:/favor/list";
    }

    @GetMapping("clear")
    public String clear(HttpSession session) {
        favoriteService.deleteAll();
        return "redirect:/favor/list";
    }
}
