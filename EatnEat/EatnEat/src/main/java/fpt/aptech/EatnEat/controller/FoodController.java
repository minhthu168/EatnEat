/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.EatnEat.controller;

import fpt.aptech.EatnEat.entities.Food;
import fpt.aptech.EatnEat.service.FoodService;
import fpt.aptech.EatnEat.service.MenutypeService;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FoodController {

    @Autowired
    public FoodService foodService;

    @Autowired
    public MenutypeService menutypeService;

    @Autowired
    ServletContext application;
    @Autowired
    HttpSession session;

    @RequestMapping("/admin/indexFood")
    public String indexFood(Model model) {
        if (session.getAttribute("ADMIN") != null) {
            model.addAttribute("listFood", foodService.findAllFood());
            return "admin/food/indexFood";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/admin-searchFood")
    public String search(Model model, @RequestParam("foodname") String foodname) {
        if (session.getAttribute("ADMIN") != null) {
            model.addAttribute("listFood", foodService.findByFoodname(foodname));
            return "admin/food/indexFood";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/admin/doCreateFood")
    public String doCreateFood(Model model) {
        if (session.getAttribute("ADMIN") != null) {
            model.addAttribute("food", new Food());
            model.addAttribute("list", menutypeService.findAllMenu());
            return "admin/food/createFood";

        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/admin/createFood")
    public String createFood(Model model,
            @Valid Food newFood, BindingResult bindingResult,
            @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
        if (session.getAttribute("ADMIN") != null) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("list", menutypeService.findAllMenu());
                return "admin/food/createFood";
            }

            String fileName = multipartFile.getOriginalFilename();
            newFood.setImage(fileName);

            Food savedFood = foodService.saveFood(newFood);

            model.addAttribute("list", menutypeService.findAllMenu());

            String uploadDir = "./images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                System.out.println(filePath.toFile().getAbsolutePath());
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("Could not save uploaded file" + fileName);
            }

            return "redirect:/admin-ingredientforfood/" + savedFood.getFoodid();
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping("/deleteFood/{id}")
    public String deleteFood(Model model,@PathVariable("id") Integer foodid) {
        if (session.getAttribute("ADMIN") != null) {
            Food food = foodService.findOneFood(foodid);
            if (food != null) {
                foodService.deleteFood(food);
            }
            return "redirect:../admin/indexFood";
        } else {
            return "redirect:/login";
        }
    }
}
