package fpt.aptech.EatnEat.controller;

import fpt.aptech.EatnEat.entities.*;
import fpt.aptech.EatnEat.entities.models.FoodToDay;
import fpt.aptech.EatnEat.service.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class IngredientController {

    @Autowired
    IngredientService service;

    @Autowired
    ReceipeService receipeService;

    @Autowired
    FoodService foodService;

    @Autowired
    ViewService viewService;

    @Autowired
    OrderDetailService detailService;
    @Autowired
    HttpSession session;

    @GetMapping("/admin/ingredient")
    public String ingre(Model model) {
        if (session.getAttribute("ADMIN") != null) {
            model.addAttribute("list", service.findAll());
            model.addAttribute("ingredient", new Ingredient());
            return "admin/ingredient/ingredient";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/admin/createIngredient")
    public String create(Model model, Ingredient ingredient) {
        if (session.getAttribute("ADMIN") != null) {
            service.save(ingredient);
            return "redirect:../admin/ingredient";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/editIngredient/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        if (session.getAttribute("ADMIN") != null) {
            model.addAttribute("ingre", service.findOne(id));
            return "admin/ingredient/editIngredient";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/admin/deleteIngredient/{id}")
    public String index(Model model, @PathVariable("id") Integer id) {
        if (session.getAttribute("ADMIN") != null) {
            Ingredient ingredient = service.findOne(id);
            service.delete(ingredient);
            return "redirect:../ingredient";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/admin-ingredientforfood/{id}")
    public String createReceipe(Model model, @PathVariable("id") Integer id) {
        if (session.getAttribute("ADMIN") != null) {
            model.addAttribute("food", foodService.findOneFood(id));
            model.addAttribute("ingre", service.findAll());
            Receipe receipe = new Receipe();
            receipe.setFoodid(foodService.findOneFood(id));
            model.addAttribute("receipe", receipe);
            model.addAttribute("listReceipe", foodService.findOneFood(id).getReceipeList());
            return "admin/ingredient/receipe";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("admin/createIngredientforfood")
    public String DocreateReceipe(Model model, Receipe receipe) {
        if (session.getAttribute("ADMIN") != null) {
            if (receipeService.IsExsist(receipe.getFoodid(), receipe.getIngredientid()) == null) {
                Receipe r = receipeService.save(receipe);
            }
            return "redirect:../admin-ingredientforfood/" + receipe.getFoodid().getFoodid();
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/deleteReipece/{id}/{foodid}")
    public String deleteReceipe(Model model, @PathVariable("id") Integer id, @PathVariable("foodid") Integer foodid) {
        if (session.getAttribute("ADMIN") != null) {
            Receipe r = receipeService.findOne(id);
            receipeService.delete(r);
            return "redirect:/admin-ingredientforfood/" + foodid;
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/admin/ingredientforDay")
    public String ingreforDay(Model model) {
        if (session.getAttribute("ADMIN") != null) {
            List<Orderdetail> orders = detailService.FoodsOfDay(new Date());

            HashMap<Integer, FoodToDay> listFood = service.getListFoodToDay(orders);

            HashMap<String, IngredientToDay> list = service.getListIngredientToDay(listFood);
           
            model.addAttribute("FoodToDay", listFood.values());
            model.addAttribute("list", list.values());
            return "admin/ingredient/ingredientforDay";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/admin/ingredientforDay/{id}")
    public String ingreforFoodToDay(Model model, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("ADMIN") != null) {
            List<Orderdetail> orders = detailService.FoodsOfDay(new Date());
            HashMap<Integer, FoodToDay> listFood = service.getListFoodToDay(orders);
            FoodToDay food = listFood.get(id);
            List<IngredientToDay> ingreList = service.getListIngredientforFoodToDay(food);
            redirectAttributes.addFlashAttribute("listIngreforFood", ingreList);
            return "redirect:/admin/ingredientforDay";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/admin/searchIngredientByTime")
    public String searchFromTo(Model model, @RequestParam("timeFrom") String timeFrom, @RequestParam("timeTo") String timeTo) throws ParseException {
        if (session.getAttribute("ADMIN") != null) {
            if (!timeFrom.isEmpty() && !timeTo.isEmpty()) {
                Date from = new SimpleDateFormat("yyyy-MM-dd").parse(timeFrom);
                Date to = new SimpleDateFormat("yyyy-MM-dd").parse(timeTo);
                List<Orderdetail> orders = detailService.SearchFromTo(from, to);
                HashMap<Integer, FoodToDay> listFood = service.getListFoodToDay(orders);
                HashMap<String, IngredientToDay> list = service.getListIngredientToDay(listFood);
                double total = list.values().stream().mapToDouble(item -> item.getTotalamount()).sum();
                DecimalFormat formatter = new DecimalFormat("###,###,###");
                model.addAttribute("total", formatter.format(total));
                model.addAttribute("list", list.values());
                model.addAttribute("FoodToDay", listFood.values());
            }

            return "admin/ingredient/ingredientforDay";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/admin/TotalIngredientforFood_{id}_{num}")
    public String TotalReceipe(Model model, @PathVariable("id") Integer id, @PathVariable("num") Integer num) {
        if (session.getAttribute("ADMIN") != null) {
            Food food = foodService.findOneFood(id);
            List<Receipe> list = food.getReceipeList();
            for (Receipe receipe : list) {
                receipe.setQuantity(receipe.getQuantity() * num);
            }
            model.addAttribute("listReceipe", list);
            model.addAttribute("title", "Ingredients for " + num + " " + food.getFoodname());
            return "admin/ingredient/ingredientforfood";
        } else {
            return "redirect:/login";
        }
    }

}
